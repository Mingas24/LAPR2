package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SubmitApplicationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AddProfessionalQualificationUI implements Initializable{
    
    @FXML
    private TextArea txtDescription;
    @FXML
    private Button btnAddProfessional;
    
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
    }
    
    @FXML
    private void actionAddProfessional(ActionEvent event){
        this.submitController.newProfessionalQualification(txtDescription.getText());
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}