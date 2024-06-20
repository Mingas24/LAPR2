package lapr.project.gpsd.ui;

import static java.awt.SystemColor.window;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.ExportExecutionOrdersController;
import lapr.project.gpsd.utils.Time;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class ChooseFileUI implements Initializable {

    @FXML
    private Button btnSelect;
    @FXML
    private ComboBox<String> comboType;
    @FXML
    private Label lblX;

    AuthenticationController authController;
    ExportExecutionOrdersController exportController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(AuthenticationController authController, ExportExecutionOrdersController exportController) {
        this.authController = authController;
        this.exportController = exportController;
        List<String> types = new ArrayList<String>() {
            {
                add("CSV");
                add("XML");
                add("XLSX");
            }
        };
        this.comboType.getItems().addAll(types);
    }

    @FXML
    private void actionSelect(ActionEvent event) {
        this.exportController.writeExecutionOrders(this.comboType.getValue());
        this.exportController.closeWindows();
    }
}
