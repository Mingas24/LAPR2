package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.*;
import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceRequestRegistryTest {

    private Bootstrap bs=new Bootstrap();
    private CategoryRegistry cr=new CategoryRegistry(bs.getCategoryRegistry());
    private ServiceTypeRegistry str=new ServiceTypeRegistry(bs.getServiceTypeRegistry());
    private ServiceRegistry sr=new ServiceRegistry(bs.getServiceRegistry(cr,str));
    private ClientRegistry clir=new ClientRegistry(bs.getClientRegistry());

    private ServiceRequestRegistry serviceRequestRegistryUnderTest;

    @BeforeEach
    public void setUp() {
        serviceRequestRegistryUnderTest = new ServiceRequestRegistry(bs.getServiceRequestRegistry(clir,sr));
    }

    @Test
    public void testNewRequest() {
        // Setup

        final PostalAddress address = new PostalAddress("add", new ZipCode("4000-008"), "town");
        final Client client = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
        final ServiceRequest expectedResult = new ServiceRequest(client, address);

        // Run the test
        final ServiceRequest result = serviceRequestRegistryUnderTest.newRequest(client, address);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateRequest() {
        // Setup
        final PostalAddress address = new PostalAddress("add", new ZipCode("4000-008"), "town");
        final Client client = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
        final ServiceRequest servReq = new ServiceRequest(client, address);

        final GeographicalAreaRegistry geoAreReg = new GeographicalAreaRegistry(bs.getGeographicalAreaRegistry(new ExternalServiceAdapter()));

        // Run the test
        final boolean result = serviceRequestRegistryUnderTest.validateRequest(servReq, geoAreReg);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testVerifyRequest1() {
        // Setup
        Client client = clir.getClientByTIN("100542369");
        PostalAddress postAddr = client.getPostalAddress("Rua D. João de França, nº 1", new ZipCode("4420-001"), "Gondomar");
        ServiceRequest servReq = new ServiceRequest(client, postAddr);
        Service serv = sr.getServiceById("01");
        String servDesc = "Close water tap";
        ServiceDescription sd = new ServiceDescription(servDesc, new Time(1,0),serv);
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 3), new Time(9,0));
        PreferedSchedule ps = new PreferedSchedule(0,new Date(2019, 6, 3), new Time(9,0));
        servReq.setCost(150);

        // Run the test
        final boolean result = serviceRequestRegistryUnderTest.verifyRequest(sd, ps);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testRegisterRequest() {
        // Setup
        final PostalAddress address = new PostalAddress("add", new ZipCode("4000-008"), "town");
        final Client client = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
        final ServiceRequest servReq = new ServiceRequest(client, address);

        final GeographicalAreaRegistry geoAreReg = new GeographicalAreaRegistry(bs.getGeographicalAreaRegistry(new ExternalServiceAdapter()));
        final int expectedResult = 7;

        // Run the test
        final int result = serviceRequestRegistryUnderTest.registerRequest(servReq, geoAreReg);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCreateRequestNumber() {
        // Setup
        final int expectedResult = 7;

        // Run the test
        final int result = serviceRequestRegistryUnderTest.createRequestNumber();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testAddRequest() {
        // Setup
        final PostalAddress address = new PostalAddress("add", new ZipCode("4000-008"), "town");
        final Client client = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
        final ServiceRequest servReq = new ServiceRequest(client, address);

        // Run the test
        serviceRequestRegistryUnderTest.addRequest(servReq);

        // Verify the results
       assertTrue(serviceRequestRegistryUnderTest.getRequestList().contains(servReq));
    }

    @Test
    public void testAddRequest1() {
        // Setup
        Service serv = sr.getServiceById("01");;
        final String strDesc = "help";

        // Run the test
        serviceRequestRegistryUnderTest.addRequest(serv, strDesc);

        // Verify the results
    }

    @Test
    public void testNotifyClient() {
        // Setup

        // Run the test
        serviceRequestRegistryUnderTest.notifyClient();

        // Verify the results
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = serviceRequestRegistryUnderTest;
        final Object equals=new ServiceRequestRegistry(bs.getServiceRequestRegistry(clir,sr));
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = serviceRequestRegistryUnderTest.equals(o);
        final boolean resultEq=serviceRequestRegistryUnderTest.equals(equals);
        final boolean resultNul=serviceRequestRegistryUnderTest.equals(nuller);
        final boolean resultCla=serviceRequestRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        Assertions.assertFalse(resultNul);
        Assertions.assertFalse(resultCla);
    }

    @Test
    public void testGetRequestListByClient() {
        // Setup
        Client client;
        PostalAddress postAddr;
        ServiceRequest servReq;
        Service serv;
        String servDesc;
        final Client cli = clir.getClientByTIN("210975020");
        final List<ServiceRequest> requests = new ArrayList<>();
        //5
        client = clir.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = sr.getServiceById("01");
        servDesc = "Water tap repair";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 7), new Time(19,0));
        servReq.setCost(140);
        requests.add(servReq);

        //6
        client = clir.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = sr.getServiceById("05");
        servDesc = "Repair vehicle engine and change oil";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6,8), new Time(9,0));
        servReq.setCost(120);
        requests.add(servReq);

        //7
        client = clir.getClientByTIN("210975020");
        postAddr = client.getPostalAddress("R. Cegonheira, nº 3", new ZipCode("4470-528"), "Maia");
        servReq = new ServiceRequest(client, postAddr);
        serv = sr.getServiceById("06");
        servDesc = "Gate paiting";
        servReq.addServiceDescription(serv, servDesc, new Time(1,0));
        servReq.addSchedule(new Date(2019, 6, 29), new Time(20,0));
        servReq.setCost(130);
        requests.add(servReq);

        // Run the test
        final List<ServiceRequest> result = serviceRequestRegistryUnderTest.getRequestListByClient(cli);

        // Verify the results
        assertEquals(requests, result);
    }

    @Test
    public void testGetAssignedServices() {
        // Setup
        final List<ServiceRequest> lsr = Arrays.asList();
        final List<AssignedService> expectedResult = Arrays.asList();

        // Run the test
        final List<AssignedService> result = serviceRequestRegistryUnderTest.getAssignedServices(lsr);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
