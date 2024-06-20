package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RegisterServiceProviderController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RegisterSPInfoUI implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;

    AuthenticationController auth_controller;
    RegisterServiceProviderController registerServProvController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void init(AuthenticationController auth_controller, RegisterServiceProviderController registerServProvController){
        this.auth_controller = auth_controller;
        this.registerServProvController = registerServProvController;
        this.lblInfo.setText(this.registerServProvController.getInfoToConfirmation());
    }
    
    @FXML
    private void actionConfirm(ActionEvent event) {
        if (this.registerServProvController.registerServiceProvider())
            this.registerServProvController.closeWindows();
    }

    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}