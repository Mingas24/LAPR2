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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.SpecifyGeographicalAreaController;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.ZipCode;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyGeographicalAreaUI implements Initializable{

    @FXML
    private Button btnContinue;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtDesignation;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtRadius;
    @FXML
    private TextField txtTransportationCost;

    AuthenticationController auth_controller;
    SpecifyGeographicalAreaController specifyAreaController = new SpecifyGeographicalAreaController();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }
    
    public void init(AuthenticationController auth_controller, Stage stage){
        this.auth_controller = auth_controller;
        this.specifyAreaController.addToListOfWindows(stage);
    }    

    @FXML
    private void actionContinue(ActionEvent event) throws IOException{
        GeographicalArea area = this.specifyAreaController.newArea(txtDesignation.getText(), new ZipCode(txtZipCode.getText()), Double.parseDouble(txtTransportationCost.getText()), Float.parseFloat(txtRadius.getText()));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/05.1 - Options.fxml"));
        Parent options = loader.load();
                
        Scene optionsScene = new Scene(options);
        SpecifyGeographicalAreaOptionsUI ui = loader.getController();    
        ui.initCont(this.auth_controller, this.specifyAreaController);
                
        Stage window = new Stage();
        window.setScene(optionsScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        this.specifyAreaController.addToListOfWindows(window);
        window.show();
    }

    @FXML
    private void actionBack(ActionEvent event)throws IOException{
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}