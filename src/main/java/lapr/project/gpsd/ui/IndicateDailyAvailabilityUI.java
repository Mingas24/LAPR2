package lapr.project.gpsd.ui;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.IndicateDailyAvailabilityController;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class IndicateDailyAvailabilityUI implements Initializable {

    @FXML
    private DatePicker initDate;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<Time> initTime;
    @FXML
    private ComboBox<Time> endTime;
    @FXML
    private Button btnExit;
    @FXML
    private Button btnFinish;
    @FXML
    private Button buttonAdd;
    
    AuthenticationController authController;
    IndicateDailyAvailabilityController indicateController = new IndicateDailyAvailabilityController();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(AuthenticationController authController) {
        this.authController = authController;
        this.indicateController.identifyNewAvailabilities();
        this.initTime.getItems().setAll(this.indicateController.getWorkingHours());
        this.endTime.getItems().setAll(this.indicateController.getWorkingHours());
    }

    @FXML
    private void actionAdd(ActionEvent event) {
        Date initDateF = getToDate(this.initDate.getValue());
        Date endDateF = getToDate(this.endDate.getValue());
        
        this.indicateController.newAvailability(initDateF, this.initTime.getValue(), endDateF, this.endTime.getValue());
        
        clean();
    }

    private Date getToDate(LocalDate dateL){
        int year = dateL.getYear();
        int month = dateL.getMonthValue();
        int day = dateL.getDayOfMonth();
        return new Date(year, month, day);
    }
    
    private void clean(){
        this.initTime.setValue(null);
        this.initDate.setValue(null);
        this.endDate.setValue(null);
        this.endTime.setValue(null);
    }
    
    @FXML
    private void actionFinish(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void actionExit(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
