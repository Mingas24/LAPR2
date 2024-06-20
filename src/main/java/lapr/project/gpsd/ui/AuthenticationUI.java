package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserRole;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.utils.Constants;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AuthenticationUI implements Initializable{

    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private Label lblError;
    @FXML
    private Button btnLogIn;
    @FXML
    private Button btnBack;
    
    AuthenticationController auth_controller = new AuthenticationController();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        // TODO
    }    

    @FXML
    private void actionLogIn(ActionEvent event){
        String sEmail = txtEmail.getText();
        String sPwd = txtPassword.getText();
            
        boolean sucess = auth_controller.doLogin(sEmail, sPwd);
        if (!sucess)
            lblError.setText("User and/or Password wrong!");
        else
            redirectsUser(auth_controller.getUserRoles(), event);
    }
    
    private void redirectsUser(List<UserRole> roles, ActionEvent event){
        if (roles == null)
           return;
        if (roles.isEmpty())
           return;
                  
        UserRole role = selectsRole(roles);
       
        if (role.hasId(Constants.ROLE_ADMIN)){
            try{
                toMenuAdminUI(event);
            }catch(IOException ex){
                createErrorAlert(ex).show();
            }
        }
        if (role.hasId(Constants.ROLE_CLIENT)){
            try{
                toMenuClientUI(event);
            }catch(IOException ex){
                createErrorAlert(ex).show();
            }
        }
        if (role.hasId(Constants.ROLE_SERVICE_PROVIDER)){
            try{
                toMenuServiceProviderUI(event);
            }catch(IOException ex){
                createErrorAlert(ex).show();
            }
        }
        if (role.hasId(Constants.ROLE_HRO)){
            try{
                toMenuHROUI(event);
            }catch(IOException ex){
                createErrorAlert(ex).show();
            }
        }
    }

    private UserRole selectsRole(List<UserRole> roles){
        if (roles.size() == 1)
            return roles.get(0);
        else
            return toShowAndSelect(roles);
    }
    
    private UserRole toShowAndSelect(List<UserRole> roles){
        UserRole role = null;
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/00.1.1 - SelectRole.fxml"));
            Parent selectRole = loader.load();

            Scene selectRoleScene = new Scene(selectRole);
            selectRoleScene.getStylesheets().add("/styles/Styles.css");
            SelectRoleUI ui = loader.getController();    
            ui.initUI(this.auth_controller, roles, role);

            Stage window = new Stage();
            window.setScene(selectRoleScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            window.showAndWait();
            role = ui.role;
        }catch(IOException e){
            
        }
        return role;
    }
    
    public List<UserRole> getUserRoles(){
        return this.auth_controller.getUserRoles();
    }

    /**
     * Logs Out.
     */
    public void logout(){
        auth_controller.doLogout();
    }
    
    /**
     * 
     * @param event
     * @throws IOException
     */
    public void toMenuAdminUI(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/00.2 - MenuAdmin.fxml"));
        Parent administrative = loader.load();
        
        Scene administrativeScene = new Scene(administrative);
        administrativeScene.getStylesheets().add("/styles/Styles.css");
        MenuAdminUI ui = loader.getController();    
        ui.init(auth_controller);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(administrativeScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        window.show();
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void toMenuClientUI(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/00.3 - MenuClient.fxml"));
        Parent client = loader.load();
        
        Scene clientScene = new Scene(client);
        clientScene.getStylesheets().add("/styles/Styles.css");
        MenuClientUI ui = loader.getController();    
        ui.initContr(auth_controller);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(clientScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        window.show();
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void toMenuServiceProviderUI(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/00.4 - MenuServiceProvider.fxml"));
        Parent serviceProvider = loader.load();
        
        Scene serviceProviderScene = new Scene(serviceProvider);
        serviceProviderScene.getStylesheets().add("/styles/Styles.css");
        MenuServiceProviderUI ui = loader.getController();    
        ui.initContr(auth_controller);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(serviceProviderScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        window.show();
    }
    
    /**
     *
     * @param event
     * @throws IOException
     */
    public void toMenuHROUI(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/00.5 - MenuHRO.fxml"));
        Parent hro = loader.load();
        
        Scene hroScene = new Scene(hro);
        hroScene.getStylesheets().add("/styles/Styles.css");
        MenuHROUI ui = loader.getController();    
        ui.init(auth_controller);
        
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(hroScene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        window.show();
    }
    
    /**
     * 
     * @param ex
     * @return 
     */
    private Alert createErrorAlert(Exception ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Client Services Provider");
        alert.setHeaderText("Window transition problems!\n");
        alert.setContentText(ex.getMessage());

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