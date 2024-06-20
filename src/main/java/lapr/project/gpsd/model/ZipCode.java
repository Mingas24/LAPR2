package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Utils;

public class ZipCode{
    private String zipCode;

    /**
     * construtor sem coordenadas
     * @param zipCode
     */
    public ZipCode(String zipCode){
        this.zipCode = zipCode;
        Float[] temp = Utils.getLatAndLon(zipCode);
    }

    /**
     * Construtor com coordenadas
     * @param codPostal
     * @param latitude
     * @param longitude
     */

    public ZipCode(String codPostal, float latitude, float longitude){
        this.zipCode = codPostal;
    }

    public String getZipCode(){
        return zipCode;
    }

    public void setCodPostal(String codPostal){
        this.zipCode = codPostal;
    }

    public boolean isEmpty(ZipCode zip){
        if(zip==null){
            return true;
        }else{
            return false;
        }
    }

    public float getLatitude() {
        Float aux[]=Utils.getLatAndLon(this.zipCode);
        return aux[0];
    }

    public float getLongitude() {
        Float aux[]=Utils.getLatAndLon(this.zipCode);
        return aux[1];
    }
    
    @Override
    public String toString(){
        return String.format("%s", this.zipCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZipCode that = (ZipCode) o;
        return zipCode.equals(that.zipCode);
    }
}
