package lapr.project.gpsd.model;

public interface Service{

    /**
     * Searches for a Service with the same ID as the given one.
     * @param strId - Given ID.
     * @return - True if it's the same ID, False if it isn't.
     */
    public boolean hasID(String strId);

    /**
     * Obtains the service ID.
     * @return service id
     */
    public String getID();

    /**
     * Obtains the service short description.
     * @return 
     */
    public String getStrShortDescription();
            
    /**
     * Obtains the service full description.
     * @return 
     */
    public String getStrFullDescription();
    
    /**
     * Obtains the service's category
     * @return service's category
     */
    public Category getCategory();

    /**
     * Obtais the service's hourly cost
     * @return service's hourly cost
     */
    public double getHourlyCost();
    
    /**
     * Checks if the service has any addional attributes
     * @return true if it has
     */
    public boolean needsExtraData();
    
    /**
     * Checks which other attribute the service has
     * @return 
     */
    public String getOtherAtributes();
    
    /**
     * Obtais the service's type
     * @return service's type
     */
    public ServiceType getServiceType();
    
    /**
     * give a value to the additional data that the service has
     * @param data addiotional data
     */
    public void setExtraData(String data);
    
    /**
     * Checks if a service has ID
     * @param Id
     * @return true if it has
     */
    public boolean hasId(String Id);
}