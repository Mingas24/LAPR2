package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.ExportExecutionOrdersController;
import lapr.project.gpsd.utils.Date;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class ExportExecutionOrdersUI implements Initializable {

    @FXML
    private Button btnConsult;
    @FXML
    private Button btnBack;
    @FXML
    private DatePicker initDate;
    @FXML
    private DatePicker endDate;

    AuthenticationController authController;
    ExportExecutionOrdersController exportController = new ExportExecutionOrdersController();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void init(AuthenticationController authController, Stage window) {
        this.authController = authController;
        this.exportController.addToListOfWindows(window);
        this.exportController.newExportation();
    }
    
    @FXML
    private void actionConsult(ActionEvent event) {
        Date initDateF = getToDate(initDate.getValue());
        Date endDateF = getToDate(endDate.getValue());
        this.exportController.getExecutionOrders(initDateF, endDateF);
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/12.1 - SelectFile.fxml"));
            Parent choose = loader.load();

            Scene chooseScene = new Scene(choose);
            ChooseFileUI ui = loader.getController();
            ui.init(this.authController, this.exportController);

            Stage window = new Stage();
            window.setScene(chooseScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            this.exportController.addToListOfWindows(window);
            window.show();
        } catch (IOException e) {
            System.out.println("Error in FXML File.");
        }
    }

    private Date getToDate(LocalDate dateL){
        int year = dateL.getYear();
        int month = dateL.getMonthValue();
        int day = dateL.getDayOfMonth();
        return new Date(year, month, day);
    }
    
    @FXML
    private void actionBack(ActionEvent event) {
    }
    
}
