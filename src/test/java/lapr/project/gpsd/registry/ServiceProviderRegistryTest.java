package lapr.project.gpsd.registry;

import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.*;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServiceProviderRegistryTest {
    private Bootstrap bs=new Bootstrap();
    private ServiceProviderRegistry serviceProviderRegistryUnderTest;
    private List<GeographicalArea> areas;
    private List<Category> categories;
    private GeographicalAreaRegistry gar;
    private CategoryRegistry cr;
    private ExternalServiceAdapter api = new ExternalServiceAdapter();

    @BeforeEach
    public void setUp() {
        gar=new GeographicalAreaRegistry(bs.getGeographicalAreaRegistry(api));
        cr=new CategoryRegistry(bs.getCategoryRegistry());
        serviceProviderRegistryUnderTest = new ServiceProviderRegistry(bs.getServiceProviderRegistry(gar, cr));
        categories=new ArrayList<>();
        areas=new ArrayList<>();
    }


    @Test
    public void testGetServiceProviderByEmail() {
        // Setup
        final String email = "aPadrao@gmail.com";
        ServiceProvider ex=serviceProviderRegistryUnderTest.getServiceProviderList().get(0);

        // Run the test
        final ServiceProvider result = serviceProviderRegistryUnderTest.getServiceProviderByEmail(email);

        // Verify the results
        assertEquals(ex, result);
    }

    @Test
    public void testGetCompatibleServiceProviders() {
        // Setup
        final List<PreferedSchedule> requestedServicesSchedule = new ArrayList<>();
        final List<ServiceProvider> expectedResult = new ArrayList<>();
        PreferedSchedule ps=new PreferedSchedule(0,new Date(2019,06,18),new Time(22));
        requestedServicesSchedule.add(ps);
        expectedResult.add(serviceProviderRegistryUnderTest.getServiceProviderList().get(2));

        // Run the test
        final List<ServiceProvider> result = serviceProviderRegistryUnderTest.getCompatibleServiceProviders(requestedServicesSchedule);

        // Verify the results
        assertEquals(expectedResult, result);
    }


    @Test
    public void testRegisterServiceProvider() {
        // Setup
        final ServiceProvider expectedResult = new ServiceProvider("Cristiano Ronaldo", "cr7", "999999999", "41241241","977777777","a@a.a", new PostalAddress("R. Acores", "4415-995", "Acores"), new ArrayList<>());
        final AuthorizationFacade af = new AuthorizationFacade();

        // Run the test
        final boolean result = serviceProviderRegistryUnderTest.registerServiceProvider(expectedResult, af);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = serviceProviderRegistryUnderTest;
        final Object equals=new ServiceProviderRegistry(bs.getServiceProviderRegistry(gar, cr));
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = serviceProviderRegistryUnderTest.equals(o);
        final boolean resultNul=serviceProviderRegistryUnderTest.equals(nuller);
        final boolean resultCla=serviceProviderRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }
}
