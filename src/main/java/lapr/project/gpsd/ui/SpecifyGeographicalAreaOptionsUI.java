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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.SpecifyGeographicalAreaController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyGeographicalAreaOptionsUI implements Initializable{

    @FXML
    private Button btnSeeList;
    @FXML
    private Button btnComplete;

    AuthenticationController auth_controller;
    SpecifyGeographicalAreaController specifyAreaController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void initCont(AuthenticationController auth_controller, SpecifyGeographicalAreaController specifyAreaController){
        this.auth_controller = auth_controller;
        this.specifyAreaController = specifyAreaController;
    }

    @FXML
    private void actionSeeList(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/05.2 - ListOfZipCodes.fxml"));
        Parent list = loader.load();

        Scene listScene = new Scene(list);
        SpecifyGeographicalAreaListUI ui = loader.getController();    
        ui.init(this.auth_controller, this.specifyAreaController);

        Stage window = new Stage();
        window.setScene(listScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        window.show();
    }

    @FXML
    private void actionComplete(ActionEvent event){
        if(this.specifyAreaController.registerArea()){
//            this.specifyAreaController.saveInFile();
            this.specifyAreaController.closeWindows();
        }
    }
}