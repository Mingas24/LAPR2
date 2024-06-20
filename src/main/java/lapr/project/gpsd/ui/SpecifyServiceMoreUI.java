package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.SpecifyServiceController;
import lapr.project.gpsd.ui.SpecifyServiceInfoUI;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyServiceMoreUI implements Initializable {

    @FXML
    private Button btnContinue;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblExtraInfo;
    @FXML
    private TextField txtExtra;

    AuthenticationController authController;
    SpecifyServiceController specifyController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void init(AuthenticationController authController, SpecifyServiceController specifyController){
        this.authController = authController;
        this.specifyController = specifyController;
    }
    
    @FXML
    private void actionContinue(ActionEvent event) {
        this.specifyController.setExtraData(txtExtra.getText());
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/04.2 - SeeInfo.fxml"));
            Parent seeInfo = loader.load();

            Scene seeInfoScene = new Scene(seeInfo);
            SpecifyServiceInfoUI ui = loader.getController();

            Stage window = new Stage();
            window.setScene(seeInfoScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            this.specifyController.addToListOfWindows(window);
            ui.init(this.authController, this.specifyController);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void actionBack(ActionEvent event) {
    }
    
}
