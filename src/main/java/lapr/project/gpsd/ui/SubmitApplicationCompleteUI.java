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
import lapr.project.gpsd.controller.SubmitApplicationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SubmitApplicationCompleteUI implements Initializable{

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;
    
    SubmitApplicationController submitController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(SubmitApplicationController submitController){
        this.submitController = submitController;
        lblInfo.setText(this.submitController.toString());
    }

    @FXML
    private void actionConfirm(ActionEvent event) throws IOException{
        if(this.submitController.validateApplication()){
            this.submitController.registerApplication();
            this.submitController.finishSubmission(event);
        }
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}