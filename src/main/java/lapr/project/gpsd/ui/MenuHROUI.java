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

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class MenuHROUI implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnEvaluate;

    AuthenticationController auth_controller;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void init(AuthenticationController auth_controller){
        this.auth_controller = auth_controller;
    }

    @FXML
    private void actionRegister(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/08 - RegisterServiceProvider.fxml"));
            Parent register = loader.load();
                
            Scene registerScene = new Scene(register);
            RegisterServiceProviderUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(registerScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void actionEvaluate(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/15 - EvaluateServiceProviders.fxml"));
            Parent evaluate = loader.load();
                
            Scene evaluateScene = new Scene(evaluate);
            EvaluateServiceProvidersUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(evaluateScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(this.auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    @FXML
    private void actionExit(ActionEvent event) {
        this.auth_controller.doLogout();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}