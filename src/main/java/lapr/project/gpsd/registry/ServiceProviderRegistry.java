package lapr.project.gpsd.registry;

import java.util.ArrayList;
import java.util.List;
import lapr.project.authorization.AuthorizationFacade;

import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.PreferedSchedule;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceProviderApplication;

public class ServiceProviderRegistry{
    private List<ServiceProvider> servProviderList;

    /**
     * Constructor.
     * @param servProvidersList - List of registered Service Providers.
     */
    public ServiceProviderRegistry (List<ServiceProvider> servProvidersList){
        this.servProviderList = servProvidersList;
    }
    
    /**
     * Default constructor
     */
    public ServiceProviderRegistry(){
        this.servProviderList = new ArrayList<>();
    }
    
    /**
     * Returns the list of Service Providers.
     * @return
     */
    public List<ServiceProvider> getServiceProviderList(){
        return servProviderList;
    }
    
    public void setServiceProviderList(List<ServiceProvider> servProviderList){
        this.servProviderList = servProviderList;
    }

    /**
     * Returns the Service Provider with the given email.
     * @param email - Given email.
     * @return - Service Provider.
     */
    public ServiceProvider getServiceProviderByEmail(String email){
        for(ServiceProvider servPro: servProviderList){
            if(servPro.hasEmail(email))
                return servPro;
        }
        return null;
    }

    /**
     * Returns a list of the Service Providers with a matching schedule to the given one.
     * @param requestedServicesSchedule - List with all the preferes schedules of the client
     * @return - List of Service Providers.
     */
    public List<ServiceProvider> getCompatibleServiceProviders(List<PreferedSchedule> requestedServicesSchedule) {
        List<ServiceProvider> compatiblesServiceProviders = new ArrayList<>();

        for (PreferedSchedule ps : requestedServicesSchedule){

            for(ServiceProvider sp : this.servProviderList)
                if(sp.isCompatible(ps))
                    compatiblesServiceProviders.add(sp);
        }
        return compatiblesServiceProviders;
    }   

    /**
     * Generates a ServiceProvider with the usage of a service provider application
     * @param application
     * @return service provider generated
     */
    public ServiceProvider newServiceProvider(ServiceProviderApplication application) {
        String name = application.getName().trim();
        String abrevName = getAbrevName(name);
        String tin = application.getTin();
        String mechNumber = generateMechNumber();
        String tel = application.getTelephone();
        String email = application.getEmail();
        PostalAddress postAddr = application.getPostalAddress();
        List<Category> cl = application.getCatList();
        return new ServiceProvider(name, abrevName, tin, mechNumber, tel, email, postAddr, cl);
    }
    
    /**
     * Given a name, this method generates an abreviated name using the first and last name only
     * @param name
     * @return abreviated name
     */
    private String getAbrevName(String name){
        if(name.indexOf(" ")!=-1){
            String firstLetter = name.substring(0,1);
            String lastName = name.substring(name.lastIndexOf(" "));
            return firstLetter+lastName;
        }
        return name;
    }

    /**
     * generates a mechanical number to Service provider
     * @return generated mechanical number
     */
    private String generateMechNumber() {
        return String.format("%05d", this.servProviderList.size());
    }

    /**
     * Regists the service provider and the user
     * @param sp serice provider
     * @param af authorization facade to save the user
     * @return true if it is added sucessfully
     */
    public boolean registerServiceProvider(ServiceProvider sp, AuthorizationFacade af) {
        if(this.validateServiceProvider(sp))
            if(this.addServiceProvider(sp)){
                String name = sp.getName();
                String email = sp.getEmail();
                String pwd = generatePwd();
                af.registerUserWithRole(name, email, pwd, Constants.ROLE_SERVICE_PROVIDER);
                return true;
            }
        return false;
    }
    
    /**
     * Validetes a service provider
     * @param sp service provider to validate
     * @return true if he is valid
     */
    private boolean validateServiceProvider(ServiceProvider sp){
        //Validates
        return true;
    }
    
    /**
     * Generates a random pwd for the user
     * @return random pwd
     */
    private String generatePwd() {
        //Generates random password
        return "123456";
    }
    
    /**
     * Adds a service provider to the list
     * @param sp service provider to add
     * @return true if it is added sucessfully
     */
    private boolean addServiceProvider(ServiceProvider sp){
        return this.servProviderList.add(sp);
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceProviderRegistry that = (ServiceProviderRegistry) o;
        return servProviderList.equals(that.servProviderList);
    }
}