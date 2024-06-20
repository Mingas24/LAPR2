package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class MainPageUI implements Initializable{

    @FXML
    private Button btnAuthentication;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnSubmit;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    

    @FXML
    private void actionAuthentication(ActionEvent event) throws IOException{
        Parent authentication = FXMLLoader.load(getClass().getResource("/fxml/00.1 - Authentication.fxml"));
        Scene authenticationScene = new Scene(authentication);
        authenticationScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(authenticationScene);
        window.show();
    }

    @FXML
    private void actionRegister(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/01 - RegisterAsClient.fxml"));
        Parent register = loader.load();
                
        Scene registerScene = new Scene(register);
        registerScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        
        RegisterClientUI ui = loader.getController();    
        ui.init(window);
        window.show();
    }

    @FXML
    private void actionSubmitApplication(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/02 - SubmitApplication.fxml"));
        Parent submitApplication = loader.load();
                
        Scene submitApplicationScene = new Scene(submitApplication);
        submitApplicationScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(submitApplicationScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        
        SubmitApplicationUI ui = loader.getController();    
        ui.init(window);
        window.show();
    }
}