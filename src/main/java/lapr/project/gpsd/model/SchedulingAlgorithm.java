package lapr.project.gpsd.model;

import java.util.List;

public interface SchedulingAlgorithm {
    List<ServiceRequest> execute(List<ServiceRequest> lstRequests, List<ServiceProvider> lstProviders);
    String getId();
}
