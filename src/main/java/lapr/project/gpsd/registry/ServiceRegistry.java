package lapr.project.gpsd.registry;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Service;

public class ServiceRegistry {
    private List<Service> serviceList;

    /**
     * Constructor.
     * @param servicesList - List of every Registed Service.
     */
    public ServiceRegistry(List<Service> servicesList){
        this.serviceList = servicesList;
    }
    
    /**
     * Default constructor
     */
    public ServiceRegistry(){
        this.serviceList = new ArrayList<>();
    }    

    /**
     * Sets the List to the given one.
     * @param serviceList 
     */
    public void setServiceList(List<Service> serviceList){
        this.serviceList = serviceList;
    }

    /**
     * Returns the Service with the given ID.
     * @param idServ - Given ID.
     * @return - Service found.
     */
    public Service getServiceById(String idServ){
        for(Service serv: serviceList){
            if(serv.hasID(idServ))
                return serv;
        }
        return null;
    }

    /**
     * Returns every Service of a given Category.
     * @param catID - Category ID.
     * @return - List of every Service.
     */
    public List<Service> getServicesOfCategory(String catID){
        List<Service> servicesReturn = new ArrayList<>();
        for(Service serv: serviceList){
            Category cat = serv.getCategory();
            if(serv.getCategory().hasID(catID))
                servicesReturn.add(serv);
        }
        return servicesReturn;
    }
    
    public boolean registerService(Service serv){
        if(this.validateService(serv))
            return this.addService(serv);
        return false;
    }
    
    public boolean validateService(Service serv){
        //validates te serivce
        return true;
    }
    
    private boolean addService(Service serv){
        return this.serviceList.add(serv);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceRegistry that = (ServiceRegistry) o;
        return serviceList.equals(that.serviceList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceList);
    }
}