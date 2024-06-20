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

public class MenuAdminUI implements Initializable{

    @FXML
    private Button btnExit;
    @FXML
    private Button btnSpecifyCategory;
    @FXML
    private Button btnSpecifyService;
    @FXML
    private Button btnSpecifyArea;

    AuthenticationController auth_controller;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void init(AuthenticationController auth_controller){
        this.auth_controller = auth_controller;
    }

    @FXML
    private void actionSpecifyCategory(ActionEvent event) throws IOException{
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/03 - SpecifyServiceCategory.fxml"));
            Parent specifyCat = loader.load();
                
            Scene specifyCatScene = new Scene(specifyCat);
            SpecifyServiceCategoryUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(specifyCatScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void actionSpecifyService(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/04 - SpecifyService.fxml"));
            Parent specifyServ = loader.load();
                
            Scene specifyServScene = new Scene(specifyServ);
            SpecifyServiceUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(specifyServScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void actionSpecifyArea(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/05 - SpecifyGeographicalArea.fxml"));
        Parent options = loader.load();
                
        Scene optionsScene = new Scene(options);
        SpecifyGeographicalAreaUI ui = loader.getController();    
                
        Stage window = new Stage();
        window.setScene(optionsScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        ui.init(auth_controller, window);
        window.show();
    }
    
    @FXML
    private void actionExit(ActionEvent event){
        this.auth_controller.doLogout();
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}