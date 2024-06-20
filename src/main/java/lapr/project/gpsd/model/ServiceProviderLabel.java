/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.Objects;

/**
 *
 * @author ADMIN
 */
public class ServiceProviderLabel {
    private String label;
    
    private final String LABEL_WORSTPROVIDER = "Worst Providers";
    private final String LABEL_REGULARPROVIDER = "Regular Providers";
    private final String LABEL_OUTSTANDINGPROVIDER = "Outstanding Providers";

    public ServiceProviderLabel(double diffValue, double sumValue, ServiceProvider servPro) {
        double servProRating = servPro.getRating();
        if(servProRating<diffValue){
            this.label=LABEL_WORSTPROVIDER;
        }
        else{
            if ((servProRating>=diffValue) && (servProRating<=sumValue)){
                this.label = LABEL_REGULARPROVIDER;
            }
            else{
                this.label = LABEL_OUTSTANDINGPROVIDER;
            }
        }
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceProviderLabel that = (ServiceProviderLabel) o;
        return label.equals(that.label);
    }

    @Override
    public String toString() {
        return label ;
    }
}
