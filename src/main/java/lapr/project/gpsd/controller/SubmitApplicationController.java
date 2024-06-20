package lapr.project.gpsd.controller;

import java.io.IOException;
import java.util.ArrayList;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.ServiceProviderApplication;
import lapr.project.gpsd.registry.CategoryRegistry;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import lapr.project.gpsd.model.AcademicQualification;
import lapr.project.gpsd.model.ProfessionalQualification;
import lapr.project.gpsd.registry.ApplicationRegistry;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class SubmitApplicationController {

    private ApplicationGPSD app;
    private Company company;
    private ServiceProviderApplication serviceProApp;
    private CategoryRegistry catReg;
    private List<Stage> windows;
    private ApplicationRegistry appReg;

    public SubmitApplicationController() {
        this.app = ApplicationGPSD.getInstance();
        this.company = app.getCompany();
        this.appReg = this.company.getApplicationRegistry();
        this.windows = new ArrayList<>();
    }

    /**
     * Creates a new Service Provider Application.
     *
     * @param strName
     * @param strTIN
     * @param strTelephone
     * @param strEmail
     * @param strAddress
     * @param strZipCode
     * @param strTown
     */
    public void newApplication(String strName, String strTIN, String strTelephone, String strEmail, String strAddress, String strZipCode, String strTown) {
        PostalAddress addr1 = ServiceProviderApplication.newPostalAddress(strAddress, strZipCode, strTown);
        this.serviceProApp = this.company.newApplication(strName, strTIN, strTelephone, strEmail, addr1);
    }

    /**
     *
     * @param strDesign
     * @param strDegree
     * @param strClassific
     * @return
     */
    public boolean newAcademicQualification(String strDesign, String strDegree, String strClassific) {
        return this.serviceProApp.newAcademicQualification(strDesign, strDegree, strClassific);
    }

    /**
     * Creates a new Professional Qualification to the Service Provider
     * Application.
     *
     * @param strDesc Description
     * @return
     */
    public boolean newProfessionalQualification(String strDesc) {
        return this.serviceProApp.newProfessionalQualification(strDesc);
    }

    //O método no comentário seguinte não é para implementar
    //public void newSuppDoc();
    /**
     * Returns the list of categories.
     *
     * @return
     */
    public List<Category> getCategoryList() {
        this.catReg = company.getCategoryRegistry();
        return this.catReg.getCategoryList();
    }

    /**
     * Registers a new category on the ServiceProviderApplication.
     *
     * @param cat
     */
    public void registerCategory(Category cat) {
        this.serviceProApp.registerCategory(cat);
    }

    /**
     * Validates the Service Provider Application.
     *
     * @return
     */
    public boolean validateApplication() {
        return this.appReg.validateApplication(this.serviceProApp);
    }

    public void getApplicationRegistry() {
        this.appReg = this.company.getApplicationRegistry();
    }

    /**
     * Registers the Service Provider Application.
     */
    public void registerApplication() {
        this.appReg.registerApplication(this.serviceProApp);
    }

    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }

    public void finishSubmission(ActionEvent event) throws IOException {
        closeWindows();
    }

    @Override
    public String toString() {
        String str = String.format("Name: %s%n"
                + "TIN: %s%n"
                + "Telephone: %s%n"
                + "Email: %s%n%n"
                + "Address: %s%n"
                + "ZIP Code: %s%n"
                + "Town: %s%n%n",
                this.serviceProApp.getName(), this.serviceProApp.getTin(),
                this.serviceProApp.getTelephone(), this.serviceProApp.getEmail(),
                this.serviceProApp.getPostalAddress().getAddress(),
                this.serviceProApp.getPostalAddress().getZipCode().toString(),
                this.serviceProApp.getPostalAddress().getTown());
        str += String.format("Categories:%n");
        int i = 1;
        for (Category cat : this.serviceProApp.getCatList()) {
            str += String.format("%s. Category: %s%n",
                    i, cat);
            i++;
        }
        str += String.format("%nAcademic Qualification:%n");
        i = 1;
        for (AcademicQualification acad : this.serviceProApp.getAcadQual()) {
            str += String.format("%s. Designation: %s%n"
                    + "%s. Degree: %s%n"
                    + "%s. Classification: %s%n%n",
                    i, acad.getDesignation(), i, acad.getDegree(), i, acad.getClassification());
            i++;
        }
        str += String.format("%nProfessional Qualification:%n");
        i = 1;
        for (ProfessionalQualification prof : this.serviceProApp.getProfQual()) {
            str += String.format("%s. Description: %s%n",
                    i, prof.getDescription());
            i++;
        }
        return str;
    }

    public ServiceProviderApplication getServiceProApp() {
        return serviceProApp;
    }
}
