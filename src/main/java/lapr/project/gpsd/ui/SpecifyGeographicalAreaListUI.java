package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.SpecifyGeographicalAreaController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyGeographicalAreaListUI implements Initializable{

    @FXML
    private Button btnBack;
    @FXML
    private ScrollPane listZipCodes;
    @FXML
    private Label lblInfo;    
    
    AuthenticationController auth_controller;
    SpecifyGeographicalAreaController specifyAreaController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }
    
    public void init(AuthenticationController auth_controller, SpecifyGeographicalAreaController specifyAreaController){
        this.auth_controller = auth_controller;
        this.specifyAreaController = specifyAreaController;
        lblInfo.setText(this.specifyAreaController.getCoverageListToString());
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}