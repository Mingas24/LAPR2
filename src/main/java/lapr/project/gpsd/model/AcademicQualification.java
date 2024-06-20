package lapr.project.gpsd.model;

/**
 *
 * @author Paulo Silva (1180957)
 */
public class AcademicQualification {
    private String designation;
    private String degree;
    private String classification;

    /**
     * Constructor with designation, degree and classification of the Academic Qualifications.
     * @param designation
     * @param degree
     * @param classification 
     */
    public AcademicQualification(String designation, String degree, String classification) {
        this.designation = designation;
        this.degree = degree;
        this.classification = classification;
    }
    
    /**
     * Constructor copy of the Academic Qualifications
     * @param aq 
     */
    public AcademicQualification (AcademicQualification aq){
        this.designation = aq.designation;
        this.degree = aq.degree;
        this.classification = aq.classification;
    }
    
    public String getDesignation(){
        return this.designation;
    }
    
    public String getDegree(){
        return this.degree;
    }
    
    public String getClassification(){
        return this.classification;
    }
    
    public void setDesignation(String strDesignation){
        this.designation = strDesignation;
    }
    
    public void setDegree(String strDegree){
        this.degree = strDegree;
    }
    
    public void setClassification(String strClassification){
        this.classification = strClassification;
    }
    
    @Override
    public String toString(){
        return String.format("Academic Qualifications: Designation: %s, Degree: %s, Classification: %s", this.designation, this.degree, this.classification);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicQualification that = (AcademicQualification) o;
        return designation.equals(that.designation) &&
                degree.equals(that.degree) &&
                classification.equals(that.classification);
    }

}