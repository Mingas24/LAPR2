/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.lang.reflect.Constructor;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jee ^^
 */
public class WriterType {

    private String path;
    private String extension;
    
    /**
     * Possible designations
     */
    private static String[] extensions = {"XML", "XLSX",
        "CSV"};
    
    /**
     * Possible paths
     */
    private static String[] paths = {"lapr.project.gpsd.model.XmlWriter","lapr.project.gpsd.model.XlsxWriter",
    "lapr.project.gpsd.model.CsvWriter"};
    
    public WriterType(String extension){
        path = getPath(extension);
        this.extension = extension;
    }
    
    /**
     * Obtains the path to create the class based on the designation
     * @param extension
     * @return path
     */
    public String getPath(String extension){
        for(int i = 0; i<extensions.length; i++) {
            if (extensions[i].equalsIgnoreCase(extension)) {
                return paths[i];
            }
        }
        throw new IllegalArgumentException("Invalid designation.");
    }
    
    public boolean hasExtension(String extension){
        return this.extension.equals(extension);
    }    
    
    public Writer newWriter(){
        try {
            Class<?> oClass = Class.forName(this.path);
            Class[] argsClasses = new Class[]{};
            Constructor constructor;
            constructor = oClass.getConstructor(argsClasses);
            Object[] argsValues = new Object[] {};
            Writer writ = (Writer) constructor.newInstance(argsValues);
            return writ;
        } catch (Exception ex) {
            Logger.getLogger(ServiceType.class.getName()).log(Level.SEVERE, "Exception caught!", ex);
            ex.printStackTrace();
        }
        return null;
        
    }
    
}
