package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class ServiceProviderApplication {
    
    private String name;
    private String tin;
    private String telephone;
    private String email;
    private PostalAddress postalAddress;
    private String status;
    
    private List<AcademicQualification> m_lstAcademicQualification;
    private List<ProfessionalQualification> m_lstProfessionalQualification;
    private List<Category> m_lstCategory;
    
    /**
     * Full constructor.
     * @param strName
     * @param strTIN
     * @param strTelephone
     * @param strEmail
     * @param strAddress
     * @param strZipCode
     * @param strTown 
     */
    public ServiceProviderApplication(String strName, String strTIN, String strTelephone, String strEmail, String strAddress, String strZipCode, String strTown){
        this.name = strName;
        this.tin = strTIN;
        this.telephone = strTelephone;
        this.email = strEmail;
        this.postalAddress = new PostalAddress(strAddress, strZipCode, strTown);
        this.m_lstAcademicQualification = new ArrayList<>();
        this.m_lstProfessionalQualification = new ArrayList<>();
        this.m_lstCategory = new ArrayList<>();
        this.status = Constants.STATUS_WAITING;
    }
    /**
     * Contructor using Copy Constructor from PostalAddress class.
     * @param strName
     * @param strTIN
     * @param strTelephone
     * @param strEmail
     * @param addr1 
     */
    public ServiceProviderApplication(String strName, String strTIN, String strTelephone, String strEmail, PostalAddress addr1){
        this.name = strName;
        this.tin = strTIN;
        this.telephone = strTelephone;
        this.email = strEmail;
        this.postalAddress = new PostalAddress(addr1);
        this.m_lstAcademicQualification = new ArrayList<>();
        this.m_lstProfessionalQualification = new ArrayList<>();
        this.m_lstCategory = new ArrayList<>();
        this.status = Constants.STATUS_WAITING;
    }
    
    /**
     * Constructor copy for ServiceProviderApplication objects.
     * @param appl 
     */
    public ServiceProviderApplication(ServiceProviderApplication appl){
        this.name = appl.name;
        this.tin = appl.tin;
        this.telephone = appl.telephone;
        this.email = appl.email;
        this.postalAddress = appl.postalAddress;
        this.m_lstAcademicQualification = appl.m_lstAcademicQualification;
        this.m_lstProfessionalQualification = appl.m_lstProfessionalQualification;
        this.m_lstCategory = appl.m_lstCategory;
        this.status=appl.status;
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getTin(){
        return this.tin;
    }
    
    public String getTelephone(){
        return this.telephone;
    }
    
    public String getEmail(){
        return this.email;
    }
    
    public PostalAddress getPostalAddress(){
        return this.postalAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String state) {
        this.status = state;
    }

    /**
     * Creates a new Postal Address.
     * @param strAddress
     * @param strZipCode
     * @param strTown
     * @return 
     */
    public static PostalAddress newPostalAddress(String strAddress, String strZipCode, String strTown){
        return new PostalAddress(strAddress, strZipCode, strTown);
    }
    
    /**
     * Creates a new Academic Qualification object.
     * @param strDesign
     * @param strDegree
     * @param strClassific 
     * @return  
     */
    public boolean newAcademicQualification(String strDesign, String strDegree, String strClassific){
        AcademicQualification aq = new AcademicQualification(strDesign, strDegree, strClassific);
        if(validateAcademicQualification(aq)){
            return this.addAcademicQualification(aq);
        }
        return false;
    }
    
    /**
     * Validates the academic qualifsication objects.
     * @param aq
     * @return 
     */
    private boolean validateAcademicQualification(AcademicQualification aq){
        return !((aq.getClassification() == null) || (aq.getDegree() == null) || 
                (aq.getDesignation() == null) || (aq.getClassification().isEmpty()) || 
                (aq.getDegree().isEmpty()) || (aq.getDesignation().isEmpty()));
    }
    
    /**
     * Adds the Academic Qualfication object to the ServiceProviderApplication object.
     * @param aq 
     */
    private boolean addAcademicQualification(AcademicQualification aq){
        return this.m_lstAcademicQualification.add(aq);
    }
    
    /**
     * Creates a new Professional Application.
     * @param description
     * @return 
     */
    public boolean newProfessionalQualification(String description){
        ProfessionalQualification pq = new ProfessionalQualification(description);
        if(validateProfessionalQualification(pq)){
            return this.addProfessionalQualification(pq);
        }
        return false;
    }
    
    /**
     * Validates the Professional Qualification object.
     * @param pq
     * @return 
     */
    private boolean validateProfessionalQualification(ProfessionalQualification pq){
        return !(pq.getDescription().trim().length()==0);
    }
    
    /**
     * Adds the Professional Qualification to the ServiceProviderApplication object.
     * @param pq 
     */
    private boolean addProfessionalQualification(ProfessionalQualification pq){
        return this.m_lstProfessionalQualification.add(pq);
    }
    
    /**
     * Adds a new category to the Serivce Provider Application
     * @param cat 
     */
    public void registerCategory(Category cat){
        if(validateCategory(cat)){
            this.addCategory(cat);
        }
    }
    
    /**
     * Validates the Category.
     * @param cat
     * @return 
     */
    private boolean validateCategory(Category cat){
        if(cat==null){
            return false;
        }
        return true;
    }
    
    /**
     * Adds the category to the category list.
     * @param cat
     * @return 
     */
    private boolean addCategory(Category cat){
        return this.m_lstCategory.add(cat);
    }

    public List<AcademicQualification> getAcadQual() {
        return m_lstAcademicQualification;
    }

    public List<ProfessionalQualification> getProfQual() {
        return m_lstProfessionalQualification;
    }

    public List<Category> getCatList() {
        return m_lstCategory;
    }

    public boolean hasTin(String tin) {
        return this.tin.equals(tin);
    }
    
    public String toStringAcademic(){
        //7 - AcademicQualification(A1(x;y;z)/ A2(x;y;z)...)
        String str = "";
        for(AcademicQualification acad: this.m_lstAcademicQualification){
            if (!"".equals(str))
                str += "/";
            str += (acad.getDesignation() + ";" + acad.getDegree() + ";" + acad.getClassification());
        }
        return str;
    }
    
    public String toStringProfessional(){
        //8 - ProfessionalQualification(P1/ P2...)
        String str = "";
        for(ProfessionalQualification prof: this.m_lstProfessionalQualification){
            if (!"".equals(str))
                str += "/";
            str += prof.getDescription();
        }
        return str;
    }
    
    public String toStringCategory(){
        //9 - Category(C1/ C2...)
        String str = "";
        for(Category cat: this.m_lstCategory){
            if (!"".equals(str))
                str += "/";
            str += cat.getCatID();
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceProviderApplication that = (ServiceProviderApplication) o;
        return name.equals(that.name) &&
                tin.equals(that.tin) &&
                telephone.equals(that.telephone) &&
                email.equals(that.email) &&
                postalAddress.equals(that.postalAddress) &&
                status.equals(that.status) &&
                m_lstAcademicQualification.equals(that.m_lstAcademicQualification) &&
                m_lstProfessionalQualification.equals(that.m_lstProfessionalQualification) &&
                m_lstCategory.equals(that.m_lstCategory);
    }
}