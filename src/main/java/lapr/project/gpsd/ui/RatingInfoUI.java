package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RatingServiceProviderController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RatingInfoUI implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnBack;
    @FXML
    private Label lblInfo;

    AuthenticationController authController;
    RatingServiceProviderController rateController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(AuthenticationController authController, RatingServiceProviderController rateController) {
        this.authController = authController;
        this.rateController = rateController;
        this.lblInfo.setText(this.rateController.toString());
    }

    @FXML
    private void actionConfirm(ActionEvent event) {
        if (this.rateController.registerRating())
            this.rateController.closeWindows();
    }

    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}