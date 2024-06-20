package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.ReportEndServiceController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class JustificationAndSolutionUI implements Initializable {

    @FXML
    private TextField txtJust;
    @FXML
    private TextField txtSol;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;

    AuthenticationController authController;
    ReportEndServiceController reportController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void init(AuthenticationController authController, ReportEndServiceController reportController) {
        this.authController = authController;
        this.reportController = reportController;
    }

    @FXML
    private void actionConfirm(ActionEvent event) {
        if (this.reportController.reportEndService(txtJust.getText(), txtSol.getText()))
            this.reportController.closeWindows();
    }

    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
