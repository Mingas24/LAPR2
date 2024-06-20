/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.Evaluation;
import lapr.project.gpsd.model.ExecutionOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;
import lapr.project.gpsd.registry.ServiceRequestRegistry;

/**
 *
 * @author Utilizador
 */
public class ReportEndServiceController {

    private ServiceProvider sp;
    private Company company;
    private ExecutionOrder eo;
    private ExecutionOrderRegistry eor;
    private List<ExecutionOrder> lpeo;
    private Evaluation eva;
    private ServiceRequest sReq;
    private ServiceRequestRegistry sReqReg;
    private ApplicationGPSD app;
    private ServiceProviderRegistry spr;
    private String sol;
    private String just;
    private List<Stage> windows;
    
    /**
     * Constructor that instanciates the company through the ApplicationGPSD
     * class.
     */
    public ReportEndServiceController() {
        if (!ApplicationGPSD.getInstance().getPresentSession().isLoggedInWithRole(Constants.ROLE_SERVICE_PROVIDER)) {
            throw new IllegalStateException("Unauthorized User!");
        }
        this.company = ApplicationGPSD.getInstance().getCompany();
        String email = ApplicationGPSD.getInstance().getPresentSession().getUserEmail();
        this.spr = company.getServiceProviderRegistry();
        this.sp = spr.getServiceProviderByEmail(email);
        System.out.println(sp.toString());
        this.windows = new ArrayList<>();
        this.eor=this.company.getExecutionOrderRegistry();
        this.lpeo=new ArrayList<>();
    }

    /**
     * Method that creates a new Report End Service
     *
     * @return lpeo
     */
    public List<ExecutionOrder> newReportEndService() {
//       app = ApplicationGPSD.getInstance();
//        UserSession login = app.getPresentSession();
//        String email = login.getUserEmail();
//         this.sp = this.spr.getServiceProviderByEmail(email);
//        eo = eor.getExecutionOrderRegistry();
        this.lpeo = eor.getPendingExecutionOrderByServiceProvider(sp);
        return lpeo;
    }

    /**
     * Method to choose the execution order that the service provider finished
     *
     * @param num
     * @return eor.chooseExecutionOrder(num)
     */
    public ExecutionOrder chooseExecutionOrder(int num) {
        return eor.chooseExecutionOrder(num);
    }

    /**
     * Method to choose the evaluation of the finished service
     *
     * @param answer
     */
    public void chooseEvaluation(boolean answer) {
        eva = new Evaluation(answer);
    }

    /**
     * Method that set to finished the service if the sol and just had been
     * added
     *
     * @param just
     * @param sol
     * @return if the just and sol had been added return true, if not return
     * false
     */
    public boolean reportEndService(String just, String sol) {
        if (eva.reportEndService(just, sol)) {
            eo.setToFinished();
            return true;
        }
        return false;
    }
    
    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
}
