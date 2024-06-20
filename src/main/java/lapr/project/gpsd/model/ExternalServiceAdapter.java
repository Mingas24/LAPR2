package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExternalServiceAdapter implements ExternalService{
    /**
     * The api used by the adapter
     */
    private ExternalServiceByCsv api;

    /**
     * Constructor
     */
    public ExternalServiceAdapter() {
        this.api = new ExternalServiceByCsv();
    }

    /**
     * Returns the zip codes covered by an area with center in the zip code 'center' and radius 'radius'.
     * This adapter uses an api that goes through a csv file and gets all zip codes within the area.
     * @param center Central zip code
     * @param radius Radius (metres)
     * @return
     */
    @Override
    public List<ZipCode> obtainCoverage(ZipCode center, double radius) {
        return new ArrayList<>(api.obtainCoverage(center,radius));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return true;
    }

}
