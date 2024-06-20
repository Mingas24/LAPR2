package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SubmitApplicationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AddAcademicQualificationUI implements Initializable{

    @FXML
    private Button btnAddAcademic;
    @FXML
    private TextField txtDesignation;
    @FXML
    private TextField txtDegree;
    @FXML
    private TextField txtClassification;
    
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
    private void actionAddAcademic(ActionEvent event){
        this.submitController.newAcademicQualification(txtDesignation.getText(), txtDegree.getText(), txtClassification.getText());
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}