/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.List;

/**
 *
 * @author Jee ^^
 */
public interface Writer {
    
    /**
     * Writes execution order to a file
     * @param leo exectuion orders to write
     * @return 
     */
    boolean writeExecutionOrders(List<ExecutionOrder> leo);
}
