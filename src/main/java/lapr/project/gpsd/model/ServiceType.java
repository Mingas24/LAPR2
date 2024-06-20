/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jee ^^
 */
public class ServiceType{
    
    private String designation;
    private String servTypeId;
    private String path;
    
    /**
     * Possible designations
     */
    private static String[] designations = {"Fixed", "Expandable",
        "Limited"};
    
    /**
     * Possible paths
     */
    private static String[] paths = {"lapr.project.gpsd.model.FixedService","lapr.project.gpsd.model.ExpandableService",
    "lapr.project.gpsd.model.LimitedService"};
    
    /**
     * Constructor
     * @param designation
     * @param servTypeId 
     */
    public ServiceType (String designation, String servTypeId){
        this.path = getPath(designation);
        this.servTypeId = servTypeId;
        this.designation = designation;                
    }

    public String getDesignation() {
        return designation;
    }

    public String getServTypeId() {
        return servTypeId;
    }
    
    /**
     * Obtains the path to create the class based on the designation
     * @param designation
     * @return path
     */
    public String getPath(String designation){
        for(int i = 0; i<designations.length; i++) {
            if (designations[i].equals(designation)) {
                return paths[i];
            }
        }
        throw new IllegalArgumentException("Invalid designation.");
    }
    
    /**
     * creates a new Service
     * @param id ID
     * @param sDesc short description
     * @param fDesc full description
     * @param cost hourly cost
     * @param cat category
     * @return created service
     */
    public Service newService(String id, String sDesc, String fDesc, double cost, Category cat){
        try {
            Class<?> oClass = Class.forName(this.path);
            Class[] argsClasses = new Class[] { String.class, String.class, String.class,
            double.class, Category.class, getClass()};
            Constructor constructor;
            constructor = oClass.getConstructor(argsClasses);
            Object[] argsValues = new Object[] { id, sDesc, fDesc, cost, cat, this };
            Service serv = (Service) constructor.newInstance(argsValues);
            return serv;
        } catch (Exception ex) {
            Logger.getLogger(ServiceType.class.getName()).log(Level.SEVERE, "Exception caught!", ex);
            ex.printStackTrace();
        }
        return null;
        
    }
    
    public boolean hasID(String id){
        return this.servTypeId.equals(id);
    }

    @Override
    public String toString() {
        return this.designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceType that = (ServiceType) o;
        return designation.equals(that.designation) &&
                servTypeId.equals(that.servTypeId) &&
                path.equals(that.path);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}