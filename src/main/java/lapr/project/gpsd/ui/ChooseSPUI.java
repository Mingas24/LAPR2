package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.EvaluateServiceProviderController;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.utils.Time;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class ChooseSPUI implements Initializable {

    @FXML
    private Button btnEdit;
    @FXML
    private Button btnCancel;
    @FXML
    private ComboBox<ServiceProvider> comboSP;
    @FXML
    private ComboBox<String> comboLabel;
    
    AuthenticationController authController;
    EvaluateServiceProviderController evaluateController;
    List<ServiceProvider> spList;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void init(AuthenticationController authController, EvaluateServiceProviderController evaluateController, List<ServiceProvider> spList){
        this.authController = authController;
        this.evaluateController = evaluateController;
        this.spList = spList;
        this.comboSP.getItems().setAll(spList);
        this.comboLabel.getItems().setAll(getLabels());
    }
    
    private List<String> getLabels(){
        return new ArrayList<String>() {
            {
                add("Worst Providers");
                add("Regular Providers");
                add("Outstanding Providers");
            }
        };
    }
    
    @FXML
    private void actionEdit(ActionEvent event) {
        this.evaluateController.changeServiceProviderLabel(this.comboSP.getValue(), this.comboLabel.getValue());
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    
}
