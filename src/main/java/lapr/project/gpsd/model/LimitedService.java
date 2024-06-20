/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.Objects;

/**
 *
 * @author Jee ^^
 */
public class LimitedService implements Service{
    private String strID;
    private String strShortDescription;
    private String strFullDescription;
    private double hourlyCost;
    private Category category;
    private final boolean OTHER_ATRIBUTES_FLAG = false;
    private final String OTHER_ATRIBUTES = "No Other Atributes";
    private ServiceType servType;
    
    /**
     * Constructor.
     * @param strID - ID of the Service.
     * @param strShortDescription
     * @param strFullDescription
     * @param hourlyCost - Cost by the Hour.
     * @param category - Service category.
     * @param st - Service's type
     */
    public LimitedService(String strID, String strShortDescription, String strFullDescription, double hourlyCost, Category category, ServiceType st){
        if((strID == null) || (strShortDescription == null) || (strFullDescription == null) ||
           (hourlyCost < 0) || (category == null) ||
           (strID.isEmpty()) || (strShortDescription.isEmpty()) || (strFullDescription.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        
        this.strID = strID;
        this.strShortDescription = strShortDescription;
        this.strFullDescription = strFullDescription;
        this.hourlyCost = hourlyCost;
        this.category = category;
        this.servType = st;
    }
    
    @Override
    public boolean hasID(String strId) {
        return this.strID.equalsIgnoreCase(strId);
    }

    @Override
    public String getID() {
        return this.strID;
    }

    @Override
    public String getStrShortDescription() {
        return strShortDescription;
    }

    @Override
    public String getStrFullDescription() {
        return strFullDescription;
    }

    @Override
    public Category getCategory() {
        return this.category;
    }

    @Override
    public double getHourlyCost() {
        return this.hourlyCost;
    }

    @Override
    public boolean needsExtraData() {
        return OTHER_ATRIBUTES_FLAG;
    }

    @Override
    public String getOtherAtributes() {
        return OTHER_ATRIBUTES;
    }

    @Override
    public ServiceType getServiceType() {
        return this.servType;
    }

    @Override
    public void setExtraData(String data) {
        //overridden method
    }
    
    @Override
    public boolean equals(Object o){
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/
        
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        LimitedService obj = (LimitedService) o;
        return (Objects.equals(strID, obj.strID));
    }
    
    @Override
    public String toString(){
        return this.strID + " - " + this.strShortDescription + " - " + this.strFullDescription + " - " + this.hourlyCost + " - Category: " + this.category.toString();
    }

    @Override
    public boolean hasId(String Id) {
        return this.strID.equals(Id);
    }
}