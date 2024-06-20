package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RequestServiceExecutionController;
import lapr.project.gpsd.controller.SpecifyServiceCategoryController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RequestServiceInfoUI implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;

    AuthenticationController authController;
    RequestServiceExecutionController requestController;
    int num;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void init(AuthenticationController authController, RequestServiceExecutionController requestController){
        this.authController = authController;
        this.requestController = requestController;
        lblInfo.setText(this.requestController.toString());
    }

    @FXML
    private void actionConfirm(ActionEvent event) {
        this.num = this.requestController.registerRequest();
        createAlert();
        this.requestController.closeWindows();
    }

    private Alert createAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Client Services Provider");
        alert.setHeaderText("Your request number is:\n");
        String numStr = String.format("%d", this.num);
        alert.setContentText(numStr);

        return alert;
    }
    
    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}