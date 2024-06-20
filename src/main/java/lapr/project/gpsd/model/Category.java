package lapr.project.gpsd.model;

import java.util.Objects;

public class Category{
    private String catID;
    private String description;         
    
    public Category(String catID, String description){
        if((catID == null) || (description == null) ||
           (catID.isEmpty()) || (description.isEmpty()))
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        
        this.catID = catID;
        this.description = description;
    }
    
    public boolean hasID(String catID){
        return this.catID.equalsIgnoreCase(catID);
    }
    
    public boolean hasDescription(String description){
        return this.description.equalsIgnoreCase(description);
    }
    
    public String getCatID(){
        return this.catID;
    }

    public String getDescription() {
        return description;
    }
       
    @Override
    public String toString(){
        return String.format("%s - %s", this.catID, this.description);
    }

    
    @Override
    public boolean equals(Object o) {
        // Inspired in https://www.sitepoint.com/implement-javas-equals-method-correctly/
        
        // self check
        if (this == o)
            return true;
        // null check
        if (o == null)
            return false;
        // type check and cast
        if (getClass() != o.getClass())
            return false;
        // field comparison
        Category obj = (Category) o;
        return (Objects.equals(catID, obj.catID));
    }
}