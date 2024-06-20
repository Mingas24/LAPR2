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
import lapr.project.gpsd.model.ServiceProvider;
import lapr.project.gpsd.model.ServiceProviderLabel;
import lapr.project.gpsd.model.ServiceProviderStatistics;
import lapr.project.gpsd.registry.ServiceProviderRegistry;

/**
 *
 * @author ADMIN
 */
public class EvaluateServiceProviderController {
    private ApplicationGPSD app;
    private Company company;
    private ServiceProviderRegistry servProReg;
    private List<ServiceProvider> spList;
    private ServiceProviderStatistics sps;
    private List<Double> allServiceProviderMeanRatingValueList;
    private List<Stage> windows;
    
    public EvaluateServiceProviderController(){
        this.app = ApplicationGPSD.getInstance();
        this.company = app.getCompany();
        this.windows = new ArrayList<>();
    }
    
    public List<Double> calculateServiceProvidersMeanRatingList(){
        this.servProReg = company.getServiceProviderRegistry();
        this.spList = this.servProReg.getServiceProviderList();
        List<Double> allSpMeanRatingValueList = this.sps.calculateServiceProvidersMeanRatingList(this.spList);
        this.company.setServiceProviderMeanRatingList(allSpMeanRatingValueList);
        return this.allServiceProviderMeanRatingValueList = allSpMeanRatingValueList;
    }
    
    public double calculateEntireCompanyMeanRating (List<Double> allServiceProviderMeanRatingValueList){
        double meanRatingCompany = this.sps.calculateEntireCompanyMeanRating(allServiceProviderMeanRatingValueList);
        this.company.setEntireCompanyServiceProviderMeanRating(meanRatingCompany);
        return meanRatingCompany;
        
    }
    
    public void calculateServiceProviderLabels(double meanRatingCompany, List<Double> meanRatingsServiceProvider){
        double deviation = this.sps.calculateDeviationEntireCompanyRating(meanRatingCompany, meanRatingsServiceProvider);
        double diffValue = meanRatingCompany - deviation;
        double sumValue = meanRatingCompany + deviation;
        for(ServiceProvider servPro : spList){
            ServiceProviderLabel spLabel = new ServiceProviderLabel(diffValue, sumValue, servPro);
            servPro.setLabel(spLabel);
        }
    }
    
    public double getServiceProviderMeanRating (ServiceProvider sp){
        return sp.getRating();
    }
    
    public double getEntireCompanyMeanRating(){
        return company.getEntireCompanyServiceProviderMeanRating();
    }
    
    public void changeServiceProviderLabel(ServiceProvider sp, String spLabel){
        sp.setLabel(spLabel);
    }

    public List<Double> getAllServiceProviderMeanRatingValueList() {
        return allServiceProviderMeanRatingValueList;
    }

    public List<ServiceProvider> getSpList() {
        return spList;
    }
    
    public void addToListOfWindows(Stage stage) {
        this.windows.add(stage);
    }

    public void closeWindows() {
        for (Stage stage : this.windows) {
            stage.close();
        }
    }
    
    public String getServiceProvidersNames(){
        String str = "Service Providers:\n\n";
        
        for (ServiceProvider sp: this.spList)
            str += sp.getAbrevName() + "\n";
        
        return str;
    }
    
    public String getServiceProvidersRates(){
        String str = "Ratings:\n\n";
        
        for (ServiceProvider sp: this.spList)
            str += sp.getRating() + "\n";
        
        return str;
    }
    
    public String getServiceProvidersLabels(){
        String str = "Labels:\n\n";
        
        for (ServiceProvider sp: this.spList)
            str += sp.getLabel().getLabel() + "\n";
        
        return str;
    }
}
