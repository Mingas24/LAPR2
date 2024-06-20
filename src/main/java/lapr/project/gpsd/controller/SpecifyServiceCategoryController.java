package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.utils.Utils;

public class SpecifyServiceCategoryController {

    private Company com;
    private CategoryRegistry catReg;
    private Category cat;
    private List<Stage> windows;

    /**
     * Constructor
     */
    public SpecifyServiceCategoryController() {
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Creates a new Category
     *
     * @param catCode category's catCode
     * @param desc category's description
     * @return true if its created and its valid
     */
    public boolean newCategory(String catCode, String desc) {
        try {
            catReg = com.getCategoryRegistry();
            cat = catReg.newCategory(catCode, desc);
            return catReg.validateCategory(cat);
        } catch (RuntimeException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, "Runtime exception ocurred.", ex);
            this.cat = null;
            return false;
        }
    }

    /**
     * regists the created Category
     *
     * @return true if its sucessfully registered
     */
    public boolean registerCategory() {
        return catReg.registerCategory(cat);
    }
//    public void saveInFile(){
//        this.catReg.saveInFile();
//    }

    /**
     * obtains all the info of the category in a string
     *
     * @return string with all the created category info
     */
    public String getCategoryString() {
        return cat.toString();
    }

    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }

    @Override
    public String toString() {
        return String.format("ID: %s%n"
                + "Description: %s", this.cat.getCatID(), this.cat.getDescription());
    }
}
