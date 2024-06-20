package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.RegisterServiceProviderController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.ServiceProvider;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RegisterServiceProviderUI implements Initializable{

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtTIN;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtTown;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnBack;
    @FXML
    private ChoiceBox<GeographicalArea> choiceArea;
    @FXML
    private Label lblInfo;
    
    AuthenticationController auth_controller;
    RegisterServiceProviderController registerServProvController = new RegisterServiceProviderController();

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(AuthenticationController auth_controller, Stage stage){
        this.auth_controller = auth_controller;
        this.registerServProvController.addToListOfWindows(stage);
        List<GeographicalArea> geoAreaList = this.registerServProvController.getGeographicalAreasList();
        choiceArea.getItems().addAll(geoAreaList);
    }    
    
    @FXML
    private void actionInsertedTin(ActionEvent event){
        if (this.registerServProvController.existServiceProviderByTin(txtTIN.getText())){
            ServiceProvider servProv = this.registerServProvController.getServProv();
            changeToAble();
            changeToTheInfo(servProv);
        }else
            lblInfo.setText("Service Provider not found!");
    }
    
    private void changeToAble(){
        txtName.setDisable(false);
        txtTelephone.setDisable(false);
        txtEmail.setDisable(false);
        txtAddress.setDisable(false);
        txtZipCode.setDisable(false);
        txtTown.setDisable(false);
        choiceArea.setDisable(false);
    }
    
    private void changeToTheInfo(ServiceProvider servProv){
        txtName.setText(servProv.getName());
        txtTelephone.setText(servProv.getTelephone());
        txtEmail.setText(servProv.getEmail());
        PostalAddress postalAddress = servProv.getPostalAddress();
        txtAddress.setText(postalAddress.getAddress());
        txtZipCode.setText(postalAddress.getZipCode().toString());
        txtTown.setText(postalAddress.getTown());
    }
    
    @FXML
    private void actionRegister(ActionEvent event){
        if (this.registerServProvController.addGeographicalArea(choiceArea.getValue().getAreaID())){
            try{
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/08.1 - SeeInfo.fxml"));
                Parent info = loader.load();

                Scene infoScene = new Scene(info);
                RegisterSPInfoUI ui = loader.getController();    

                Stage window = new Stage();
                window.setScene(infoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.registerServProvController.addToListOfWindows(window);
                ui.init(this.auth_controller, this.registerServProvController);
                window.show();
            }catch(IOException e){
                System.out.println(e);
            }
        }
    }

    @FXML
    private void actionBack(ActionEvent event){
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }   
}
