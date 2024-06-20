package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterClientController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RegisterCompleteUI implements Initializable{

    @FXML
    private Button btnConfirm;
    @FXML
    private Label lblInfo;
    @FXML
    private Button btnBack;
    
    RegisterClientController registerController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void initContr(RegisterClientController registerController){
        this.registerController = registerController;
        lblInfo.setText(this.registerController.toString());
    }    

    @FXML
    private void actionConfirm(ActionEvent event) throws IOException{
        if (this.registerController.registerClient()){
//            this.registerController.saveInFile();;
            this.registerController.registComplete();
            this.registerController.changeToAuthentication(event);
        }
    }

    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}