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
import lapr.project.gpsd.controller.AssociatePostalAddressToClientController;
import lapr.project.gpsd.controller.AuthenticationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AssociatePostalAddressUI implements Initializable{

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtTown;
    @FXML
    private Button btnAddPostalAddress;

    AuthenticationController authController;
    AssociatePostalAddressToClientController addController = new AssociatePostalAddressToClientController();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    

    public void init(AuthenticationController authController, Stage window){
        this.authController = authController;
        this.addController.addToListOfWindows(window);
        this.addController.newAssociation();
    }
    
    @FXML
    private void actionAddPostalAddress(ActionEvent event){
        this.addController.newPostalAddress(txtAddress.getText(), txtZipCode.getText(), txtTown.getText());
        try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/07.1 - SeeInfo.fxml"));
                Parent addInfo = loader.load();

                Scene addInfoScene = new Scene(addInfo);
                AssociateSeeInfoUI ui = loader.getController();    
                ui.init(this.authController, this.addController);
                
                Stage window = new Stage();
                window.setScene(addInfoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.addController.addToListOfWindows(window);
                window.show();
            }catch(IOException e){
                System.out.println(e);
            }
    }
}