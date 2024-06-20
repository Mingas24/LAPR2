/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.ReportEndServiceController;
import lapr.project.gpsd.model.ExecutionOrder;

/**
 * FXML Controller class
 *
 * @author Utilizador
 */
public class ReportEndServiceUI implements Initializable {

    @FXML
    private ChoiceBox<String> choiceEvaluation;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private ComboBox<ExecutionOrder> comboService;

    AuthenticationController authController;
    ReportEndServiceController reportController = new ReportEndServiceController();
    List<String> options;
    private ExecutionOrder execOrd;
    private int num;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.options = new ArrayList<>();
    }

    public void init(AuthenticationController authController, Stage window) {
        this.authController = authController;
        this.reportController.addToListOfWindows(window);
        this.options.add("Done without problems.");
        this.options.add("Done with problems.");
        this.comboService.getItems().setAll(this.reportController.newReportEndService());
        this.choiceEvaluation.getItems().setAll(options);
    }

    @FXML
    private void actionService(ActionEvent event) {
        this.execOrd = comboService.getValue();
    }

    @FXML
    private void actionConfirm(ActionEvent event) throws IOException {
        if (choiceEvaluation.getValue().equals("Done without problems.")){
            this.reportController.chooseEvaluation(true);
            this.reportController.closeWindows();
        }
        if (choiceEvaluation.getValue().equals("Done with problems.")) {
            this.reportController.chooseEvaluation(false);
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/13.1 - JustificationAndSolution.fxml"));
                Parent just = loader.load();

                Scene justScene = new Scene(just);
                JustificationAndSolutionUI ui = loader.getController();
                ui.init(this.authController, this.reportController);

                Stage window = new Stage();
                window.setScene(justScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.reportController.addToListOfWindows(window);
                window.show();
            } catch (IOException e) {
                System.out.println("Error in FXML File.");
            }
        }
    }

    @FXML
    private void actionBack(ActionEvent event) throws IOException {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
