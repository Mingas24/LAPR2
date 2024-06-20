package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.model.ZipCode;

public class SpecifyGeographicalAreaController {

    private GeographicalAreaRegistry gar;
    private Company com;
    private GeographicalArea ga;
    private List<Stage> windows;

    /**
     * Constructor that instanciates the company through the ApplicationGPSD
     * class.
     *
     */
    public SpecifyGeographicalAreaController() {
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    public List<ZipCode> getCoverageList() {
        return this.ga.getCoverageList();
    }

    public String getCoverageListToString() {
        String str = "";
        List<ZipCode> list = this.ga.getCoverageList();
        for (ZipCode zip : list) {
            str += zip.getZipCode() + "\n";
        }
        return str;
    }

    /**
     * Instantiates the desired area.
     *
     * @param designation - Area's designation.
     * @param zipCode - Area's central ZIPCode.
     * @param tCost - Area's transportation cost.
     * @param radius - Area's radius.
     * @return - Instantiated Geographical Area.
     */
    public GeographicalArea newArea(String designation, ZipCode zipCode, double tCost, float radius) {
        this.gar = com.getGeographicalAreaRegistry();
        gar.setApi(com.getApi());
        this.ga = gar.newArea(designation, zipCode, tCost, radius);
        return this.ga;
    }

    /**
     * Registers the previously created area
     *
     * @return Boolean true if everything goes as planned, false otherwise
     */
    public boolean registerArea() {
        return gar.registerGeographicalArea(this.ga);
    }
    
//    public void saveInFile(){
//        this.gar.saveInFileArea();
//        this.gar.saveInFileCoverage();
//    }

    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
}
