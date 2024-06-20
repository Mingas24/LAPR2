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
import lapr.project.gpsd.controller.SubmitApplicationController;

/**
 * FXML Controller class
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SubmitApplicationOptionsUI implements Initializable{

    @FXML
    private Button btnAddAcademic;
    @FXML
    private Button btnComplete;
    @FXML
    private Button btnAddProfessional;
    @FXML
    private Button btnCategory;
    
    SubmitApplicationController submitController;
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
    }    
    
    public void init(SubmitApplicationController submitController){
        this.submitController = submitController;
    }

    @FXML
    private void actionCategory(ActionEvent event){
        try{
            FXMLLoader loader2 = new FXMLLoader();
            loader2.setLocation(getClass().getResource("/fxml/02.2 - AddCategory.fxml"));
            Parent category = loader2.load();
                
            Scene categoryScene = new Scene(category);
            AddCategoryUI ui = loader2.getController();    
            ui.init(this.submitController);
                
            Stage window = new Stage();
            window.setScene(categoryScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            window.show();
        }catch(IOException e){
            System.out.println("Error in FXML File.");
        }
    }
    
    @FXML
    private void actionAddAcademic(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/02.3 - AddAcademicQualification.fxml"));
            Parent academic = loader.load();
                
            Scene academicScene = new Scene(academic);
            AddAcademicQualificationUI ui = loader.getController();    
            ui.init(this.submitController);
                
            Stage window = new Stage();
            window.setScene(academicScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            window.show();
        }catch(IOException e){
            System.out.println("Error in FXML File.");
        }
    }
    
    @FXML
    private void actionAddProfessional(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/02.4 - AddProfessionalQualification.fxml"));
            Parent professional = loader.load();
                
            Scene professionalScene = new Scene(professional);
            AddProfessionalQualificationUI ui = loader.getController();    
            ui.init(this.submitController);
                
            Stage window = new Stage();
            window.setScene(professionalScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            window.show();
        }catch(IOException e){
            System.out.println("Error in FXML File.");
        }
    }

    @FXML
    private void actionComplete(ActionEvent event){
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/02.5 - Complete.fxml"));
            Parent complete = loader.load();
                
            Scene completeScene = new Scene(complete);
            SubmitApplicationCompleteUI ui = loader.getController();    
            ui.init(this.submitController);
                
            Stage window = new Stage();
            window.setScene(completeScene);
            window.getIcons().add(new Image("file:icon.png"));
            window.setTitle("Client Services Provider");
            this.submitController.addToListOfWindows(window);
            window.show();
        }catch(IOException e){
            System.out.println("Error in FXML File.");
        }
    }
}