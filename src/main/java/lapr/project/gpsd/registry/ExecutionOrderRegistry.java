/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.registry;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.AssignedService;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.ExecutionOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.utils.Date;

/**
 *
 * @author Jee ^^
 */
public class ExecutionOrderRegistry {

    private List<ExecutionOrder> execOrdersList;
    private ServiceProvider sp;

    /**
     *
     * @param execOrdersList Execution orders
     */
    public ExecutionOrderRegistry(List<ExecutionOrder> execOrdersList) {
        this.execOrdersList = execOrdersList;
    }

    /**
     * Default constructor
     */
    public ExecutionOrderRegistry() {
        this.execOrdersList = new ArrayList<>();
    }

    /**
     * Generates a new Execution order and register it
     *
     * @param as assignedService
     * @param sr ServiceRequest
     * @return true if its sucesfull registered
     */
    public boolean newExecutionOrder(AssignedService as, ServiceRequest sr) {
        int num = getExecOrderNum();
        Date date = getCurrentDate();
        ExecutionOrder eo = new ExecutionOrder(num, date, sr, as,sp);
        if (validate(eo)) {
            return this.execOrdersList.add(eo);
        }
        return false;
    }

    /**
     * Generates a number to the executionOrder
     *
     * @return number generated
     */
    private int getExecOrderNum() {
        return this.execOrdersList.size() + 1;
    }

    /**
     * Obtains an object Date with the current date
     *
     * @return Current date
     */
    private Date getCurrentDate() {
        return Date.dataAtual();
    }

    /**
     * Validates the executionOrder
     *
     * @param eo executionOrder
     * @return true if the executionOrder is valid
     */
    private boolean validate(ExecutionOrder eo) {
        //validates
        return true;
    }

    public List<ExecutionOrder> getExecutionOrderRegistry() {
        return execOrdersList;
    }
    public List<ExecutionOrder> getPendingExecutionOrderByServiceProvider(ServiceProvider sp){
        List<ExecutionOrder> pendingExecOrdersList=new ArrayList<>();
        System.out.println(execOrdersList.size());
        for(ExecutionOrder eo :execOrdersList ){
            System.out.println("ENTRA FOR EORDERREG");
            if(eo.hasServiceProvider(sp))
                System.out.println("ENTRA IF EORDERREG");
                pendingExecOrdersList.add(eo);
        }
        return pendingExecOrdersList;
    }
    public ExecutionOrder chooseExecutionOrder(int num){
        for(ExecutionOrder eo:execOrdersList){
            if(eo.hasNum(num))
                return eo;
        }
        return null;
    }

    public List<ExecutionOrder> getExecutionOrderInInterval(List<ExecutionOrder> leo, Date initDate, Date endDate) {
        List<ExecutionOrder> leoInDate = new ArrayList<>();
        for(ExecutionOrder eo : leo){
            if(eo.getAssignedService().getSchedule().getDay().isBetween(initDate, endDate))
                leoInDate.add(eo);
        }
        return leoInDate;   
    }
    
    public List<ExecutionOrder> getProvidedExecutionsListByClient(Client client){
        List<ExecutionOrder> execOrdList = new ArrayList<>();
        
        for (ExecutionOrder execOrd: this.execOrdersList){
            if (execOrd.getServiceRequest().getClient().equals(client))
                execOrdList.add(execOrd);
        }
        
        return execOrdList;
    }

    public ExecutionOrder getExecutionOrderByNum(int num){
        for(ExecutionOrder exe : execOrdersList){
            if(exe.getNum()==num){
                return exe;
            }
        }
        return null;
    }
}
