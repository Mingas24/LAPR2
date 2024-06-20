package lapr.project.gpsd.registry;

import java.util.ArrayList;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.model.PostalAddress;

import java.util.List;
import java.util.Objects;
import lapr.project.gpsd.model.AssignedService;
import lapr.project.gpsd.model.PreferedSchedule;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.ServiceDescription;
import lapr.project.gpsd.utils.Time;

public class ServiceRequestRegistry{
    
    private Time END_SHIFT = new Time(0,0);
    private Time START_SHIFT = new Time(6,0);
            
    private List<ServiceRequest> requestsList;

    /**
     * Constructor.
     * @param requestsList
     */
    public ServiceRequestRegistry(List<ServiceRequest> requestsList) {
        this.requestsList = requestsList;
    }

    /**
     * Empty constructor.
     */
    public ServiceRequestRegistry(){
        this.requestsList = new ArrayList<>();
    }

    /**
     * Creates a new Request.
     * @param client
     * @param postalAddress
     * @return
     */
    public ServiceRequest newRequest(Client client, PostalAddress postalAddress){
        return new ServiceRequest(client, postalAddress);
    }

    /**
     * validates the request.
     * @param servReq
     * @param geoAreReg 
     * @return
     */
    public boolean validateRequest(ServiceRequest servReq, GeographicalAreaRegistry geoAreReg){
       double cost = servReq.calculateCost(geoAreReg);
       return verifyRequest(servReq);
    }
    
    private boolean verifyRequest(ServiceRequest servReq){
        boolean flag;
        for (ServiceDescription sd : servReq.getDescription()){
            flag = false;
            for (PreferedSchedule ps : servReq.getSchedules()){
                if(verifyRequest(sd, ps)){
                    flag= true;
                    break;
                }
            }
            if(!flag)
                return false;
        }
        return true;
    }
    
    public boolean verifyRequest(ServiceDescription sd, PreferedSchedule ps){
        Time duration = sd.getDuration();
        return (ps.getTime().plus(duration).isBetween(END_SHIFT, START_SHIFT) || ps.getTime().isMaior(ps.getTime().plus(duration)));
    }

    /**
     * Adds the request to the Registered Requests List.
     * @param servReq - Request to register.
     * @param geoAreaReg - GeographicalAreaRegistry.
     * @return Request number
     */
    public int registerRequest(ServiceRequest servReq, GeographicalAreaRegistry geoAreaReg){
        validateRequest(servReq, geoAreaReg);
        int num = createRequestNumber();
        servReq.setNumber(num);
        addRequest(servReq);
        // Notify the client
        return num;
    }

    /**
     * Creates the number for the Request.
     * @return
     */
    public int createRequestNumber(){
        return this.requestsList.size();
    }

    /**
     * Adds the request to the RequestList.
     * @param req
     */
    public void addRequest(ServiceRequest req){
       this.requestsList.add(req);
    }
    
    public void addRequest(Service serv, String strDesc){
        
    }

    /**
     * Notifies the client of the sucess of the UC.
     */
    public void notifyClient(){
        System.out.println("Sucess!");
        //This method would have sent a email to the client, whitch is not requested.
    }

    public List<ServiceRequest> getRequestList(){
        return requestsList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceRequestRegistry that = (ServiceRequestRegistry) o;
        return requestsList.equals(that.requestsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestsList);
    }

    public List<ServiceRequest> getRequestListByClient(Client cli) {
        List<ServiceRequest> lsr = new ArrayList<>();
        for(ServiceRequest sr : this.requestsList){
            if(sr.hasClient(cli))
                lsr.add(sr);
        }
        return lsr;
    }

    public List<AssignedService> getAssignedServices(List<ServiceRequest> lsr) {
        List<AssignedService> las = new ArrayList<>();
        for(ServiceRequest sr : lsr){
            las.addAll(sr.getAssignedServices());
        }
        return las;
    }

}