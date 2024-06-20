package lapr.project.gpsd.model;

import lapr.project.gpsd.registry.CategoryRegistry;
import lapr.project.gpsd.registry.*;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.controller.Bootstrap;

/**
 *
 * @author Utilizador
 */
public class Company {
    private Logger LOGGER;

    final private String designation;
    final private String tin;
    final private int delay;
    final private int interval;
    final private ClientRegistry cliReg;
    private ApplicationRegistry appReg;
    private ServiceRequestRegistry servReqReg;
    private GeographicalAreaRegistry geoAreaReg;
    private CategoryRegistry catReg;
    private ServiceRegistry servReg;
    private ServiceProviderRegistry servProvReg;
    private ExternalService api;
    private WriterTypeRegistry wtr;
    private ExecutionOrderRegistry execOrdReg;
    private List<Double> serviceProviderMeanRatingList;
    private double entireCompanyServiceProviderMeanRating;
    final private ServiceTypeRegistry servTypeReg;
    private final AuthorizationFacade authorization;
    private final Set<Client> clientList;
    private final Set<Category> categoryList;
    private final Set<Service> serviceList;
    private final String className="lapr.project.gpsd.model.ExternalServiceAdapter";
    private final String taskInfo="Assign providers to requests";

    /**
     * Construtor que recebe os registos de todos os documentos da classe
     * Bootstrap
     *
     * @param strDesignation designacao da empresa
     * @param strTIN nif da empresa
     */
    public Company(String strDesignation, String strTIN, String delay, String interval) {
        this.delay=Integer.parseInt(delay);
        this.interval=Integer.parseInt(interval);
        this.designation = strDesignation;
        this.tin = strTIN;

        this.authorization = new AuthorizationFacade();

        this.clientList = new HashSet<>();
        this.categoryList = new HashSet<>();
        this.serviceList = new HashSet<>();
        this.cliReg = new ClientRegistry();
        this.catReg = new CategoryRegistry();
        this.servReg = new ServiceRegistry();
        this.geoAreaReg = new GeographicalAreaRegistry();
        this.servProvReg = new ServiceProviderRegistry();
        this.appReg = new ApplicationRegistry();
        this.servReqReg = new ServiceRequestRegistry();
        this.execOrdReg = new ExecutionOrderRegistry();
        this.servTypeReg = new ServiceTypeRegistry();
        this.wtr = new WriterTypeRegistry();
    }

    /**
     * Method to get the AuthorizationFacade
     *
     * @return this.authorization
     */
    public AuthorizationFacade getAuthorizationFacade() {
        return this.authorization;
    }

    // Clients:
    /**
     * Method to get the ClientRegistry
     *
     * @return this.cliReg
     */
    public ClientRegistry getClientRegistry() {
        return this.cliReg;
    }

    // Category:
    /**
     * Returns the Category Registry.
     *
     * @return catReg
     */
    public CategoryRegistry getCategoryRegistry() {
        return catReg;
    }

    public WriterTypeRegistry getWriterTypeRegistry(){
        return this.wtr;
    }
    
    /**
     * Return service type registry
     * @return service type registry
     */
    public ServiceTypeRegistry getServiceTypeRegistry() {
        return servTypeReg;
    }
    
    /**
     * Method to change the Category Registry
     *
     * @param catReg
     */
    public void setCategoryRegistry(CategoryRegistry catReg) {
        this.catReg = catReg;
    }

    // Service:

    /**
     * Returns the Service Registry
     *
     * @return servReg
     */
    public ServiceRegistry getServiceRegistry() {
        return servReg;
    }

    /**
     * Method to change the service registry
     *
     * @param servReg
     */
    public void setServiceRegistry(ServiceRegistry servReg) {
        this.servReg = servReg;
    }

    // Geographical Area:
    /**
     * Returns the Geographical Area Registry.
     *
     * @return geoAreaReg
     */
    public GeographicalAreaRegistry getGeographicalAreaRegistry() {
        return geoAreaReg;
    }

    /**
     * Method to change the Geographical Area Registry
     *
     * @param gaReg
     */
    public void setGeographicalAreaRegistry(GeographicalAreaRegistry gaReg) {
        this.geoAreaReg = gaReg;
    }

    // Service Provider:
    /**
     * Method to get the Service Provider Registry
     *
     * @return servProvReg
     */
    public ServiceProviderRegistry getServiceProviderRegistry() {
        return servProvReg;
    }

    /**
     * Method to change the Service Provider Registry
     *
     * @param servProvReg
     */
    public void setServiceProviderRegistry(ServiceProviderRegistry servProvReg) {
        this.servProvReg = servProvReg;
    }

    /**
     * Method that notifies the service provider that they have new execution
     * order
     *
     * @param servProvList
     */
    public void notifiesServiceProvider(List<ServiceProvider> servProvList) {
        //Notifies every service provider that they have new execution orders.
    }

    // Application:
    /**
     * Method to get the Application Registry
     *
     * @return this.appReg
     */
    public ApplicationRegistry getApplicationRegistry() {
        return this.appReg;
    }

    /**
     * Method to chance the Application Registry
     *
     * @param appReg
     */
    public void setApplicationRegistry(ApplicationRegistry appReg) {
        this.appReg = appReg;
    }

    /**
     * Creates a new application
     *
     * @param strName
     * @param strTIN
     * @param strTelephone
     * @param strEmail
     * @param addr1
     * @return new ServiceProviderApplication(strName, strTIN, strTelephone,
     * strEmail, addr1)
     */
    public ServiceProviderApplication newApplication(String strName, String strTIN, String strTelephone, String strEmail, PostalAddress addr1) {
        return new ServiceProviderApplication(strName, strTIN, strTelephone, strEmail, addr1);
    }

    // Service Request
    /**
     * Retuns the Service Request Registry
     *
     * @return servReqReg
     */
    public ServiceRequestRegistry getServiceRequestRegistry() {
        return this.servReqReg;
    }

    /**
     * Method to change the service request registry
     *
     * @param servReqReg
     */
    public void setServiceRequestRegistry(ServiceRequestRegistry servReqReg) {
        this.servReqReg = servReqReg;
    }

    // Execution Order:
    /**
     * Method to get an Execution Order Registry
     *
     * @return execOrdReg
     */
    public ExecutionOrderRegistry getExecutionOrderRegistry() {
        return this.execOrdReg;
    }

    /**
     * Method to change an Execution Order Registry
     *
     * @param execOrdReg
     */
    public void setExecutionOrderRegistry(ExecutionOrderRegistry execOrdReg) {
        this.execOrdReg = execOrdReg;
    }

    public List<Double> getServiceProviderMeanRatingList() {
        return serviceProviderMeanRatingList;
    }

    public void setServiceProviderMeanRatingList(List<Double> serviceProviderMeanRatingList) {
        this.serviceProviderMeanRatingList = serviceProviderMeanRatingList;
    }

    public double getEntireCompanyServiceProviderMeanRating() {
        return entireCompanyServiceProviderMeanRating;
    }

    public void setEntireCompanyServiceProviderMeanRating(double entireCompanyServiceProviderMeanRating) {
        this.entireCompanyServiceProviderMeanRating = entireCompanyServiceProviderMeanRating;
    }
    
    
    
    

    /**
     * Method to get an External Service
     *
     * @return api
     */
    public ExternalService getApi() {
        try {
            Class<?> adapterType = Class.forName(this.className);
            try {
                this.api = (ExternalService) adapterType.newInstance();
            }catch (IllegalAccessException e){
                LOGGER.log(Level.SEVERE, "Illegal access exception found. Contact support.");
                e.printStackTrace();
            }catch (InstantiationException e){
                LOGGER.log(Level.SEVERE, "Instantiation exception found. Contact support.");
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e){
            LOGGER.log(Level.SEVERE, "Class '" + className + "' not found, please contact support.");
            e.printStackTrace();
        }
        return api;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return delay == company.delay &&
                interval == company.interval &&
                Double.compare(company.entireCompanyServiceProviderMeanRating, entireCompanyServiceProviderMeanRating) == 0 &&
                LOGGER.equals(company.LOGGER) &&
                designation.equals(company.designation) &&
                tin.equals(company.tin) &&
                cliReg.equals(company.cliReg) &&
                appReg.equals(company.appReg) &&
                servReqReg.equals(company.servReqReg) &&
                geoAreaReg.equals(company.geoAreaReg) &&
                catReg.equals(company.catReg) &&
                servReg.equals(company.servReg) &&
                servProvReg.equals(company.servProvReg) &&
                api.equals(company.api) &&
                execOrdReg.equals(company.execOrdReg) &&
                serviceProviderMeanRatingList.equals(company.serviceProviderMeanRatingList) &&
                servTypeReg.equals(company.servTypeReg) &&
                authorization.equals(company.authorization) &&
                clientList.equals(company.clientList) &&
                categoryList.equals(company.categoryList) &&
                serviceList.equals(company.serviceList) &&
                className.equals(company.className) &&
                taskInfo.equals(company.taskInfo);
    }

    @Override
    public int hashCode() {
        return 0;
    }

    /**
     * Validates the Service Provider Application
     *
     * @param appl
     * @return true if validate, false if not validate.
     */
    public boolean validateApplication(ServiceProviderApplication appl) {
        return !((appl.getName() == null) || (appl.getTin() == null) || (appl.getTelephone() == null) || (appl.getEmail() == null) || (appl.getPostalAddress() == null) || (appl.getName().isEmpty()) || (appl.getTin().isEmpty()) || (appl.getTelephone().isEmpty()) || (appl.getEmail().isEmpty()));
    }

    /**
     * Registers the Service Provider Application.
     *
     * @param appl
     * @return this.addApplication(appl) if validateApplication(appl) is true
     * and return false if validateApplication(appl) is false
     */
    public boolean registerApplication(ServiceProviderApplication appl) {
        if (validateApplication(appl)) {
            return this.addApplication(appl);
        }
        return false;
    }

    /**
     * Adds hte Service provider Applcation to the Company's Application
     * Registry.
     *
     * @param appl
     * @return this.appReg.addApplication(appl)
     */
    public boolean addApplication(ServiceProviderApplication appl) {
        return this.appReg.addApplication(appl);
    }

    public int getDelay() {
        return delay;
    }

    public int getInterval() {
        return interval;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public List<SchedulingAlgorithm> getAlgorithmList(){
        List<SchedulingAlgorithm> list = new ArrayList<>();
        list.add(new FirstComeFirstServe());
        list.add(new RandomScheduling());
        return list;
    }
}
