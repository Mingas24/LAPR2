package lapr.project.gpsd.model;

import java.util.List;
import java.util.Objects;

public class GeographicalArea {
    private int areaID; //Construtores precisam de ID
    final private String desig;
    final private double tCost;
    final private float radius;
    final private List<ZipCode> coverage;
    final private ZipCode zipCodeCenter;
    private ExternalService api;
    

    /**
     * Construtor used to specify a new geographical area in the application.
     * @param desig designacao da area (p exemplo Rua x)
     * @param tCost custo da deslocacao dentro dessa area
     * @param radius radius da area
     * @param zipCodeCenter codigo postal mais central da area
     * @param api
     */
    public GeographicalArea(String desig, double tCost, float radius, ZipCode zipCodeCenter, ExternalService api){
        this.desig = desig;
        this.radius = radius;
        this.tCost = tCost;
        this.zipCodeCenter = zipCodeCenter;
        this.api = api;
        this.coverage = api.obtainCoverage(zipCodeCenter,radius);
    }

    /**
     * Constructor used for loading geographical areas from a file.
     * @param desig
     * @param tCost
     * @param radius
     * @param coverage
     * @param zipCodeCenter
     */
    public GeographicalArea(String desig, double tCost, float radius, List<ZipCode> coverage, ZipCode zipCodeCenter){
        this.desig = desig;
        this.tCost = tCost;
        this.radius = radius;
        this.coverage = coverage;
        this.zipCodeCenter = zipCodeCenter;
    }

    public int getAreaID(){
        return areaID;
    }
    
    public boolean hasID(int areaID){
        return this.areaID==areaID;
    }

    public String getDesig(){
        return this.desig;
    }
    
    public boolean hasGeographicalArea(String area){
        return this.desig.equalsIgnoreCase(area);
    }
    
    public double getTransportationCost(){
        return this.tCost;
    }

    public float getRadius(){
        return this.radius;
    }
    
    public List<ZipCode> getCoverageList(){
        return this.coverage;
    }
    
    public ZipCode getCenterZipCode(){
        return this.zipCodeCenter;
    }
    
    @Override
    public String toString(){
        return String.format("%2d - %s (%s)", this.areaID, this.desig, this.zipCodeCenter);
    }
    
    @Override
    public boolean equals(Object o) {
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
        GeographicalArea obj = (GeographicalArea) o;
        return (Objects.equals(desig, obj.desig));
    }
}