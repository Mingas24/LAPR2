package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.EvaluateServiceProviderController;
import lapr.project.gpsd.model.ServiceProvider;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class EvaluateServiceProvidersUI implements Initializable {

    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    private Label lblSP;
    @FXML
    private Label lblRating;
    @FXML
    private Label lblLabels;
    @FXML
    private Label lblCompanyMean;
    @FXML
    private Pane pane;
    
    AuthenticationController authController;
    EvaluateServiceProviderController evaluateController;
    Stage window;
    @FXML
    private Button btnEdit;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.evaluateController = new EvaluateServiceProviderController();
    }    
    
    public void init(AuthenticationController authController, Stage window){
        this.authController = authController;
        this.window = window;
        this.evaluateController.addToListOfWindows(this.window);
        List<Double> d1 = this.evaluateController.calculateServiceProvidersMeanRatingList();
        double d2 = this.evaluateController.calculateEntireCompanyMeanRating(this.evaluateController.calculateServiceProvidersMeanRatingList());
        this.evaluateController.calculateServiceProviderLabels(d2, d1);
        
        this.lblCompanyMean.setText("Company Mean Rating: " + d2);
        
        setChart();
        
        this.lblSP.setText(this.evaluateController.getServiceProvidersNames());
        
        this.lblRating.setText(this.evaluateController.getServiceProvidersRates());
        
        this.lblLabels.setText(this.evaluateController.getServiceProvidersLabels());
    }
    
    private void setChart(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Service Providers");
 
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Mean Rating");
        
        BarChart chart = new BarChart(xAxis, yAxis);
        
        XYChart.Series serie = new XYChart.Series();
        serie.setName("Company");
        
        for (ServiceProvider sp: this.evaluateController.getSpList()){
            serie.getData().add(new XYChart.Data(sp.getName(), this.evaluateController.getServiceProviderMeanRating(sp)));
        }
        
        System.out.println(chart);
        chart.getData().add(serie);
        
        this.pane.getChildren().add(chart);
    }

    @FXML
    private void actionConfirm(ActionEvent event) {
        this.evaluateController.closeWindows();
    }

    @FXML
    private void actionCancel(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    private void actionEdit(ActionEvent event) {
        this.evaluateController.closeWindows();
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/15.1 - ChooseServiceProvider.fxml"));
            Parent change = loader.load();
                
            Scene changeScene = new Scene(change);
            ChooseSPUI ui = loader.getController();    
                
            Stage window2 = new Stage();
            window2.setScene(changeScene);
            window2.getIcons().add(new Image("file:icon.png"));
            window2.setTitle("Client Services Provider");
            ui.init(this.authController, this.evaluateController, this.evaluateController.getSpList());
            window2.show();
        }catch(IOException e){
            System.out.println(e);
        }
    }
}
