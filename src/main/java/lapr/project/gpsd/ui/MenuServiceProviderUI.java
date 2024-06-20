package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;

public class MenuServiceProviderUI implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private Button btnIndicateAvailability;
    @FXML
    private Button btnCheckOrders;
    @FXML
    private Button btnReportService;

    AuthenticationController auth_controller;
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initContr(AuthenticationController auth_controller) {
        this.auth_controller = auth_controller;
    }

    @FXML
    private void actionIndicateAvailability(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/09 - IndicateDailyAvailability.fxml"));
            Parent indicate = loader.load();
                
            Scene indicateScene = new Scene(indicate);
            IndicateDailyAvailabilityUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(indicateScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void actionCheckOrders(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/12 - ExportExecutionOrders.fxml"));
            Parent report = loader.load();
                
            Scene reportScene = new Scene(report);
            ExportExecutionOrdersUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(reportScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }

    @FXML
    private void actionReportService(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/13 - ReportEndService.fxml"));
            Parent report = loader.load();
                
            Scene reportScene = new Scene(report);
            ReportEndServiceUI ui = loader.getController();    
                
            Stage window = new Stage();
            window.setScene(reportScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
    
    @FXML
    private void actionExit(ActionEvent event) {
        this.auth_controller.doLogout();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
