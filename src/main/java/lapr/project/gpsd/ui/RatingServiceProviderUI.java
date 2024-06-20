package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RatingServiceProviderController;
import lapr.project.gpsd.model.ExecutionOrder;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RatingServiceProviderUI implements Initializable {

    @FXML
    private Button btnRate;
    @FXML
    private ComboBox<ExecutionOrder> comboExecOrd;
    @FXML
    private CheckBox check1;
    @FXML
    private CheckBox check5;
    @FXML
    private CheckBox check3;
    @FXML
    private CheckBox check2;
    @FXML
    private CheckBox check4;
    
    AuthenticationController authController;
    RatingServiceProviderController rateController = new RatingServiceProviderController();
    List<ExecutionOrder> execOrdList;
    int rate;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void init(AuthenticationController authController, Stage window){
        this.authController = authController;
        this.rateController.addToListOfWindows(window);
        this.execOrdList = this.rateController.newRatingServiceProvider();
        this.comboExecOrd.getItems().setAll(execOrdList);
        this.rate = 0;
    }
    
    @FXML
    private void actionExecOrd(ActionEvent event) {
        this.rateController.chooseExecutionOrder(this.comboExecOrd.getValue());
    }

    @FXML
    private void action1(ActionEvent event) {
        this.rate = 1;
        this.check2.setSelected(false);
        this.check3.setSelected(false);
        this.check4.setSelected(false);
        this.check5.setSelected(false);
    }

    @FXML
    private void action2(ActionEvent event) {
        this.rate = 2;
        this.check1.setSelected(false);
        this.check3.setSelected(false);
        this.check4.setSelected(false);
        this.check5.setSelected(false);
    }
    
    @FXML
    private void action3(ActionEvent event) {
        this.rate = 3;
        this.check1.setSelected(false);
        this.check2.setSelected(false);
        this.check4.setSelected(false);
        this.check5.setSelected(false);
    }

    @FXML
    private void action4(ActionEvent event) {
        this.rate = 4;
        this.check1.setSelected(false);
        this.check2.setSelected(false);
        this.check3.setSelected(false);
        this.check5.setSelected(false);
    }

    @FXML
    private void action5(ActionEvent event) {
        this.rate = 5;
        this.check1.setSelected(false);
        this.check2.setSelected(false);
        this.check3.setSelected(false);
        this.check4.setSelected(false);
    }
    
    @FXML
    private void actionRate(ActionEvent event) {
        if (this.rate == 0)
            createErrorAlert();
        else{
            this.rateController.chooseRating(this.rate);
        }
    }
    
    private Alert createErrorAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Client Services Provider");
        alert.setHeaderText("Incorrect rating! Select a rating.\n");

        return alert;
    }
    
    private void seeInfo(){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/14.1 - SeeInfo.fxml"));
            Parent info = loader.load();
                
            Scene infoScene = new Scene(info);
            RatingInfoUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(infoScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(this.authController, this.rateController);
            this.rateController.addToListOfWindows(window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
