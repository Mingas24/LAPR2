package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class ServiceProviderStatistics {
    
    public static List<Double> calculateServiceProvidersMeanRatingList(List<ServiceProvider> spList) {
        List<Double> allSpMeanRatingValueList = new ArrayList<>();
        for (ServiceProvider sp : spList){
            double meanRating = calculateMeanRating(sp);
            sp.setRating(meanRating);
            allSpMeanRatingValueList.add(meanRating);
        }
        return allSpMeanRatingValueList;
    }
    
    private static Double calculateMeanRating(ServiceProvider sp){
        double sumRating = 0;
        List <Rating> spRatingList = sp.getRatingRegistry().getRatings();
        if(!spRatingList.isEmpty()){
            for (Rating rat : spRatingList){
                sumRating += rat.getRate();
            }
            return (sumRating/spRatingList.size());
        }
        return 3.0;
    }

    public static double calculateDeviationEntireCompanyRating(double meanRatingCompany, List<Double> meanRatingsServiceProvider) {
        return Math.sqrt(calculateVariance(meanRatingCompany, meanRatingsServiceProvider));
    }
    
    private static double calculateVariance(double meanRatingCompany, List<Double> meanRatingsServiceProvider){
        List <Double> temp = new ArrayList<>();
        double sum = 0, numb;
        for (double rat : meanRatingsServiceProvider){
            numb = Math.pow((rat-meanRatingCompany), 2);
            temp.add(numb);
            sum += numb;
        }
        return (sum/meanRatingsServiceProvider.size());
       
    }
    
    public static double calculateEntireCompanyMeanRating(List<Double> allServiceProviderMeanRatingValueList){
        double sum = 0;
        for (double value : allServiceProviderMeanRatingValueList){
            sum += value;
        }
        return sum/allServiceProviderMeanRatingValueList.size();
    }  
}