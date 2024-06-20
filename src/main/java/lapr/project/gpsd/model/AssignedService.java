/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Constants;

import java.util.Objects;

/**
 *
 * @author Jee ^^
 */
public class AssignedService {
    private AssignedSchedule sch;
    private ServiceProvider sp;
    private ServiceDescription sd;
    private String status;
    
    /**
     * Constructor with service provider, description of the service and assigned schedule
     * @param sp - Service provider
     * @param sd - Service description
     * @param sch - AssignedSchedule
     */
    public AssignedService(ServiceProvider sp, ServiceDescription sd, AssignedSchedule sch){
        this.sch = sch;
        this.sp = sp;
        this.sd = sd;
        this.status = Constants.STATUS_WAITING;
    }
    
    public AssignedSchedule getSchedule() {
        return sch;
    }

    public void setSchedule(AssignedSchedule sch) {
        this.sch = sch;
    }

    public ServiceProvider getServiceProvider() {
        return sp;
    }

    public void setServiceProvider(ServiceProvider sp) {
        this.sp = sp;
    }

    public ServiceDescription getServiceDescription() {
        return sd;
    }

    public void setServiceDescription(ServiceDescription sd) {
        this.sd = sd;
    }

    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    @Override
    public String toString() {
        return String.format("Assigned Service: \nService Description: %s\nService Provider: %s\nAssigned Schedule: %s\nStatus: %s ", this.sd, this.sp, this.sch, this.status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignedService that = (AssignedService) o;
        return sch.equals(that.sch) &&
                sp.equals(that.sp) &&
                sd.equals(that.sd) &&
                status.equals(that.status);
    }

}
