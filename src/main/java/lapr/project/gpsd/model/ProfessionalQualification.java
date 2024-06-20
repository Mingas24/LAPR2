package lapr.project.gpsd.model;

import java.util.Objects;

/**
 *
 * @author Paulo Silva (1180957)
 */
public class ProfessionalQualification {
    private String description;
    
    /**
     * Constructor with the description of the qualifications.
     * @param description - description of the qualifications
     */
    public ProfessionalQualification(String description){
        this.description = description;
    }
    
    /**
     * Constructor copy of the Professional Qualifications.
     * @param pq 
     */
    public ProfessionalQualification(ProfessionalQualification pq){
        this.description = pq.description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    @Override
    public String toString() {
        return String.format("Professional Qualification: %s", this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfessionalQualification that = (ProfessionalQualification) o;
        return description.equals(that.description);
    }
}