package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SubmitApplicationController;
import lapr.project.gpsd.model.Category;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SubmitApplicationUI implements Initializable{

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtTIN;
    @FXML
    private TextField txtTelephone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtZipCode;
    @FXML
    private TextField txtTown;
    @FXML
    private ComboBox<Category> comboCat;
    @FXML
    private Button btnApply;
    @FXML
    private Button btnBack;
    
    SubmitApplicationController submitController = new SubmitApplicationController();
    List<Category> categoryList = this.submitController.getCategoryList();
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        List<Category> categoryList = this.submitController.getCategoryList();
        comboCat.getItems().addAll(categoryList);
    }    
    
    public void init(Stage stage){
        this.submitController.addToListOfWindows(stage);
    }

    @FXML
    private void actionApply(ActionEvent event) throws IOException{
        this.submitController.newApplication(txtName.getText(), txtTIN.getText(), txtTelephone.getText(), txtEmail.getText(), txtAddress.getText(), txtZipCode.getText(), txtTown.getText());
        this.submitController.registerCategory(comboCat.getValue());
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/02.1 - Options.fxml"));
        Parent options = loader.load();

        Scene options2Scene = new Scene(options);
        options2Scene.getStylesheets().add("/styles/Styles.css");
        SubmitApplicationOptionsUI ui = loader.getController();    
        ui.init(submitController);

        Stage window = new Stage();
        window.setScene(options2Scene);
        window.getIcons().add(new Image("file:icon.png"));
        window.setTitle("Client Services Provider");
        this.submitController.addToListOfWindows(window);
        window.show();
    }
    
    @FXML
    private void actionBack(ActionEvent event) throws IOException{
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        Parent mainPage = FXMLLoader.load(getClass().getResource("/fxml/00 - MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPage);
        mainPageScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
}