package lapr.project.gpsd.registry;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.ServiceProviderApplication;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class ApplicationRegistry{
    private List<ServiceProviderApplication> applicationsList;
    
    /**
     * Constructor with the Applications list.
     * @param applicationsList - List of Applications.
     */
    public ApplicationRegistry(List<ServiceProviderApplication> applicationsList){
        this.applicationsList = applicationsList;
    }
    
    /**
     * Default constructor.
     */
    public ApplicationRegistry(){
        this.applicationsList = new ArrayList<>();
    }
    
    /**
     * Returns the list of Service Provider Applications.
     * @return 
     */
    public List<ServiceProviderApplication> getApplicationsList(){
        return applicationsList;
    }
    
    public void setApplicationsList(List<ServiceProviderApplication> applicationsList){
        this.applicationsList = applicationsList;
    }
    
    public ServiceProviderApplication getApplicationByTin(String tin) {
        for(ServiceProviderApplication app : this.applicationsList){
            if(app.hasTin(tin))
                return app;
        }
        return null;
    }
    
    public boolean validateApplication(ServiceProviderApplication appl) {
        return !((appl.getName() == null) || (appl.getTin() == null) || (appl.getTelephone() == null) || (appl.getEmail() == null) || (appl.getPostalAddress() == null) || (appl.getName().isEmpty()) || (appl.getTin().isEmpty()) || (appl.getTelephone().isEmpty()) || (appl.getEmail().isEmpty()));
    }

    public boolean registerApplication(ServiceProviderApplication appl) {
        if (validateApplication(appl))
            return this.addApplication(appl);
        return false;
    }
        
    /**
     * Adds the application to the application list.
     * @param appl
     * @return 
     */
    public boolean addApplication(ServiceProviderApplication appl){
        return this.applicationsList.add(appl);
    }
    
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicationRegistry that = (ApplicationRegistry) o;
        return applicationsList.equals(that.applicationsList);
    }
}