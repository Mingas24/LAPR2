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
import lapr.project.gpsd.controller.SpecifyServiceController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyServiceInfoUI implements Initializable{

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;

    AuthenticationController authController;
    SpecifyServiceController specifyController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(AuthenticationController authController, SpecifyServiceController specifyController){
        this.authController = authController;
        this.specifyController = specifyController;
        this.lblInfo.setText(this.specifyController.toString());
    }

    @FXML
    private void actionConfirm(ActionEvent event){
        if (this.specifyController.validate()){
            if(this.specifyController.registerService()){
//              this.specifyController.saveInFile();
                this.specifyController.closeWindows();
            }
        }
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}