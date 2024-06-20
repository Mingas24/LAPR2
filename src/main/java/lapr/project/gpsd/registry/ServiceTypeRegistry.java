/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.registry;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.ServiceType;

/**
 *
 * @author Jee ^^
 */
public class ServiceTypeRegistry {
    
    private List<ServiceType> serviceTypeList;

    /**
     * Constructor.
     * @param servicesTypeList
     */
    public ServiceTypeRegistry(List<ServiceType> servicesTypeList){
        this.serviceTypeList =new ArrayList<>(servicesTypeList);
    }
    
    /**
     * Default constructor
     */
    public ServiceTypeRegistry(){
        this.serviceTypeList = new ArrayList<>();
    }   
    
    /**
     * Obtains the list of all service types
     * @return list with all the service types
     */
    public List<ServiceType> getServiceTypeList(){
        return this.serviceTypeList;
    }
    
    /**
     * Regists a new service type
     * @param st service type
     * @return true if its registered
     */
    public boolean registerServiceType(ServiceType st){
        if(validateServiceType(st))
            return addServiceType(st);
        return false;
    }
    
    /**
     * Validates a service type
     * @param st servite type
     * @return true if its valid
     */
    private boolean validateServiceType(ServiceType st){
        //validates service type
        return true;
    }
    
    /**
     * Adds a service type
     * @param st service type to add
     * @return  true if its added sucessfully
     */
    private boolean addServiceType(ServiceType st){
        return this.serviceTypeList.add(st);
    }
       
    public ServiceType getServiceTypeById(String id){
        for(ServiceType servType: serviceTypeList){
            if(servType.hasID(id))
                return servType;
        }
        return null;
    }
}
