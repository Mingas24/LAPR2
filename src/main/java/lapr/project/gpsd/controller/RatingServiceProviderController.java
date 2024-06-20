package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.ExecutionOrder;
import lapr.project.gpsd.model.Rating;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.registry.ClientRegistry;
import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.RatingRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;

/**
 *
 * @author Gonçalo Pinto (1180987)
 */
public class RatingServiceProviderController {
    private ApplicationGPSD app;
    private Company company;
    private ClientRegistry cliReg;
    private Client client;
    private ExecutionOrderRegistry execOrdReg;
    private List<ExecutionOrder> execOrdList;
    private ExecutionOrder execOrd;
    private ServiceProvider servProv;
    private RatingRegistry ratReg;
    private Rating rat;
    private List<Stage> windows;

    public RatingServiceProviderController() {
        this.app = ApplicationGPSD.getInstance();
        this.company = app.getCompany();
        this.windows = new ArrayList<>();
        this.cliReg = this.company.getClientRegistry();
        this.execOrdReg = this.company.getExecutionOrderRegistry();
    }
    
    public List<ExecutionOrder> newRatingServiceProvider(){
        UserSession login = this.app.getPresentSession();
        String email = login.getUserEmail();
        this.client = this.cliReg.getClientByEmail(email);
        return this.execOrdList = this.execOrdReg.getProvidedExecutionsListByClient(client);
    }
    
    public void chooseExecutionOrder(ExecutionOrder execOrd){
        this.execOrd = execOrd;
        this.servProv = this.execOrd.getServiceProvider();
        this.ratReg = this.servProv.getRatingRegistry();
    }
    
    public ExecutionOrder selectExecutionOrder(int execOrdID){
        for (ExecutionOrder execOrd: this.execOrdList){
            if (execOrd.getNum() == execOrdID)
                return execOrd;
        }
        return null;
    }
    
    public void chooseRating(int rat){
        this.rat = this.ratReg.newRating(rat);
    }
    
    public boolean registerRating(){
        return this.ratReg.registerRating(this.rat);
    }
    
    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
    
    @Override
    public String toString(){
        return String.format("Rate: ", this.rat.getRate());
    }
}
