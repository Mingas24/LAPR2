package lapr.project.gpsd.model;

import lapr.project.gpsd.registry.GeographicalAreaRegistry;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceRequestTest {


    private Date date=new Date(2019,04,25);

    private PostalAddress address= new PostalAddress("add", new ZipCode("4000-008"), "town");

    private Client clientUnderTest;

    private ServiceRequest serviceRequestUnderTest;

    private Time duration=new Time(1);

    private Service service=new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id"));

    private ServiceDescription serviceDescriptionUnderTest;

    private ServiceProvider sp = new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private AssignedSchedule sch = new AssignedSchedule(new Date(2019,12,31), new Time(15), new Time(16));

    private AssignedService assignedServiceUnderTest;

    @BeforeEach
    public void setUp() {
        clientUnderTest = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
        serviceRequestUnderTest = new ServiceRequest(0, date, clientUnderTest, address, Arrays.asList());
        assignedServiceUnderTest = new AssignedService(sp, sd, sch);
        serviceDescriptionUnderTest = new ServiceDescription("description", duration, service);

    }

    @Test
    public void testValidatesServiceRequest() {
        // Setup
        final ServiceDescription description = this.serviceDescriptionUnderTest;

        // Run the test
        final boolean result = serviceRequestUnderTest.validatesServiceRequest(description);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddServiceDescription() {
        // Setup
        final ServiceDescription descAdd = this.serviceDescriptionUnderTest;

        // Run the test
        final boolean result = serviceRequestUnderTest.addServiceDescription(descAdd);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddServiceDescription1() {
        // Setup
        final Service serv = this.service;
        final String desc = "new";
        final Time duration = this.duration;

        // Run the test
        final boolean result = serviceRequestUnderTest.addServiceDescription(serv, desc, duration);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testAddSchedule() {
        // Setup
        final Date date = this.date;
        final Time time = new Time(15);

        // Run the test
        final boolean result = serviceRequestUnderTest.addSchedule(date, time);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddSchedule1() {
        // Setup
        final PreferedSchedule sche = new PreferedSchedule(0,this.date, new Time(15));

        // Run the test
        final boolean result = serviceRequestUnderTest.addSchedule(sche);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddAssignedService() {
        // Setup
        final AssignedService assignedService = this.assignedServiceUnderTest;

        // Run the test
        final boolean result = serviceRequestUnderTest.addAssignedService(assignedService);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCalculateCost() {
        // Setup
        List<GeographicalArea> list = new ArrayList<>();
        ZipCode zipCodeCenter=new ZipCode("4470-057");

        ExternalService api=new ExternalServiceAdapter();

        GeographicalArea geographicalAreaUnderTest=new GeographicalArea("desig", 0.0, 0.0f, zipCodeCenter, api);
        list.add(geographicalAreaUnderTest);
        final GeographicalAreaRegistry geoAreReg = new GeographicalAreaRegistry(list);
        final double expectedResult = 0.0;

        // Run the test
        final double result = serviceRequestUnderTest.calculateCost(geoAreReg);

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testToString() {
        // Setup
        String eResult = "";
        eResult += "Postal Address - " + serviceRequestUnderTest.getPostalAddress().toString();
        eResult += "\n";
        eResult += "\n";
        eResult += "\n";
        eResult += "Total Cost: " + serviceRequestUnderTest.getTotalCost();

        // Run the test
        final String result = serviceRequestUnderTest.toString();

        // Verify the results
        assertEquals(eResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = serviceRequestUnderTest;
        final Object oEqual=new ServiceRequest(0, date, clientUnderTest, address, Arrays.asList());
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = serviceRequestUnderTest.equals(o);
        final boolean rEqual=serviceRequestUnderTest.equals(oEqual);
        final boolean rNull=serviceRequestUnderTest.equals(oNull);
        final boolean rClass=serviceRequestUnderTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEqual);
        assertFalse(rNull);
        assertFalse(rClass);
    }

    @Test
    public void testHasClient() {
        // Setup
        final Client cli = clientUnderTest;

        // Run the test
        final boolean result = serviceRequestUnderTest.hasClient(cli);

        // Verify the results
        assertTrue(result);
    }
}
