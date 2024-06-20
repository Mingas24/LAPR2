package lapr.project.gpsd.model;

import java.util.Objects;

public class PostalAddress{
    final private String address;
    final private ZipCode zipCode;
    final private String town;
    
    public PostalAddress(String strAddress, String strZipCode, String strTown){
        this.address = strAddress;
        this.zipCode = new ZipCode(strZipCode);
        this.town = strTown;
    }
    
    public PostalAddress(String strAddress, ZipCode zipCode, String strTown){
        this.address = strAddress;
        this.zipCode = zipCode;
        this.town = strTown;
    }
    /**
     * Constructor Copy.
     * @param addr1 
     */
    public PostalAddress (PostalAddress addr1){
        this.address = addr1.address;
        this.zipCode = addr1.zipCode;
        this.town = addr1.town;
    }
      
    @Override
    public String toString(){
        return String.format("%s - %s - %s", this.address, this.zipCode.toString(), this.town);
    }

    public ZipCode getZipCode() {
        return this.zipCode;
    }

    public String getAddress() {
        return this.address;
    }

    public String getTown() {
        return this.town;
    }
    
    @Override
    public boolean equals(Object o) {
        // Inspirado em https://www.sitepoint.com/implement-javas-equals-method-correctly/
        
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
        PostalAddress obj = (PostalAddress) o;
        return (Objects.equals(address, obj.address) && 
                Objects.equals(zipCode, obj.zipCode) &&
                Objects.equals(town, obj.town));
    }
}