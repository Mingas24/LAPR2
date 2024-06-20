/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Utils;

/**
 *
 * @author Jee ^^
 */
public class ExecutionOrder {

    private final int num;
    private final Date registerDate;
    private final ServiceRequest sr;
    private final AssignedService as;
    private final ServiceProvider sp;
    private boolean statusExecOrd;
    private double dist;
    
    /**
     * Contructor
     *
     * @param num
     * @param date
     * @param sr
     * @param as
     * @param sp
     */
    public ExecutionOrder(int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp) {
        this.num = num;
        this.registerDate = date;
        this.sr = sr;
        this.as = as;
        this.sp = sp;
        this.statusExecOrd = false;
        this.dist = Utils.distance(as.getServiceProvider().getPostalAddress().getZipCode().getZipCode(), sr.getPostalAddress().getZipCode().getZipCode());
    }

    public ExecutionOrder(int num, Date date, ServiceRequest sr, AssignedService as, ServiceProvider sp, double dist) {
        this.num = num;
        this.registerDate = date;
        this.sr = sr;
        this.as = as;
        this.sp = sp;
        this.statusExecOrd = false;
        this.dist = dist*1000;
    }

    /**
     * Verifies if exists a certain Service Provider
     *
     * @param sp
     * @return this.sp.equals(sp)
     */
    public boolean hasServiceProvider(ServiceProvider sp) {
        return this.sp.equals(sp);
    }

    /**
     * Verifies if exists a certain id
     *
     * @param num
     * @return this.num == num
     */
    public boolean hasNum(int num) {
        return this.num == num;
    }

    /**
     * Method to choose the evaluation of the finished service
     *
     * @param answer
     * @return new Evaluation(answer)
     */
    public Evaluation chooseEvaluation(boolean answer) {
        return new Evaluation(answer);
    }

    /**
     * Exchanges the service to done
     */
    public void setToFinished() {
        this.statusExecOrd = true;
    }

    public int getNum() {
        return num;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public ServiceRequest getServiceRequest() {
        return sr;
    }

    public AssignedService getAssignedService() {
        return as;
    }

    public ServiceProvider getServiceProvider() {
        return sp;
    }

    public boolean getStatus() {
        return statusExecOrd;
    }
    
    public double getDistance(){
        return this.dist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExecutionOrder that = (ExecutionOrder) o;
        return num == that.num &&
                statusExecOrd == that.statusExecOrd &&
                Double.compare(that.dist, dist) == 0 &&
                registerDate.equals(that.registerDate) &&
                sr.equals(that.sr) &&
                as.equals(that.as) &&
                sp.equals(that.sp);
    }

    @Override
    public String toString() {
        return "num=" + num +
                ", registerDate=" + registerDate +
                ", sr=" + sr +
                ", as=" + as +
                ", sp=" + sp +
                ", statusExecOrd=" + statusExecOrd +
                ", dist=" + dist;

    }
}
