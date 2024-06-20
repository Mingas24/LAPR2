package lapr.project.gpsd.controller;

import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;
import lapr.project.authorization.model.UserSession;
import lapr.project.gpsd.model.Availability;
import lapr.project.gpsd.model.Company;
import lapr.project.gpsd.model.DailyAvailabilityList;
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.registry.ServiceProviderRegistry;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;

/**
 *
 * @author Jee ^_^
 */
public class IndicateDailyAvailabilityController {

    private Company com;
    private ServiceProvider sp;
    private DailyAvailabilityList dal;
    private Availability avai;

    /**
     * Constructor
     */
    public IndicateDailyAvailabilityController() {
        this.com = ApplicationGPSD.getInstance().getCompany();
    }

    /**
     * Iniciates a identification of daily availabilities
     */
    public void identifyNewAvailabilities() {
        ApplicationGPSD app = ApplicationGPSD.getInstance();
        UserSession session = app.getPresentSession();
        String email = session.getUserEmail();
        ServiceProviderRegistry spr = this.com.getServiceProviderRegistry();
        this.sp = spr.getServiceProviderByEmail(email);
        this.dal = sp.getDailyAvailabilityList();
    }

    /**
     * Creates a new availability
     *
     * @param initDate Inicial date
     * @param initTime Iniciat time
     * @param endDate End date
     * @param endTime End time
     */
    public void newAvailability(Date initDate, Time initTime, Date endDate, Time endTime) {
        this.avai = dal.newAvailability(initDate, initTime, endDate, endTime);
    }

    /**
     * Regists the availability in the Service provider dailyAvailabilityList
     *
     * @return true if its sucessfully added
     */
    public boolean registerAvailability() {
        return this.dal.registerAvailability(avai);
    }

    public List<Time> getWorkingHours() {
        return new ArrayList<Time>() {
            {
                add(new Time(6, 00));
                add(new Time(6, 30));
                add(new Time(7, 00));
                add(new Time(7, 30));
                add(new Time(8, 00));
                add(new Time(8, 30));
                add(new Time(9, 00));
                add(new Time(9, 30));
                add(new Time(10, 00));
                add(new Time(10, 30));
                add(new Time(11, 00));
                add(new Time(11, 30));
                add(new Time(12, 00));
                add(new Time(12, 30));
                add(new Time(13, 00));
                add(new Time(13, 30));
                add(new Time(14, 00));
                add(new Time(14, 30));
                add(new Time(15, 00));
                add(new Time(15, 30));
                add(new Time(16, 00));
                add(new Time(16, 30));
                add(new Time(17, 00));
                add(new Time(17, 30));
                add(new Time(18, 00));
                add(new Time(18, 30));
                add(new Time(19, 00));
                add(new Time(19, 30));
                add(new Time(20, 00));
                add(new Time(20, 30));
                add(new Time(21, 00));
                add(new Time(21, 30));
                add(new Time(22, 00));
                add(new Time(22, 30));
                add(new Time(23, 00));
                add(new Time(23, 30));
            }
        };
    }
}
