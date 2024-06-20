package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.AssignProviderToRequest;
import lapr.project.gpsd.model.Company;

import java.util.Timer;
import java.util.TimerTask;

public class AssignProviderToRequestController {

    private Company com;

    public AssignProviderToRequestController() {
        //empty constructor
    }

    public void start() {
        this.com = ApplicationGPSD.getInstance().getCompany();
        Timer timer = new Timer(com.getTaskInfo());
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Started assignment task.");
                Company com = ApplicationGPSD.getInstance().getCompany();
                com = ApplicationGPSD.getInstance().getCompany();
                AssignProviderToRequest task = new AssignProviderToRequest(com.getTaskInfo(), com);
                com=task.assignProvidersToRequests();
            }
        }, com.getDelay(), com.getInterval());
    }
}
