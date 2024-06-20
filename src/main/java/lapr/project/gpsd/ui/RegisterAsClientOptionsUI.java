package lapr.project.gpsd.ui;

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
import lapr.project.gpsd.controller.RegisterClientController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RegisterAsClientOptionsUI implements Initializable{

    @FXML
    private Button btnAddAddress;
    @FXML
    private Button btnComplete;
    
    RegisterClientController registerController;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }    

    public void init(RegisterClientController registerController){
        this.registerController = registerController;
    }
    
    @FXML
    private void actionAddAddress(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/01.2 - AddAddress.fxml"));
            Parent addAddress = loader.load();
                
            Scene addAddressScene = new Scene(addAddress);
            AddAddressUI ui = loader.getController();    
            ui.initCont(this.registerController);
                
            Stage window = new Stage();
            window.setScene(addAddressScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            this.registerController.addToListOfWindows(window);
            window.show();
        }catch(Exception e){
        }
    }

    @FXML
    private void actionComplete(ActionEvent event){
        try{
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/fxml/01.3 - RegisterComplete.fxml"));
            Parent complete = loader2.load();
                
            Scene completeScene = new Scene(complete);
            RegisterCompleteUI ui2 = loader2.getController();    
            ui2.initContr(registerController);
                
            Stage window2 = new Stage();
            window2.setScene(completeScene);
            window2.getIcons().add(new Image("file:icon.png"));
            window2.setTitle("Client Services Provider");
            this.registerController.addToListOfWindows(window2);
            window2.show();
        }catch(Exception e){
        }
    }
}