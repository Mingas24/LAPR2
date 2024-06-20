package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RequestServiceExecutionController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.FixedService;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PreferedSchedule;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RequestServiceExecutionUI implements Initializable {

    @FXML
    private Button btnLess;
    @FXML
    private Button btnMore;
    @FXML
    private Button btnRequest;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnExit;
    @FXML
    private Label lblWarning;
    @FXML
    private TableColumn<PreferedSchedule, Date> dateColumn;
    @FXML
    private TableColumn<PreferedSchedule, String> orderColumn;
    @FXML
    private TableColumn<PreferedSchedule, Time> timeColumn;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Time> comboTime;
    @FXML
    private Button btnAdd;
    @FXML
    private Label lblTime;
    @FXML
    private TextArea txtDescription;
    @FXML
    private TableView<PreferedSchedule> tableViewSchedule;
    @FXML
    private ComboBox<Category> comboCategory;
    @FXML
    private ComboBox<Service> comboService;
    @FXML
    private ComboBox<PostalAddress> comboPostalAddress;
    
    AuthenticationController authController;
    RequestServiceExecutionController requestController = new RequestServiceExecutionController();
    List<PreferedSchedule> scheduleList;
    boolean created;
    double cost;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.scheduleList = new ArrayList<>();
        this.created = false;
    }
    
    public void init(AuthenticationController authController, Stage window){
        this.authController = authController;
        this.requestController.addToListOfWindows(window);
        this.comboPostalAddress.getItems().addAll(this.requestController.newRequest());
        this.comboCategory.getItems().setAll(this.requestController.getCategoryList());
        this.comboTime.getItems().setAll(this.requestController.getWorkingHours());
        this.orderColumn.setCellValueFactory(new PropertyValueFactory<>("order"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
    }
    
    @FXML
    private void actionPostalAddress(ActionEvent event) {
        this.requestController.setPostalAddress(this.comboPostalAddress.getValue());
    }
    
    @FXML
    private void actionCategory(ActionEvent event) {
        this.comboService.getItems().setAll(this.requestController.getServicesOfCat(this.comboCategory.getValue().getCatID()));
    }
    
    @FXML
    private void actionService(ActionEvent event) {
        if (this.comboService.getValue().needsExtraData()){
            lblTime.setText(((FixedService)this.comboService.getValue()).getDuration().toStringHHMM());
            btnLess.setDisable(true);
            btnMore.setDisable(true);
        }
    }
    
    @FXML
    private void actionLess(ActionEvent event) {
        String time = lblTime.getText();
        if (Integer.parseInt(time) > 30) {
            lblTime.setText(String.valueOf(Integer.parseInt(time) - 30));
        } else {
            lblWarning.setText("Minimal Time!");
        }
    }

    @FXML
    private void actionMore(ActionEvent event) {
        String time = lblTime.getText();
        lblTime.setText(String.valueOf(Integer.parseInt(time) + 30));
        lblWarning.setText("");
    }
    
    @FXML
    private void actionAdd(ActionEvent event) {
        if (this.created == false)
            this.requestController.addServiceRequest(this.comboService.getValue().getID(), this.txtDescription.getText(), toTime(Integer.parseInt(this.lblTime.getText())));
        
        Date date = getToDate(datePicker.getValue());
        
        this.requestController.addPreferedSchedule(date, this.comboTime.getValue());
        this.scheduleList = this.requestController.getPreferedSchedule();
        ObservableList<PreferedSchedule> list = FXCollections.observableArrayList(this.scheduleList);
        this.tableViewSchedule.setItems(list);
    }
    
    private Date getToDate(LocalDate dateL){
        int year = dateL.getYear();
        int month = dateL.getMonthValue();
        int day = dateL.getDayOfMonth();
        return new Date(year, month, day);
    }
    
    private Time toTime(int minutes){
        int hours = minutes / 60;
        minutes -= (hours * 60);
        return new Time(hours, minutes);
    }

    @FXML
    private void actionRequest(ActionEvent event) {
        if (this.requestController.validateRequest()){
            this.cost = this.requestController.getTotalCost();
            
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/06.1 - SeeInfo.fxml"));
                Parent requestInfo = loader.load();

                Scene requestInfoScene = new Scene(requestInfo);
                RequestServiceInfoUI ui = loader.getController();

                Stage window = new Stage();
                window.setScene(requestInfoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.requestController.addToListOfWindows(window);
                ui.init(this.authController, this.requestController);
                window.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    @FXML
    private void actionCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
    
    @FXML
    private void actionExit(ActionEvent event) {
        this.authController.doLogout();
        this.requestController.closeWindows();
    }
}