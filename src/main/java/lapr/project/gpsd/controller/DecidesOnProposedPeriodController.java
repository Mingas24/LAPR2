package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.AssignedService;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.utils.Constants;
import lapr.project.gpsd.model.ServiceRequest;
import lapr.project.gpsd.model.AssignedSchedule;
import lapr.project.gpsd.model.ServiceDescription;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.registry.ClientRegistry;
import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.ServiceRequestRegistry;

/**
 *
 * @author Jee ^^
 */
public class DecidesOnProposedPeriodController {

    private Company com;
    private Client cli;
    private ExecutionOrderRegistry eor;
    private List<ServiceProvider> lsp;
    private ServiceRequest sr;
    private List<Stage> windows;
    
    /**
     * Constructor
     */
    public DecidesOnProposedPeriodController() {
        if (!ApplicationGPSD.getInstance().getPresentSession().isLoggedInWithRole(Constants.ROLE_CLIENT)) {
            throw new IllegalStateException("Unauthorized User!");
        }
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Obtains all the client's service request's assign services
     *
     * @return List with all client's service request's assign services
     */
    public List<ServiceRequest> getServiceRequests() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        UserSession session = app.getPresentSession();
        String email = session.getUserEmail();
        ClientRegistry cr = this.com.getClientRegistry();
        this.cli = cr.getClientByEmail(email);
        this.eor = this.com.getExecutionOrderRegistry();
        this.lsp = new ArrayList<>();
        ServiceRequestRegistry rsr = this.com.getServiceRequestRegistry();
        return rsr.getRequestListByClient(this.cli);

    }

    public List<AssignedService> getAssignedServices(ServiceRequest sr) {
        this.sr = sr;
        return sr.getAssignedServices();
    }

    /**
     * obtains the schedule assigned to a service
     *
     * @param as AssignedService
     * @return schedule assigned to the service
     */
    public AssignedSchedule getSchedule(AssignedService as) {
        return as.getSchedule();
    }

    /**
     * sets the service as accepted and generate an execution order
     *
     * @param as AssignedService
     */
    public void acceptAssignService(AssignedService as) {
        ServiceProvider sp = as.getServiceProvider();
        ServiceDescription sd = as.getServiceDescription();
        if (this.eor.newExecutionOrder(as, sr)) {
            this.lsp.add(as.getServiceProvider());
            as.setStatus(Constants.STATUS_ACCEPTED);
        }
    }

    /**
     * sets the service as rejected
     *
     * @param as AssignedService
     */
    public void rejectAssignService(AssignedService as) {
        as.setStatus(Constants.STATUS_REJECTED);
    }

    /**
     * Notifies all the service providers who has new execution orders assigned
     * to him
     */
    public void notifiesServiceProvider() {
        this.com.notifiesServiceProvider(this.lsp);
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
