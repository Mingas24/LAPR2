package lapr.project.gpsd.registry;

import java.util.ArrayList;
import lapr.project.gpsd.model.ExternalService;
import lapr.project.gpsd.model.GeographicalArea;

import java.util.List;
import java.util.Objects;
import lapr.project.gpsd.model.ZipCode;
import lapr.project.gpsd.utils.Utils;
import net.bytebuddy.implementation.bind.annotation.Default;

public class GeographicalAreaRegistry{

    private List<GeographicalArea> areasList;
    private ExternalService api;

    /**
     * Construtor
     * @param areasList lista de áreas registadas
     */
    public GeographicalAreaRegistry(List<GeographicalArea> areasList){
        this.areasList = areasList;
    }
    
    /**
     * Default constructor
     */
    public GeographicalAreaRegistry(){
        this.areasList = new ArrayList<>();
    }
    
    /**
     * Returns the Geographical Area list.
     * @return 
     */
    public List<GeographicalArea> getGeographicalAreaList(){
        return this.areasList;
    }
    
    public void setGeographicalAreaList(List<GeographicalArea> areasList){
        this.areasList = areasList;
    }
    
    /**
     * Returns the geographical area by introducing a area ID.
     * @param areaID
     * @return 
     */
    public GeographicalArea getGeographicalAreaByID(int areaID){
        for(GeographicalArea area: this.areasList){
            if(area.hasID(areaID)){
                return area;
            }
        }
        return null;
    }
    
    public GeographicalArea getGeographicalAreaByDesignation(String desig){
        for(GeographicalArea area: this.areasList){
            if(area.hasGeographicalArea(desig)){
                return area;
            }
        }
        return null;
    }

    /**
     * Returns the Geographical Area closer to a ZIP Code.
     * @param zipCode
     * @return
     */
    public GeographicalArea getClosestGeographicalArea(ZipCode zipCode){
        GeographicalArea areaReturner = this.areasList.get(0);
        GeographicalArea area = this.areasList.get(0);
        double lat1 = zipCode.getLatitude();
        double lat2 = area.getCenterZipCode().getLatitude();
        double lon1 = zipCode.getLongitude();
        double lon2 = area.getCenterZipCode().getLatitude();
        double min = Utils.distance(lat1, lon1, lat2, lon2);
        for(int i = 1; i < areasList.size(); i++){
            lat2 = area.getCenterZipCode().getLatitude();
            lon2 = area.getCenterZipCode().getLatitude();
            double dist = Utils.distance(lat1, lon1, lat2, lon2);
            if(dist < min){
                min = dist;
                areaReturner = area;
            }
        }
        return areaReturner;
    }

    /**
     * Instantiates a new geographical area
     * @param designation Area's designation
     * @param pAddress Area's postal address
     * @param tCost Transportation cost within the area
     * @param radius Area's radius
     * @return The instantiated geographical area
     */
    public GeographicalArea newArea(String designation, ZipCode pAddress, double tCost, float radius){
        return new GeographicalArea(designation, tCost, radius, pAddress, this.api);
    }

    public void setApi(ExternalService api) {
        this.api = api;
    }

    /**
     * Registers a geographical area in the system
     * @param ga Area to register
     * @return Boolean true if it passes the test and is added to the registry, false otherwise
     */
    public boolean registerGeographicalArea(GeographicalArea ga){
        if(validateArea(ga))
            return this.addArea(ga);
        else
            return false;
    }

    /**
     * Checks if the area isn't already in the registry, so that it can be added to it
     * @param ga Geographical area to check
     * @return Boolean true if it isn't in the registry, false if it is in the registry
     */
    public boolean validateArea(GeographicalArea ga){
        return !areasList.contains(ga);
    }

    /**
     * Adds a geographical area to the registry
     * @param ga Geographical area to add
     */
    public boolean addArea(GeographicalArea ga){
        return this.areasList.add(ga);
    }
    
    public boolean contains(GeographicalArea ga){
        return areasList.contains(ga);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeographicalAreaRegistry that = (GeographicalAreaRegistry) o;
        return areasList.equals(that.areasList);
    }
}