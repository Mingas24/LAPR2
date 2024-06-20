package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RandomSchedulingTest {

    private RandomScheduling randomSchedulingUnderTest;

    final private List<ServiceRequest> lstRequests = new ArrayList<>();

    private ZipCode zipCodeCenter=new ZipCode("4000-009");

    private ExternalService api=new ExternalServiceAdapter();

    private GeographicalArea geoAre=new GeographicalArea("desig", 5, 10000, zipCodeCenter, api);

    private PostalAddress address = new PostalAddress("Porto", "4000-008", "bleh");

    private ServiceRequest sr=new ServiceRequest(1,new Date(2019,04,25), new Client("strName", "strTIN", "strTelephone", "strEmail", address), address, new ArrayList<>());

    private ServiceProvider sp = new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

    private Availability av = new Availability(new Date(2019,01,01), new Time(07), new Date(2019,12,31), new Time(23));

    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private Category ca = new Category("id", "desc");

    final private List<ServiceProvider> lstProviders = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        sp.addAvailability(av);
        sp.addCategory(ca);
        lstProviders.add(sp);
        sr.addSchedule(new Date(2019,04,25), new Time(15));
        sr.addServiceDescription(sd);
        sr.setGeoAre(geoAre);
        lstRequests.add(sr);
        randomSchedulingUnderTest = new RandomScheduling();
    }

    @Test
    public void testCheckBestSchedule() {
        // Setup
        final ServiceProvider provider = null;
        final List<PreferedSchedule> schedules = Arrays.asList();
        final Time duration = null;
        final List<ServiceRequest> requests = Arrays.asList();
        final PreferedSchedule expectedResult = null;

        // Run the test
        final PreferedSchedule result = randomSchedulingUnderTest.checkBestSchedule(provider, schedules, duration, requests);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCheckOverlap() {
        // Setup
        final List<ServiceRequest> lstRequest = new ArrayList<>(this.lstRequests);
        final PreferedSchedule sched = this.sr.getSchedules().get(0);
        final Time duration = new Time(1);
        final ServiceProvider provider = this.sp;

        // Run the test
        final boolean result = randomSchedulingUnderTest.checkOverlap(lstRequest, sched, duration, provider);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testCheckGeographicalAvailability() {
        // Setup
        final ServiceProvider provider = this.sp;
        final ServiceRequest request = this.sr;

        // Run the test
        final boolean result = randomSchedulingUnderTest.checkGeographicalAvailability(provider, request);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = new RandomScheduling();
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = randomSchedulingUnderTest.equals(o);
        final boolean resultNull = randomSchedulingUnderTest.equals(nuller);
        final boolean resultClass=randomSchedulingUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertFalse(resultNull);
        assertFalse(resultClass);
    }
}
