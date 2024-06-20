package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.CsvWriter;
import lapr.project.gpsd.model.ExecutionOrder;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.Writer;
import lapr.project.gpsd.model.WriterType;
import lapr.project.gpsd.model.XlsxWriter;
import lapr.project.gpsd.model.XmlWriter;
import lapr.project.gpsd.registry.ExecutionOrderRegistry;
import lapr.project.gpsd.registry.ServiceProviderRegistry;
import lapr.project.gpsd.registry.WriterTypeRegistry;
import lapr.project.gpsd.utils.Date;

/**
 *
 * @author José Araújo
 */
public class ExportExecutionOrdersController {

    private Company com;
    private ServiceProvider sp;
    private ExecutionOrderRegistry eor;
    private List<ExecutionOrder> leoInInterval;
    private List<Stage> windows;

    /**
     * Constructor
     */
    public ExportExecutionOrdersController() {
        this.com = ApplicationGPSD.getInstance().getCompany();
        this.windows = new ArrayList<>();
    }

    /**
     * Iniciates a new consult
     */
    public void newExportation() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        UserSession session = app.getPresentSession();
        String email = session.getUserEmail();
        ServiceProviderRegistry spr = this.com.getServiceProviderRegistry();
        this.sp = spr.getServiceProviderByEmail(email);
        eor = this.com.getExecutionOrderRegistry();
    }

    /**
     * Gets the execution orders of a serviceProvider between 2 dates
     *
     * @param initDate date
     * @param endDate
     */
    public void getExecutionOrders(Date initDate, Date endDate) {
        List<ExecutionOrder> leoBySp = eor.getPendingExecutionOrderByServiceProvider(sp);
        leoInInterval = eor.getExecutionOrderInInterval(leoBySp, initDate, endDate);
    }

    public void writeExecutionOrders(String extension) {
        WriterTypeRegistry wtr = this.com.getWriterTypeRegistry();
        WriterType wt = wtr.getWriterTypeByExtension(extension);
        Writer writer = wt.newWriter();
        writer.writeExecutionOrders(leoInInterval);
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
