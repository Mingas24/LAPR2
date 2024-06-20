package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.FirstComeFirstServe;
import lapr.project.gpsd.model.Service;
import lapr.project.gpsd.model.ServiceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ServiceRegistryTest {
    private Bootstrap bs = new Bootstrap();
    private ServiceTypeRegistry str=new ServiceTypeRegistry(bs.getServiceTypeRegistry());
    private CategoryRegistry cr=new CategoryRegistry(bs.getCategoryRegistry());
    private ServiceRegistry serviceRegistryUnderTest;

    @BeforeEach
    public void setUp() {
        serviceRegistryUnderTest = new ServiceRegistry(bs.getServiceRegistry(cr,str));
    }

    @Test
    public void testGetServiceById() {
        // Setup
        final String idServ = "01";
        ServiceType servType = str.getServiceTypeById("FixedService");
        Service serv1 = servType.newService("01", "Light plumbing", "Intall water tap", 100.0, cr.getCategoryByID("01"));
        serv1.setExtraData("1:0");
        final Service expectedResult = serv1;

        // Run the test
        final Service result = serviceRegistryUnderTest.getServiceById(idServ);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetServicesOfCategory() {
        // Setup
        final String catID = "04";
        ServiceType servType = str.getServiceTypeById("ExpandedService");
        Service serv4 = servType.newService("04", "Prepare dinner", "Prepare dinner and clean kitchen", 80.0, cr.getCategoryByID("04"));

        final List<Service> expectedResult = new ArrayList<>();
        expectedResult.add(serv4);

        // Run the test
        final List<Service> result = serviceRegistryUnderTest.getServicesOfCategory(catID);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRegisterService() {
        // Setup
        ServiceType servType = str.getServiceTypeById("ExpandedService");
        final Service serv = servType.newService("04", "Clean house", "Clean whole house", 30.0, cr.getCategoryByID("04"));


        // Run the test
        final boolean result = serviceRegistryUnderTest.registerService(serv);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testValidateService() {
        // Setup
        ServiceType servType = str.getServiceTypeById("ExpandedService");
        final Service serv = servType.newService("04", "Clean house", "Clean whole house", 30.0, cr.getCategoryByID("04"));


        // Run the test
        final boolean result = serviceRegistryUnderTest.validateService(serv);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = serviceRegistryUnderTest;
        final Object equals=new ServiceRegistry(bs.getServiceRegistry(cr, str));
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = serviceRegistryUnderTest.equals(o);
        final boolean resultEq=serviceRegistryUnderTest.equals(equals);
        final boolean resultNul=serviceRegistryUnderTest.equals(nuller);
        final boolean resultCla=serviceRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }
}
