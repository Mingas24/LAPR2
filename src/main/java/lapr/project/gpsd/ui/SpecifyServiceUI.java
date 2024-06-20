package lapr.project.gpsd.ui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;
import lapr.project.gpsd.controller.SpecifyServiceController;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.ServiceType;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SpecifyServiceUI implements Initializable {

    @FXML
    private ComboBox<ServiceType> comboServType;
    @FXML
    private TextField txtID;
    @FXML
    private TextField txtShort;
    @FXML
    private TextField txtHourlyCost;
    @FXML
    private TextArea txtFull;
    @FXML
    private ComboBox<Category> comboCat;
    @FXML
    private Button btnContinue;
    @FXML
    private Button btnBack;

    AuthenticationController authController;
    SpecifyServiceController specifyController = new SpecifyServiceController();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init(AuthenticationController authController, Stage window) {
        this.authController = authController;
        this.specifyController.addToListOfWindows(window);
        comboServType.getItems().setAll(this.specifyController.getServiceTypeList());
        comboCat.getItems().setAll(this.specifyController.getCategoryList());
    }

    @FXML
    private void actionContinue(ActionEvent event) {
        this.specifyController.setServiceType(comboServType.getValue().getServTypeId());
        this.specifyController.setCategory(this.comboCat.getValue().getCatID());
        this.specifyController.newService(txtID.getText(), txtShort.getText(), txtFull.getText(), Double.parseDouble(txtHourlyCost.getText()));
        if (this.specifyController.needsExtraData()) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/04.1 - MoreInfo.fxml"));
                Parent moreInfo = loader.load();

                Scene moreInfoScene = new Scene(moreInfo);
                SpecifyServiceMoreUI ui = loader.getController();

                Stage window = new Stage();
                window.setScene(moreInfoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.specifyController.addToListOfWindows(window);
                ui.init(this.authController, this.specifyController);
                window.showAndWait();
            } catch (IOException e) {
                System.out.println(e);
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/04.2 - SeeInfo.fxml"));
                Parent seeInfo = loader.load();

                Scene seeInfoScene = new Scene(seeInfo);
                SpecifyServiceInfoUI ui = loader.getController();

                Stage window = new Stage();
                window.setScene(seeInfoScene);
                window.getIcons().add(new Image("file:icon.png"));
                window.setTitle("Client Services Provider");
                this.specifyController.addToListOfWindows(window);
                ui.init(this.authController, this.specifyController);
                window.show();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void actionBack(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
