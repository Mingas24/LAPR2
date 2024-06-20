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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.AuthenticationController;

public class MenuClientUI implements Initializable {

    @FXML
    private Button btnExit;
    @FXML
    private Button btnRequestService;
    @FXML
    private Button btnAddPostalAddress;
    @FXML
    private Button btnDecidePeriod;
    @FXML
    private Button btnRateService;

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
    private void actionRequestService(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/06 - RequestServiceExecution.fxml"));
            Parent request = loader.load();

            Scene requestScene = new Scene(request);
            RequestServiceExecutionUI ui = loader.getController();

            Stage window = new Stage();
            window.setScene(requestScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void actionAddPostalAddress(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/07 - AddPostalAddress.fxml"));
            Parent addPAddress = loader.load();

            Scene addPAddressScene = new Scene(addPAddress);
            AssociatePostalAddressUI ui = loader.getController();

            Stage window = new Stage();
            window.setScene(addPAddressScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void actionDecidePeriod(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/11 - DecideOnProposedPeriod.fxml"));
            Parent decide = loader.load();

            Scene decideScene = new Scene(decide);
            DecideOnProposedPeriodUI ui = loader.getController();

            Stage window = new Stage();
            window.setScene(decideScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void actionRateService(ActionEvent event) {try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/14 - RatingServiceProvider.fxml"));
            Parent decide = loader.load();

            Scene decideScene = new Scene(decide);
            RatingServiceProviderUI ui = loader.getController();

            Stage window = new Stage();
            window.setScene(decideScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            ui.init(auth_controller, window);
            window.show();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @FXML
    private void actionExit(ActionEvent event) {
        this.auth_controller.doLogout();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
