package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceProviderTest {


    private PostalAddress address=new PostalAddress("a",new ZipCode("4470-057"),"r");

    private ServiceProvider serviceProviderUnderTest;

    @BeforeEach
    public void setUp() {

        serviceProviderUnderTest = new ServiceProvider("name", "abrevName", "tin", "mechNumber", "telephone", "email", address, new ArrayList<>(), new ArrayList<>());
    }

    @Test
    public void testHasEmail() {
        // Setup
        final String email = "email";

        // Run the test
        final boolean result = serviceProviderUnderTest.hasEmail(email);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetAvailabilityInDate() {
        // Setup
        final Date date = null;
        final Time[] expectedResult = new Time[2];
        expectedResult[0]=new Time(0);
        expectedResult[1]=new Time(0);

        // Run the test
        final Time[] result = serviceProviderUnderTest.getAvailabilityInDate(date);

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }

    @Test
    public void testIsCompatible() {
        // Setup
         Date date=new Date(2019,04,25);

         Time time=new Time(15);

         PreferedSchedule preferedScheduleUnderTest=new PreferedSchedule(0,date,time);
         serviceProviderUnderTest.addAvailability(new Availability(new Date(2019,01,01), new Time(12), new Date(2019,12,31), new Time(21)));

        // Run the test
        final boolean result = serviceProviderUnderTest.isCompatible(preferedScheduleUnderTest);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddGeographicalArea() {
        // Setup
        final ZipCode zipCodeCenter=new ZipCode("4470-057");

        final ExternalService api=new ExternalServiceAdapter();

        final GeographicalArea geographicalAreaUnderTest=new GeographicalArea("desig", 0.0, 0.0f, zipCodeCenter, api);

        // Run the test
        final boolean result = serviceProviderUnderTest.addGeographicalArea(geographicalAreaUnderTest);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddAvailability() {
        // Setup
        final Availability av = new Availability(new Date(2019,01,01), new Time(12), new Date(2019,12,31), new Time(21));
        ;

        // Run the test
        final boolean result = serviceProviderUnderTest.addAvailability(av);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddCategory() {
        // Setup
        final Category ca = new Category("catID", "description");

        // Run the test
        final boolean result = serviceProviderUnderTest.addCategory(ca);

        // Verify the results
        assertTrue(result);
    }
}
