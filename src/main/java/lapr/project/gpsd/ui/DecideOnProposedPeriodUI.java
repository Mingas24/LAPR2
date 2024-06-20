package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.DecidesOnProposedPeriodController;
import lapr.project.gpsd.model.AssignedSchedule;
import lapr.project.gpsd.model.AssignedService;
import lapr.project.gpsd.model.ServiceRequest;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class DecideOnProposedPeriodUI implements Initializable {

    @FXML
    private Button btnAccept;
    @FXML
    private Button btnReject;
    @FXML
    private Label lblPeriod;
    
    AuthenticationController authController;
    DecidesOnProposedPeriodController decideController = new DecidesOnProposedPeriodController();
    List<ServiceRequest> requestList;
    List<AssignedService> assignedByRequestList;
    List<AssignedService> assignedList;
    int i;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.i = 0;
    }    

    public void init(AuthenticationController authController, Stage window){
        this.authController = authController;
        this.decideController.addToListOfWindows(window);
        this.requestList = this.decideController.getServiceRequests();
        for (ServiceRequest serv: this.requestList){
            this.assignedByRequestList = this.decideController.getAssignedServices(serv);
            for (AssignedService ass: assignedByRequestList)
                this.assignedList.add(ass);
        }
        if (this.assignedList.get(0).equals(null)){
            createAlert();
            this.decideController.closeWindows();
        }else
            lblPeriod.setText(this.assignedList.get(i).toString());
    }
    
    private Alert createAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Client Services Provider");
        alert.setHeaderText("No services to decide!\n");

        return alert;
    }
    
    @FXML
    private void actionAccept(ActionEvent event) {
        this.decideController.acceptAssignService(this.assignedList.get(++i));
        if (i < this.assignedList.size())
            lblPeriod.setText(this.assignedList.get(i).toString());
        else{
            this.decideController.notifiesServiceProvider();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }  
    }

    @FXML
    private void actionReject(ActionEvent event) {
        this.decideController.rejectAssignService(this.assignedList.get(++i));
        if (i < this.assignedList.size())
            lblPeriod.setText(this.assignedList.get(i).toString());
        else{
            this.decideController.notifiesServiceProvider();
            ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        }  
    }
}
