package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserRole;
import lapr.project.gpsd.controller.AuthenticationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SelectRoleUI implements Initializable {

    @FXML
    private Button btnOne;
    @FXML
    private Button btnTwo;

    AuthenticationController authController;
    List<UserRole> roles;
    public UserRole role;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initUI(AuthenticationController authController, List<UserRole> roles, UserRole role){
        this.authController = authController;
        this.roles = roles;
        btnOne.setText(this.roles.get(0).toString());
        btnTwo.setText(this.roles.get(1).toString());
    }
    
    @FXML
    private void actionOne(ActionEvent event){
        this.role = roles.get(0);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void actionTwo(ActionEvent event){
        this.role = roles.get(1);
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }  
}