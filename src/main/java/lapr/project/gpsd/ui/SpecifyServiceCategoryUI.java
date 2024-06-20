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
import lapr.project.gpsd.controller.SpecifyServiceCategoryController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyServiceCategoryUI implements Initializable{

    @FXML
    private Button btnSpecify;
    @FXML
    private Button btnBack;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtDescription;

    AuthenticationController authController;
    SpecifyServiceCategoryController specifyController = new SpecifyServiceCategoryController();
    
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
        this.specifyController.addToListOfWindows(window);
    }
    
    @FXML
    private void actionSpecify(ActionEvent event){
        if(this.specifyController.newCategory(txtID.getText(), txtDescription.getText())){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/03.1 - SeeInfo.fxml"));
                Parent specifyCatInfo = loader.load();

                Scene specifyCatInfoScene = new Scene(specifyCatInfo);
                SpecifyServiceCategoryInfoUI ui = loader.getController();    
                ui.init(this.authController, this.specifyController);
                
                Stage window = new Stage();
                window.setScene(specifyCatInfoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.specifyController.addToListOfWindows(window);
                window.show();
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}