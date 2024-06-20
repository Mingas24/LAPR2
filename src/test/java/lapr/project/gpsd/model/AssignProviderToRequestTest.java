package lapr.project.gpsd.model;

import lapr.project.gpsd.controller.ApplicationGPSD;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AssignProviderToRequestTest {


    private Company com= ApplicationGPSD.getInstance().getCompany();

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

    private AssignProviderToRequest assignProviderToRequestUnderTest;

    @BeforeEach
    public void setUp() {
        sp.addAvailability(av);
        sp.addCategory(ca);
        lstProviders.add(sp);
        sr.addSchedule(new Date(2019,04,25), new Time(15));
        sr.addServiceDescription(sd);
        sr.setGeoAre(geoAre);
        lstRequests.add(sr);
        assignProviderToRequestUnderTest = new AssignProviderToRequest("info", com);
        assignProviderToRequestUnderTest.assignProvidersToRequests();
    }

    @Test
    public void testAssignProvidersToRequests() {
        // Setup


        // Run the test
        assignProviderToRequestUnderTest.assignProvidersToRequests();

        // Verify the results
    }

    @Test
    public void testSelectAlgorithm() {
        // Setup
        final SchedulingAlgorithm expectedResultFCFS = new FirstComeFirstServe();
        final SchedulingAlgorithm expectedResultRS = new RandomScheduling();

        // Run the test
        final SchedulingAlgorithm resultFCFS = assignProviderToRequestUnderTest.selectAlgorithm(15);
        final SchedulingAlgorithm resultRS = assignProviderToRequestUnderTest.selectAlgorithm(40);

        // Verify the results
        assertTrue(resultFCFS instanceof FirstComeFirstServe);
        assertTrue(resultRS instanceof RandomScheduling);
    }

    @Test
    void checkProviderInArea() {
        //Setup

        //Run the test

        //Verify the results
        assertEquals(lstRequests, assignProviderToRequestUnderTest.checkProviderInArea(lstRequests,lstProviders));
    }
}
