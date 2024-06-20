package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceProviderApplication;
import lapr.project.gpsd.registry.ApplicationRegistry;
import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;

/**
 *
 * @author José Araújo
 */
public class RegisterServiceProviderController {
    
    private Company company;
    private GeographicalAreaRegistry geoAreaReg;
    private CategoryRegistry catReg;
    private ServiceProvider servProv;
    private ServiceProviderRegistry servProvReg;
    private ApplicationRegistry appReg;
    private ServiceProviderApplication servProvApp;
    private List<Stage> windows;
    
    /**
     * Constructor
     */
    public RegisterServiceProviderController() {
        if(!ApplicationGPSD.getInstance().getPresentSession().isLoggedInWithRole(Constants.ROLE_HRO))
            throw new IllegalStateException("Unauthorized User!");
        this.company = ApplicationGPSD.getInstance().getCompany();
        this.appReg = this.company.getApplicationRegistry();
        this.servProvReg = this.company.getServiceProviderRegistry();
        this.catReg = this.company.getCategoryRegistry();
        this.geoAreaReg = this.company.getGeographicalAreaRegistry();
        this.windows = new ArrayList<>();
    }

    public ServiceProvider getServProv() {
        return servProv;
    }
    
    /**
     * Generates a service provider given a tin corresponding to one of the service provider application and returns it.
     * @param tin
     * @return Boolean of existence
     */
    public boolean existServiceProviderByTin(String tin){
        this.servProvApp = this.appReg.getApplicationByTin(tin);
        this.servProv = this.servProvReg.newServiceProvider(this.servProvApp);
        if (this.servProvApp == null)
            return false;
        else
            return true;
    }
    
    /**
     * Obtains a list with all the geographical areas registered to add to the service provider
     * @return list with all the geograplhical areas
     */
    public List<GeographicalArea> getGeographicalAreasList() {
        return this.geoAreaReg.getGeographicalAreaList();
    }
    
    public List<Category> getCategoryList(){
        return this.catReg.getCategoryList();
    }
    
    /**
     * Adds a geographical area to the list of the service provider
     * @param agID id of the geographical area
     * @return true if its sucessfully added
     */
    public boolean addGeographicalArea(int agID) {
        GeographicalArea ga = geoAreaReg.getGeographicalAreaByID(agID);
        return servProv.addGeographicalArea(ga);
    }
    
    /**
     * obtains all the info the user needs to see to be able to confirm or not the registry of the service provider
     * @return String with all the necessary information
     */
    public String getInfoToConfirmation(){
        return servProv.toString();
    }
    
    /**
     * Regists the service provider and the user
     * @return true if it is sucessfully registered
     */
    public boolean registerServiceProvider() {
        AuthorizationFacade af = this.company.getAuthorizationFacade();
        return this.servProvReg.registerServiceProvider(servProv, af);
    }
    
    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
}
