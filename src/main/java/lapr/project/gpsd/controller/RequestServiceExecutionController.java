package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PreferedSchedule;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.registry.ClientRegistry;
import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.registry.ServiceRegistry;
import lapr.project.gpsd.registry.ServiceRequestRegistry;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class RequestServiceExecutionController {

    private Client cli;
    private Company com;
    private ServiceRequest sReq;
    private ServiceRequestRegistry servReqReg;
    private CategoryRegistry catReg;
    private ServiceRegistry servReg;
    private GeographicalAreaRegistry geoAreReg;
    private GeographicalArea geoAre;
    private List<Stage> windows;

    /**
     * Empty constructor
     */
    public RequestServiceExecutionController() {
        if (!ApplicationGPSD.getInstance().getPresentSession().isLoggedInWithRole(Constants.ROLE_CLIENT)) {
            throw new IllegalStateException("Unauthorized User!");
        }
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Iniciates the request, returns a list with the client's postal addresses
     *
     * @return list with the client's postal addresses
     */
    public List<PostalAddress> newRequest() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        UserSession session = app.getPresentSession();
        String email = session.getUserEmail();
        ClientRegistry cr = this.com.getClientRegistry();
        this.cli = cr.getClientByEmail(email);
        return this.cli.getPostalAddresses();
    }

    /**
     * Defines the address where the service will be provided
     *
     * @param postalAddress
     */
    public void setPostalAddress(PostalAddress postalAddress) {
        this.servReqReg = com.getServiceRequestRegistry();
        this.sReq = this.servReqReg.newRequest(cli, postalAddress);

    }

    /**
     * Returns the list of categories.
     *
     * @return
     */
    public List<Category> getCategoryList() {
        this.catReg = com.getCategoryRegistry();
        return this.catReg.getCategoryList();
    }

    /**
     * Returns the service introducing the id of a category.
     *
     * @param idCat
     * @return
     */
    public List<Service> getServicesOfCat(String idCat) {
        this.servReg = com.getServiceRegistry();
        return this.servReg.getServicesOfCategory(idCat);
    }

    /**
     * Adds a service to the Service Request.
     *
     * @param idServ
     * @param desc
     * @param duration
     */
    public boolean addServiceRequest(String idServ, String desc, Time duration) {
        Service serv = this.servReg.getServiceById(idServ);
        return this.sReq.addServiceDescription(serv, desc, duration);
    }

    /**
     * Adds a preferable schedule
     *
     * @param date
     * @param time
     */
    public void addPreferedSchedule(Date date, Time time) {
        this.sReq.addSchedule(date, time);
    }
    
    public List<PreferedSchedule> getPreferedSchedule(){
        return this.sReq.getSchedules();
    }

    /**
     * Validates the Service Request.
     *
     * @return
     */
    public boolean validateRequest() {
        geoAreReg = this.com.getGeographicalAreaRegistry();
        return this.servReqReg.validateRequest(this.sReq, this.geoAreReg);
    }

    /**
     * Retuns the Total Cost.
     *
     * @return
     */
    public double getTotalCost() {
        return this.sReq.getCost();
    }

    /**
     * Registers the Service Request on the company.
     *
     * @return Service Request number.
     */
    public int registerRequest() {
        return this.servReqReg.registerRequest(sReq, geoAreReg);
    }
    
    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
    
    public List<Time> getWorkingHours() {
        return new ArrayList<Time>() {
            {
                add(new Time(6, 00));
                add(new Time(6, 30));
                add(new Time(7, 00));
                add(new Time(7, 30));
                add(new Time(8, 00));
                add(new Time(8, 30));
                add(new Time(9, 00));
                add(new Time(9, 30));
                add(new Time(10, 00));
                add(new Time(10, 30));
                add(new Time(11, 00));
                add(new Time(11, 30));
                add(new Time(12, 00));
                add(new Time(12, 30));
                add(new Time(13, 00));
                add(new Time(13, 30));
                add(new Time(14, 00));
                add(new Time(14, 30));
                add(new Time(15, 00));
                add(new Time(15, 30));
                add(new Time(16, 00));
                add(new Time(16, 30));
                add(new Time(17, 00));
                add(new Time(17, 30));
                add(new Time(18, 00));
                add(new Time(18, 30));
                add(new Time(19, 00));
                add(new Time(19, 30));
                add(new Time(20, 00));
                add(new Time(20, 30));
                add(new Time(21, 00));
                add(new Time(21, 30));
                add(new Time(22, 00));
                add(new Time(22, 30));
                add(new Time(23, 00));
                add(new Time(23, 30));
            }
        };
    }
    @Override
    public String toString(){
        return String.format(this.sReq.toString());
    }
}