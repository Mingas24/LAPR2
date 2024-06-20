package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

public class ServiceRequest implements Comparable<ServiceRequest> {
    private int number;
    private Date date;
    private double cost;
    private Client client;
    private PostalAddress postalAddress;
    private GeographicalArea geoAre;
    private List<ServiceDescription> descriptions;
    private List<PreferedSchedule> schedules;
    private List<OtherCost> otherCosts;
    private List<AssignedService> assignedServices; //

    private static double COST_DEFAULT = 0;
    private static Date DATE_DEFAULT = new Date();
    private static GeographicalArea GEOGRAPHICALAREA_DEFAULT = null;

    /**
     * Construtor
     * @param client - The client that requests the Service.
     * @param postalAddress - Postal Address of the request.
     */
    public ServiceRequest(Client client, PostalAddress postalAddress){
        this.number = -1;
        this.client = client;
        this.postalAddress = postalAddress;
        this.date = DATE_DEFAULT;
        this.cost = COST_DEFAULT;
        this.geoAre = GEOGRAPHICALAREA_DEFAULT;
        this.descriptions = new ArrayList<>();
        this.schedules = new ArrayList<>();
        this.otherCosts = new ArrayList<>();
        this.assignedServices = new ArrayList<>();
    }
    
    /**
     * Construtor
     * @param number
     * @param date
     * @param client - The client that requests the Service.
     * @param postalAddress - Postal Address of the request.
     * @param schedules
     */
    public ServiceRequest(int number, Date date, Client client, PostalAddress postalAddress, List<PreferedSchedule> schedules){
        this.number = number;
        this.client = client;
        this.postalAddress = postalAddress;
        this.date = date;
        this.cost = COST_DEFAULT;
        this.geoAre = GEOGRAPHICALAREA_DEFAULT;
        this.descriptions = new ArrayList<>();
        this.schedules = new ArrayList<>(schedules);
        this.otherCosts = new ArrayList<>();
        this.assignedServices = new ArrayList<>();
    }

    public int getNumber(){
        return this.number;
    }

    public void setNumber(int number){
        this.number = number;
    }

    public Date getDate(){
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public GeographicalArea getGeoAre() {
        return geoAre;
    }

    public void setGeoAre(GeographicalArea geoAre) {
        this.geoAre = geoAre;
    }
    
    /**
     * Verifies if it isn't introduced an equal description.
     * @param description
     * @return
     */
    public boolean validatesServiceRequest(ServiceDescription description){
        Time duration = description.getDuration();
        return !(duration.getMinutos()%30!=0 || (duration.getHours()==0 && duration.getMinutos()==0));
    }
  
    /**
     * Adds a description to the request.
     * @param descAdd
     * @return 
     */
    public boolean addServiceDescription(ServiceDescription descAdd){
        return descriptions.add(descAdd);
    }
    
        /**
     * Adds a description to the request.
     * @param serv
     * @param desc
     * @param duration
     * @return 
     */
    public boolean addServiceDescription(Service serv, String desc, Time duration){
        ServiceDescription descrip = new ServiceDescription(desc, duration, serv);
        if(!validatesServiceRequest(descrip))
            return this.addServiceDescription(descrip);
        return false;
    }

    /**
     * Creates the number to the Service Request.
     * @return - Number of Schedules.
     */
    private int numberOfSchedules(){
        return schedules.size();
    }
    
    /**
     * Adds a Schedule to the Request.
     * @param date
     * @param time
     * @return 
     */
    public boolean addSchedule(Date date, Time time){
        int order = numberOfSchedules();
        PreferedSchedule sche = new PreferedSchedule(order, date, time);
        if(validateSchedule(sche))
            return this.addSchedule(sche);
        return false;
    }

    /**
     * Validates the given Schedule.
     * @param sche
     * @return
     */
    private boolean validateSchedule(PreferedSchedule sche){
        if(!this.schedules.contains(sche)) {
            if (sche.getTime().getHours() > 6) {
                return true;
            } else {
                return sche.getTime().getHours() == 6 && (sche.getTime().getMinutos() > 0 || sche.getTime().getSegundos() > 0);
            }
        }else {
            return false;
        }
    }

    /**
     * Adds a Schedule to the Request.
     * @param sche
     * @return 
     */
    public boolean addSchedule(PreferedSchedule sche){
        return schedules.add(sche);
    }
    
    /**
     * Adds an assigned service to the Request
     * @param assignedService
     * @return 
     */
    public boolean addAssignedService(AssignedService assignedService){
        return this.assignedServices.add(assignedService);
    }

    /**
     * Calculate the cost of the request.
     * @param geoAreReg
     * @return
     */
    public double calculateCost(GeographicalAreaRegistry geoAreReg){
        double c = 0;
        for(ServiceDescription servDesc : this.descriptions){
            c = c + servDesc.getCost();
        }
        this.otherCosts.clear();
        ZipCode zipCode = this.postalAddress.getZipCode();
        GeographicalArea geographAre = geoAreReg.getClosestGeographicalArea(zipCode);
        setGeoAre(geographAre);
        double transportationCost = geographAre.getTransportationCost();
        OtherCost oc = new OtherCost("Transportation", transportationCost);
        this.otherCosts.add(oc);
        c = c + transportationCost;
        return this.cost=c;
    }

    public double getTotalCost(){
        return this.cost;
    }

    @Override
    public String toString() {
        String result = "";
        result += "Postal Address - " + getPostalAddress().toString();
        result += "\n";
        for (PreferedSchedule sche: schedules){
            result += "\n";
            result += sche.toString();
        }
        
        result += "\n";
        for (ServiceDescription desc: descriptions){
            result += "\n";
            result += desc.toString();
        }
        for (OtherCost oc: otherCosts){
            result += "\n";
            result += oc.toString();
        }
        result += "\n";
        result += "Total Cost: " + this.cost;
        return String.format(result);
    }

    public List<ServiceDescription> getDescription() {
        return descriptions;
    }
    
    public void setDescription(List<ServiceDescription> description) {
        this.descriptions = description;
    }

    public void setSchedules(List<PreferedSchedule> schedules) {
        this.schedules = schedules;
    }

    public void setOtherCosts(List<OtherCost> otherCosts) {
        this.otherCosts = otherCosts;
    }
    
    public void setAssignedServices(List<AssignedService> assignedServices) {
        this.assignedServices = assignedServices;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PostalAddress getPostalAddress() {
        return postalAddress;
    }
    
    public List<AssignedService> getAssignedServices() {
        return this.assignedServices;
    }

    public void setPostalAddress(PostalAddress postalAddress) {
        this.postalAddress = postalAddress;
    }

    public List<PreferedSchedule> getSchedules() {
        return this.schedules;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceRequest that = (ServiceRequest) o;
        return number == that.number &&
               Double.compare(that.cost, cost) == 0 &&
               date.equals(that.date) &&
               client.equals(that.client) &&
               postalAddress.equals(that.postalAddress) &&
               descriptions.equals(that.descriptions) &&
               schedules.equals(that.schedules) &&
               otherCosts.equals(that.otherCosts);
    }
    
    public boolean hasClient(Client cli) {
        return this.client.equals(cli);
    }

    @Override
    public int compareTo(ServiceRequest o) {
        return this.date.compareTo(o.getDate());
    }
}