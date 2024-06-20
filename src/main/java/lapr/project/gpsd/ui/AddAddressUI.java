package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.RegisterClientController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AddAddressUI implements Initializable{

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtTown;
    @FXML
    private Button btnAddPostalAddress;

    RegisterClientController registerController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void initCont(RegisterClientController registerController){
        this.registerController = registerController;
    }    
    
    @FXML
    private void actionAddPostalAddress(ActionEvent event) {
        if (registerController.addPostalAddress(txtAddress.getText(), txtZipCode.getText(), txtTown.getText()))
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }   
}