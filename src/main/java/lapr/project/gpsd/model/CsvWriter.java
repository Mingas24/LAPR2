/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jee ^^
 */
public class CsvWriter implements Writer {
   
    private String fileName = "ExecutionOrders";
    private String fileExtension = ".csv";
    private File file;        
    
    public CsvWriter(){
    }
    
    @Override
    public boolean writeExecutionOrders(List<ExecutionOrder> leo) {
        if(!leo.isEmpty())
            file = new File(fileName+"_"+leo.get(0).getServiceProvider().getAbrevName()+fileExtension);
        else
            file = new File(fileName+fileExtension);
        boolean returner = true;
        try {
            Formatter out = new Formatter(file);
            header(out);
            for(ExecutionOrder eo : leo){
                int number = eo.getNum();
                String name = eo.getServiceRequest().getClient().getName();
                Double dist = eo.getDistance();
                String category = eo.getAssignedService().getServiceDescription().getService().getCategory().getCatID();
                String servType = eo.getAssignedService().getServiceDescription().getService().getServiceType().getServTypeId();
                String day = eo.getAssignedService().getSchedule().getDay().toAnoMesDiaString();
                String time = eo.getAssignedService().getSchedule().getStartTime().toStringHHMM();
                String postAddr = eo.getServiceRequest().getPostalAddress().getAddress();
                String zipCode = eo.getServiceRequest().getPostalAddress().getZipCode().getZipCode();
                String town = eo.getServiceRequest().getPostalAddress().getTown();
                
                out.format("%n%d,%s,%.2f,%s,%s,%s,%s,%s,%s,%s",number, name, dist,category, servType, day, time, postAddr, zipCode, town);
            }
            out.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvWriter.class.getName()).log(Level.SEVERE, null, ex);
            returner = false;
        }
        return returner;
    }    
    
    public void header(Formatter out){
        out.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s","Number","Client","Distance","Category","Service Type","Day","Time","Postal Address", "Town", "ZipCode");    
    }
}
