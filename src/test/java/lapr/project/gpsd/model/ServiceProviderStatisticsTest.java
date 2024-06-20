package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceProviderStatisticsTest {
    private PostalAddress address=new PostalAddress("a",new ZipCode("4470-057"),"r");

    private ServiceProvider serviceProviderUnderTest;
    @Test
    public void testCalculateServiceProvidersMeanRatingList() {
        // Setup
        final List<ServiceProvider> spList = new ArrayList<>();
        serviceProviderUnderTest = new ServiceProvider("name", "abrevName", "tin", "mechNumber", "telephone", "email", address, new ArrayList<>(), new ArrayList<>());
        serviceProviderUnderTest.addRating(new Rating(4));
        spList.add(serviceProviderUnderTest);
        final List<Double> expectedResult = new ArrayList<>();
        expectedResult.add(new Double(4));

        // Run the test
        final List<Double> result = ServiceProviderStatistics.calculateServiceProvidersMeanRatingList(spList);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculateDeviationEntireCompanyRating() {
        // Setup
        final double meanRatingCompany = 3;
        final List<Double> meanRatingsServiceProvider = new ArrayList<>();
        meanRatingsServiceProvider.add(new Double(3));
        final double expectedResult = 0;

        // Run the test
        final double result = ServiceProviderStatistics.calculateDeviationEntireCompanyRating(meanRatingCompany, meanRatingsServiceProvider);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testCalculateEntireCompanyMeanRating() {
        // Setup
        final List<Double> allServiceProviderMeanRatingValueList = new ArrayList<>();
        allServiceProviderMeanRatingValueList.add(new Double(3));
        final double expectedResult = 3;

        // Run the test
        final double result = ServiceProviderStatistics.calculateEntireCompanyMeanRating(allServiceProviderMeanRatingValueList);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }
}
