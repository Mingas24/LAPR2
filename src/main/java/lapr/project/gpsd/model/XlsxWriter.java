/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Jee ^^
 */
public class XlsxWriter implements Writer{

    private String fileName = "ExecutionOrders";
    private String fileExtension = ".xlsx";
    private String file;
    private String[] header = {"Number", "Client", "Distance", "Category" ,"Service Type", "Day", "Time", "Postal Address", "Town", "Zip Code"};
    
    public XlsxWriter(){
    }
    
    @Override
    public boolean writeExecutionOrders(List<ExecutionOrder> leo) {
        if(!leo.isEmpty())
            file = fileName+"_"+leo.get(0).getServiceProvider().getAbrevName()+fileExtension;
        else
            file = fileName+fileExtension;
        
        boolean returner = true;

        try {
            // Create a Workbook
            Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
            
            // Create a Sheet
            Sheet sheet = workbook.createSheet("ExecutionOrders");
                
            // Create a Row
            Row headerRow = sheet.createRow(0);

            // Create cells
            for(int i = 0; i < header.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(header[i]);
            }

            int rowNum = 1;
            for(ExecutionOrder eo : leo){
                // Create Other rows and cells with employees data
                
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

                Row row = sheet.createRow(rowNum++);
                
                row.createCell(0)
                        .setCellValue(number);
                
                row.createCell(1)
                        .setCellValue(name);
                
                row.createCell(2)
                        .setCellValue(dist);
                
                row.createCell(3)
                        .setCellValue(category);
                
                row.createCell(4)
                        .setCellValue(servType);
                
                row.createCell(5)
                        .setCellValue(day);
                
                row.createCell(6)
                        .setCellValue(time);
                
                row.createCell(7)
                        .setCellValue(postAddr);
                
                row.createCell(8)
                        .setCellValue(zipCode);
                
                row.createCell(9)
                        .setCellValue(town);
                
            }
            
            // Resize all columns to fit the content size
            for(int i = 0; i < header.length; i++) 
                sheet.autoSizeColumn(i);
            
            // Write the output to a file
            FileOutputStream fileOut = new FileOutputStream(file);
            workbook.write(fileOut);
            fileOut.close();
            
            // Closing the workbook
            workbook.close();
            
        } catch (IOException ex) {
            Logger.getLogger(XlsxWriter.class.getName()).log(Level.SEVERE, null, ex);
            returner = false;
        }
        return returner;
    }    
    
}
