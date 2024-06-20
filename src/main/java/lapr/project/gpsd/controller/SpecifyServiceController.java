/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.FixedService;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.ServiceType;
import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.registry.ServiceRegistry;
import lapr.project.gpsd.registry.ServiceTypeRegistry;

/**
 *
 * @author José Araújo
 */
public class SpecifyServiceController {

    private Company com;
    private ServiceTypeRegistry str;
    private ServiceType st;
    private CategoryRegistry cr;
    private Category cat;
    private Service serv;
    private ServiceRegistry serviceReg;
    private List<Stage> windows;

    /**
     * Constructor
     */
    public SpecifyServiceController() {
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Obtains all service types registered
     *
     * @return list with all the service types
     */
    public List<ServiceType> getServiceTypeList() {
        str = this.com.getServiceTypeRegistry();
        return str.getServiceTypeList();
    }

    /**
     * Sets the service type based on the id
     *
     * @param id service type id
     */
    public void setServiceType(String id) {
        st = str.getServiceTypeById(id);
    }

    /**
     * Obtains all categories registed
     *
     * @return list with all the categories
     */
    public List<Category> getCategoryList() {
        cr = com.getCategoryRegistry();
        return cr.getCategoryList();
    }

    /**
     * Sets the category based on the id
     *
     * @param id category id
     */
    public void setCategory(String id) {
        cat = cr.getCategoryByID(id);
    }

    /**
     * Builds the service
     *
     * @param id service id
     * @param sDesc service short description
     * @param fDesc service full description
     * @param cost hourly cost
     */
    public void newService(String id, String sDesc, String fDesc, double cost) {
        serv = st.newService(id, sDesc, fDesc, cost, cat);
    }

    /**
     * checks if the service needs any extra data
     *
     * @return true if it does
     */
    public boolean needsExtraData() {
        return serv.needsExtraData();
    }

    /**
     * gets a string that says what extra data is needed and asking for it
     *
     * @return string with the extra needed data
     */
    public String getExtraData() {
        return serv.getOtherAtributes();
    }

    /**
     * Sets the extra necessary data
     *
     * @param data needed data
     */
    public void setExtraData(String data) {
        serv.setExtraData(data);
    }

    /**
     * validates de service
     *
     * @return true if its valid
     */
    public boolean validate() {
        serviceReg = com.getServiceRegistry();
        return serviceReg.validateService(serv);
    }

    /**
     * gets the service info to confirmation
     *
     * @return service info
     */
    public String getServiceInfo() {
        return serv.toString();
    }

    /**
     * regists the service
     *
     * @return true if its sucessfully registered
     */
    public boolean registerService() {
        return serviceReg.registerService(serv);
    }
    
//    public void saveInFile(){
//        this.serviceReg.saveInFile();
//    }

    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
    
    @Override
    public String toString(){
        if (this.st.getDesignation().equals("Fixed"))
            return String.format("Service Type = %s%nCategory = %s%nID = %s%nShort Description = %s%nFull Description = %s%nHourly Cost = %s%nDuration = %s%n", this.st, this.cat, this.serv.getID(), this.serv.getStrShortDescription(), this.serv.getStrFullDescription(), this.serv.getHourlyCost(), ((FixedService)this.serv).getDuration().toStringHHMM());
        else
            return String.format("Service Type = %s%nCategory = %s%nID = %s%nShort Description = %s%nFull Description = %s%nHourly Cost = %s%n", this.st, this.cat, this.serv.getID(), this.serv.getStrShortDescription(), this.serv.getStrFullDescription(), this.serv.getHourlyCost());
    }
}
