package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.registry.ClientRegistry;

public class AssociatePostalAddressToClientController {

    private PostalAddress postalAddress;
    private Company company;
    private Client client;
    private ClientRegistry cliReg;
    private List<Stage> windows;

    /**
     * Iniciates the process of the Aplication GPSD
     */
    public AssociatePostalAddressToClientController() {
        this.company = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Gets the Client that wants to associate a new address to his list of
     * addresses
     */
    public void newAssociation() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        UserSession session = app.getPresentSession();
        String email = session.getUserEmail();
        this.cliReg = this.company.getClientRegistry();
        this.client = this.cliReg.getClientByEmail(email);
    }

    /**
     * Creates a new Postal Address with the information that cames from the UI
     *
     * @param address
     * @param zipCode
     * @param town
     * @return creates PostalAddress
     */
    public PostalAddress newPostalAddress(String address, String zipCode, String town) {
        this.postalAddress = Client.newPostalAddress(address, zipCode, town);
        return this.postalAddress;
    }

    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }

    /**
     * Regists the postal address to the list of PostalAddress in the client
     *
     * @return true if the association is complete with success, false otherwise
     */
    public boolean registerAddress() {
        return client.addPostalAddress(postalAddress);
    }

//    public void saveInFile(){
//        this.cliReg.saveInFile();
//    }
//    
    @Override
    public String toString() {
        return String.format("Address: %s%n"
                + "ZIP Code: %s%n"
                + "Town: %s", this.postalAddress.getAddress(), this.postalAddress.getZipCode(), this.postalAddress.getTown());
    }
}
