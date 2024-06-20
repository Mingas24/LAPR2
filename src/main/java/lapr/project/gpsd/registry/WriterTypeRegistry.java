/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.registry;

import java.util.ArrayList;
import java.util.List;
import lapr.project.gpsd.model.WriterType;

/**
 *
 * @author Jee ^^
 */
public class WriterTypeRegistry {
    private List<WriterType> wtl;
    
    public WriterTypeRegistry(){
        wtl = new ArrayList<>();
    }
    
    public WriterType getWriterTypeByExtension(String extension){
        for(WriterType writer: wtl){
            if(writer.hasExtension(extension))
                return writer;
        }
        return null;
    }
    
    public boolean registerWriterType(WriterType wt){
        if(validateWriterType(wt))
            return this.addWriterType(wt);
        return false;
    }
    
    public boolean validateWriterType(WriterType wt){
        return true;
    }
    
    private boolean addWriterType(WriterType wt){
        return this.wtl.add(wt);
    }
}
