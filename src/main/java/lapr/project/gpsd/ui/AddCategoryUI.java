package lapr.project.gpsd.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import lapr.project.gpsd.controller.SubmitApplicationController;
import lapr.project.gpsd.model.Category;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class AddCategoryUI implements Initializable {

    @FXML
    private Button btnAddCategory;
    @FXML
    private ComboBox<Category> comboCat;
    @FXML
    private Label lblX;
    
    SubmitApplicationController submitController;
    List<Category> categoryList = new ArrayList<>();

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    public void init(SubmitApplicationController submitController){
        this.submitController = submitController;
        this.categoryList = this.submitController.getCategoryList();
        comboCat.getItems().addAll(categoryList);
    }

    @FXML
    private void actionAddCategory(ActionEvent event){
        this.submitController.registerCategory(comboCat.getValue());
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}