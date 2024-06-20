package lapr.project.gpsd.model;

import java.util.List;

public interface ExternalService {
    /**
     * Returns the zip codes covered by an area with center in the zip code 'center' and radius 'radius'
     * @param center Central zip code
     * @param radius Radius (metres)
     * @return The list of all the zip codes within the area
     */
    public List<ZipCode> obtainCoverage(ZipCode center, double radius);
}