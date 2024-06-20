package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.utils.Time;

public class ServiceDescription{
    private String description;
    private Time duration; 
    private Service service;
    private String status;

    /**
     * Constructor.
     * @param description
     * @param duration
     * @param service
     */
    public ServiceDescription(String description, Time duration, Service service) {
        this.description = description;
        this.duration = duration;
        this.service = service;
        this.status = Constants.DESCRIPTION_STATUS_WAITING;
    }
    
    public Time getDuration(){
        return this.duration;
    }

    public String getDescription() {
        return description;
    }

    public Service getService() {
        return service;
    }

    public double getCost(){
        return duration.getTimeToHours()*service.getHourlyCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ServiceDescription that = (ServiceDescription) o;
        return duration.equals(that.duration) &&
                description.equals(that.description) &&
                service.equals(that.service);
    }

    @Override
    public String toString() {
        return "Description = " + description +
               "\nDuration = " + duration +
               "\nService = " + service.getID() + 
               "\nStatus = " + status;
    }
    
    public void setStatusAssigned(){
        this.status = Constants.DESCRIPTION_STATUS_ASSIGNED;
    }

    public String getStatus() {
        return status;
    }
}