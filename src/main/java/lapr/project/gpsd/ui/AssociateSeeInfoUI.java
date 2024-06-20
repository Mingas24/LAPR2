package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AssociatePostalAddressToClientController;
import lapr.project.gpsd.controller.AuthenticationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AssociateSeeInfoUI implements Initializable{

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;

    AuthenticationController authController;
    AssociatePostalAddressToClientController addController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(AuthenticationController authController, AssociatePostalAddressToClientController addController){
        this.authController = authController;
        this.addController = addController;
        lblInfo.setText(this.addController.toString());
    }

    @FXML
    private void actionConfirm(ActionEvent event){
        if (this.addController.registerAddress())
            this.addController.closeWindows();
//        this.addController.saveInFile();
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}