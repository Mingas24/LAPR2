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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterClientController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RegisterClientUI implements Initializable{

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTIN;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtTown;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnBack;
    
    RegisterClientController registerController = new RegisterClientController();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(Stage stage){
        this.registerController.addToListOfWindows(stage);
    }

    @FXML
    private void actionRegisterAsClient(ActionEvent event) throws IOException{
        if(registerController.newClient(txtName.getText(), txtTIN.getText(), 
                txtTelephone.getText(), txtEmail.getText(), txtPassword.getText(), 
                txtAddress.getText(), txtZipCode.getText(), txtTown.getText())){
            
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/01.1 - Options.fxml"));
                Parent options = loader.load();
                
                Scene optionsScene = new Scene(options);
                optionsScene.getStylesheets().add("/styles/Styles.css");
                RegisterAsClientOptionsUI ui = loader.getController();    
                ui.init(registerController);
                
                Stage window = new Stage();
                window.setScene(optionsScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.registerController.addToListOfWindows(window);
                window.show();
            }catch(Exception e){      
            }
        }else{
            createErrorAlert().show();
        }
    }
    
    /**
     * 
     * @return 
     */
    private Alert createErrorAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Client Services Provider");
        alert.setHeaderText("Information Missing!\n");

        return alert;
    }

    @FXML
    private void actionBack(ActionEvent event) throws IOException{
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        Parent mainPage = FXMLLoader.load(getClass().getResource("/fxml/00 - MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        mainPageScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
}