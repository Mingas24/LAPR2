package lapr.project.gpsd.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.registry.ClientRegistry;
import lapr.project.gpsd.utils.Utils;

public class RegisterClientController{
    private ApplicationGPSD app;
    private Company company;
    private Client client;
    private ClientRegistry cliReg;
    private String strPassword;
    private boolean registComplete;
    private List<Stage> windows;
    
    public RegisterClientController(){
        this.app = ApplicationGPSD.getInstance();
        this.company = app.getCompany();
        this.registComplete = false;
        this.windows = new ArrayList<>();
    }
    
    public boolean getRegistComplete(){
        return this.registComplete;
    }
    
    public boolean newClient(String strName, String strTIN, String strTelephone, String strEmail, String strPwd, String strAddress, String strZipCode, String strTown){
        try{
            this.strPassword = strPwd;
            ClientRegistry cr = this.company.getClientRegistry();
            PostalAddress add1 = Client.newPostalAddress(strAddress, strZipCode, strTown);
            this.client = cr.newClient(strName, strTIN, strTelephone, strEmail, strPwd, add1);
            return cr.validateClient(this.client, this.strPassword);
        }catch(RuntimeException ex){
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            this.client = null;
            return false;
        }
    }
    
    public boolean addPostalAddress(String strAddress, String strZipCode, String strTown){
        if (this.client != null){
            try{
                PostalAddress add2 = Client.newPostalAddress(strAddress, strZipCode, strTown);
                return this.client.addPostalAddress(add2);
            }catch(RuntimeException ex){
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } 
        return false;
    }
    
    public boolean registerClient(){
        this.cliReg = this.company.getClientRegistry();
        AuthorizationFacade af = this.company.getAuthorizationFacade();
        return this.cliReg.registerClient(this.client, this.strPassword, af);
    }
    
//    public void saveInFile(){
//        this.cliReg.saveInFile();
//    }

    public String getClienteString(){
        return this.client.toString();
    }
    
    public boolean registComplete(){
        return this.registComplete = true;
    }
        
    public void addToListOfWindows(Stage stage){
        this.windows.add(stage);
    }
    
    public void closeWindows(){
        for(Stage stage: this.windows)
            stage.close();
    }
    
    public void changeToAuthentication(ActionEvent event) throws IOException{
        closeWindows();
        Parent authentication = FXMLLoader.load(getClass().getResource("/fxml/0.1 - Authentication.fxml"));
        Scene authenticationScene = new Scene(authentication);
        authenticationScene.getStylesheets().add("/styles/Styles.css");
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(authenticationScene);
        window.show();
    }
    
    @Override
    public String toString(){
        int i = 1;
        String str = String.format("Name: %s%n"
                + "TIN: %s%n"
                + "Telephone: %s%n"
                + "Email: %s%n%n",
                this.client.getName(), this.client.getTIN(),
                this.client.getTelephone(), this.client.getEmail());
        for (PostalAddress addr: client.getPostalAddresses()){
            str += String.format("%s. Address: %s%n"
                    + "%s. ZIP Code: %s%n"
                    + "%s. Town: %s%n%n",
                    i, addr.getAddress(), i,  addr.getZipCode().toString(), i, addr.getTown());
            i++;
        }
        return str;
    }
}