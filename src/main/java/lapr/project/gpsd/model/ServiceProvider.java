package lapr.project.gpsd.model;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
import lapr.project.gpsd.registry.RatingRegistry;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

/**
 *
 * @author Utilizador
 */
public class ServiceProvider {

    private String name;
    private String tin;
    private String telephone;
    private String email;
    private String mechNumber;
    private String abrevName;
    private PostalAddress postAddr;
    private DailyAvailabilityList dailyAvailabilityList;
    private List<GeographicalArea> geographicalAreaList;
    private List<Category> categoryList;
    private RatingRegistry ratReg;
    private ServiceProviderLabel label;
    private double rate;
    
    private double DEFAULT_RATING = 3;

    public ServiceProvider(String mechNumber, String tin, String name, String abrevName, String email, PostalAddress postAddr, List<GeographicalArea> geographicalAreaList, List<Category> categoryList) {
        this.name = name;
        this.tin = tin;
        this.email = email;
        this.mechNumber = mechNumber;
        this.abrevName = abrevName;
        this.postAddr = postAddr;
        this.geographicalAreaList = geographicalAreaList;
        this.categoryList = categoryList;
        this.rate = DEFAULT_RATING;
        this.dailyAvailabilityList = new DailyAvailabilityList();
        this.ratReg=new RatingRegistry(new ArrayList<>());
        this.label=new ServiceProviderLabel(3,3,this);
    }
    
    
    /**
     * 
     * @param name
     * @param abrevName
     * @param tin
     * @param mechNumber
     * @param telephone
     * @param email
     * @param postAddr
     * @param listCat 
     */
    public ServiceProvider(String name, String abrevName, String tin, String mechNumber, String telephone, String email, PostalAddress postAddr, List<Category> listCat){
        this.name = name;
        this.abrevName = abrevName;
        this.tin = tin;
        this.mechNumber = mechNumber;
        this.telephone = telephone;
        this.email = email;
        this.postAddr = postAddr;
        this.categoryList = new ArrayList<>();
        for(Category cat : listCat)
            this.categoryList.add(cat);
        this.dailyAvailabilityList = new DailyAvailabilityList();
        this.geographicalAreaList = new ArrayList<>();
        this.rate = DEFAULT_RATING;
        this.ratReg=new RatingRegistry(new ArrayList<>());
        this.label=new ServiceProviderLabel(3,3,this);
    }
    
    /**
     *
     * @param name
     * @param abrevName
     * @param tin
     * @param mechNumber
     * @param telephone
     * @param email
     * @param postAddr
     * @param areas
     * @param listCat
     */
    public ServiceProvider(String name, String abrevName, String tin, String mechNumber, String telephone, String email, PostalAddress postAddr, List<GeographicalArea> areas, List<Category> listCat){
        if((name == null) || (abrevName == null) || (tin == null) || (mechNumber == null) || (telephone == null) || email == null || postAddr == null || listCat == null|| areas == null ||
           (name.isEmpty()) || (abrevName.isEmpty()) || tin.isEmpty() || mechNumber.isEmpty()||telephone.isEmpty()||email.isEmpty() )
            throw new IllegalArgumentException("None of the arguments can be null or empty.");
        this.name = name;
        this.abrevName = abrevName;
        this.tin = tin;
        this.mechNumber = mechNumber;
        this.telephone = telephone;
        this.email = email;
        this.postAddr = postAddr;
        this.geographicalAreaList = new ArrayList<>();
        for(GeographicalArea area: areas)
            this.geographicalAreaList.add(area);
        this.categoryList = new ArrayList<>();
        for(Category cat: listCat)
            this.categoryList.add(cat);
        this.dailyAvailabilityList = new DailyAvailabilityList();
        this.geographicalAreaList = new ArrayList<>();
        this.rate = DEFAULT_RATING;
        this.ratReg=new RatingRegistry(new ArrayList<>());
        this.label=new ServiceProviderLabel(3,3,this);
    }

    /**
     *
     * @return
     */
    public String getTin(){
        return tin;
    }

    /**
     *
     * @param tin
     */
    public void setTin(String tin){
        this.tin = tin;
    }

    /**
     *
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     */
    public String getMechNumber() {
        return mechNumber;
    }

    /**
     *
     * @param mechNumber
     */
    public void setMechNumber(String mechNumber) {
        this.mechNumber = mechNumber;
    }

    /**
     *
     * @return
     */
    public String getAbrevName() {
        return abrevName;
    }

    /**
     *
     * @param abrevName
     */
    public void setAbrevName(String abrevName) {
        this.abrevName = abrevName;
    }

    /**
     *
     * @return
     */
    public PostalAddress getPostalAddress() {
        return postAddr;
    }

    /**
     *
     * @param postAddr
     */
    public void setPostalAddress(PostalAddress postAddr) {
        this.postAddr = postAddr;
    }

    /**
     *
     * @return
     */
    public List<GeographicalArea> getGeographicalAreaList() {
        return geographicalAreaList;
    }

    /**
     *
     * @param geographicalAreaList
     */
    public void setGeographicalAreaList(List<GeographicalArea> geographicalAreaList) {
        this.geographicalAreaList = geographicalAreaList;
    }

    /**
     *
     * @return
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     *
     * @param categoryList
     */
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    /**
     *
     * @param dailyAvailabilityList
     */
    public void setDailyAvailabilityList(DailyAvailabilityList dailyAvailabilityList){
        this.dailyAvailabilityList = dailyAvailabilityList;
    }
    
    /**
     *
     * @return
     */
    public DailyAvailabilityList getDailyAvailabilityList(){
        return dailyAvailabilityList;
    }

    /**
     *
     * @return
     */
    public String getName(){
        return this.name;
    }
    
    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    public RatingRegistry getRatingRegistry() {
        return this.ratReg;
    }

    public double getRating() {
        return rate;
    }

    public void setRating(double rating) {
        this.rate = rating;
    }

    public ServiceProviderLabel getLabel() {
        return label;
    }

    public void setLabel(String label){
        this.label.setLabel(label);
    }
    
    public void setLabel(ServiceProviderLabel label) {
        this.label = label;
    }
    
    /**
     *
     * @param email
     * @return
     */
    public boolean hasEmail(String email){
        return this.email.equalsIgnoreCase(email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceProvider that = (ServiceProvider) o;
        return Double.compare(that.rate, rate) == 0 &&
                name.equals(that.name) &&
                tin.equals(that.tin) &&
                email.equals(that.email) &&
                mechNumber.equals(that.mechNumber) &&
                abrevName.equals(that.abrevName) &&
                postAddr.equals(that.postAddr) &&
                dailyAvailabilityList.equals(that.dailyAvailabilityList) &&
                geographicalAreaList.equals(that.geographicalAreaList) &&
                categoryList.equals(that.categoryList) &&
                ratReg.equals(that.ratReg) &&
                label.equals(that.label);
    }

    /**
     *
     * @param date
     * @return
     */
    public Time[] getAvailabilityInDate(Date date){
        return this.dailyAvailabilityList.getAvailabilityInDate(date);
    }
    
    /**
     *
     * @param ps
     * @return
     */
    public boolean isCompatible(PreferedSchedule ps){
        Time checker=new Time(0,0);
        Time[] availabilityInPreferedDay = getAvailabilityInDate(ps.getDate());
        if(!availabilityInPreferedDay[0].equals(checker)&& !availabilityInPreferedDay[1].equals(checker)) {
            return ps.getTime().isBetween(availabilityInPreferedDay[0], availabilityInPreferedDay[1]);
        }else{
            return false;
        }
    } 

    /**
     * Adds a geographical area to the list where the service provider works
     * @param ga geograplical area
     * @return true if its sucessfully aded
     */
    public boolean addGeographicalArea(GeographicalArea ga) {
        return this.geographicalAreaList.add(ga);
    }

    public boolean addAvailability(Availability av){
        return this.dailyAvailabilityList.addAvailability(av);
    }

    public boolean addCategory(Category ca){
        return this.categoryList.add(ca);
    }

    public boolean addRating(Rating ra){
        return this.ratReg.addRating(ra);
    }
    
    @Override
    public String toString(){
        return String.format("Name = %s%nTIN = %s%nEmail = %s%nMechanical Number = %s%nPostal Address = %s%nGeographical Area: %s%nCategory: = %s%n%s", this.name, this.tin, this.email, this.mechNumber, this.postAddr, this.geographicalAreaList.toString(), this.categoryList.toString(), this.label.toString());
    }
    
    
}