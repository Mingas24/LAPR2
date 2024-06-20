package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Time;
        import lapr.project.gpsd.utils.Utils;

        import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class FirstComeFirstServe implements SchedulingAlgorithm {

    private String id;
    public FirstComeFirstServe() {
        this.id="First Come First Serve Algorithm";
    }

    /**
     * Implementation of the SchedulingAlgorithm interface method
     * The algorithm goes through all the service descriptions of all service requests, trying to find a service provider that fits the servicedescription's requirements
     * When it finds a provider that can provide the service described in the description, it generates an assigned service, and stores it in the respective request's list
     * @param lstRequests List of all the requests that have at least one provider in the geographical zone
     * @param lstProviders
     */
    @Override
    public List<ServiceRequest> execute(List<ServiceRequest> lstRequests, List<ServiceProvider> lstProviders){
        List<AssignedService> returner=new ArrayList<>();
        //schedule used for creating the assigned service
        PreferedSchedule sched;
        AssignedService aserv;
        Collections.sort(lstRequests);
    //go through all the requests
        for(ServiceRequest request: lstRequests){
            //go through all the service descriptions
            for(ServiceDescription desc : request.getDescription()){
                //check all the providers to see who can provide the service listed in the description
                for(ServiceProvider provider : lstProviders){
                    //check if the provider provides the service category and is within the geographical area's radius
                    if (provider.getCategoryList().contains(desc.getService().getCategory()) && checkGeographicalAvailability(provider, request)){
                        //if he is, then check if he has schedule compatibility with the schedule listed in the request
                        sched=checkBestSchedule(provider, request.getSchedules(), desc.getDuration(), lstRequests);
                        //check if this provider can be assigned to this service
                        if(sched!=null) {
                            //if it got this far, it means the provider is compatible
                            //therefore, an assignedservice will be generated and added to the request's list
                            aserv=new AssignedService(provider, desc, new AssignedSchedule(sched.getDate(), sched.getTime(), sched.getTime().plus(desc.getDuration())));
                            request.getAssignedServices().add(aserv);
                            request.addAssignedService(aserv);
                            returner.add(aserv);
                        }

                    }

                }
            }
        }
        return lstRequests;
    }


    /**
     * Returns a schedule that the provider is compatible with
     * @param provider service provider
     * @param schedules list of schedules that the client prefers
     * @return A compatible schedule (if it finds one) or null if it doesn't
     */
    public PreferedSchedule checkBestSchedule(ServiceProvider provider, List<PreferedSchedule> schedules, Time duration , List<ServiceRequest> requests){
        for(PreferedSchedule schedule : schedules){
            if (provider.isCompatible(schedule) && !checkOverlap(requests, schedule, duration, provider)){
                return schedule;
            }
        }
        return null;
    }

    /**
     * Checks if the provider has any assigned services that overlap with the schedule found
     * @param lstRequest List of all the service requests in the system
     * @param sched schedule to check overlapping
     * @param duration duration of the request (used to get an estimated end time to the request)
     * @return false if there is no overlap, true otherwise
     */
    public boolean checkOverlap(List<ServiceRequest> lstRequest, PreferedSchedule sched, Time duration, ServiceProvider provider){
        Time aux;
        for(ServiceRequest req : lstRequest){
            //check each assigned service
            for(AssignedService as : req.getAssignedServices()){
                //if it's an assigned service belonging to the provider, the code will do the verifications
                if(as.getServiceProvider().equals(provider)) {
                    //check time overlapping
                    //first if it's in the same day
                    if (as.getSchedule().getDay().equals(sched.getDate())) {
                        //now that the day is the same, check the timing
                        aux = sched.getTime().plus(duration);
                        if (sched.getTime().isBetween(as.getSchedule().getStartTime(), as.getSchedule().getEndTime())
                                || aux.isBetween(as.getSchedule().getStartTime(), as.getSchedule().getEndTime())) {
                            return true;
                        }

                    }
                }
            }
        }
        return false;

    }

    /**
     * Checks if a provider is within a request's readius
     * @param provider Service provider to check
     * @param request Service request
     * @return true if he's within the radius, false otherwise
     */
    public boolean checkGeographicalAvailability(ServiceProvider provider, ServiceRequest request){
        ZipCode provCode = provider.getPostalAddress().getZipCode();
        ZipCode requestCode=request.getGeoAre().getCenterZipCode();
        double radius=request.getGeoAre().getRadius();
        double latCodeProv=provCode.getLatitude();
        double lonCodeProv=provCode.getLongitude();
        double latCodeReq=requestCode.getLatitude();
        double lonCodeReq=requestCode.getLongitude();
        //For the provider to have geographical availability, his postal code needs to be within the radius of the g.area
        //In other words, the distance from the provider's code to the area's central code needs to be equal or less than the area's radius
        return Utils.distance(latCodeProv,lonCodeProv,latCodeReq,lonCodeReq)<=radius;


    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FirstComeFirstServe that = (FirstComeFirstServe) o;
        return id.equals(that.id);
    }
}
