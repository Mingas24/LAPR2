package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.FirstComeFirstServe;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.ServiceProviderApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationRegistryTest {

    private Bootstrap bs=new Bootstrap();
    private ApplicationRegistry applicationRegistryUnderTest;
    private CategoryRegistry cReg=new CategoryRegistry(bs.getCategoryRegistry());

    @BeforeEach

    public void setUp() {
        applicationRegistryUnderTest = new ApplicationRegistry(bs.getApplicationRegistry(cReg));
    }

    @Test
    public void testGetApplicationByTin() {
        // Setup
        final String tin = "500324896";
        final String tinNotFound="8135978974321";
        final ServiceProviderApplication expectedResult = applicationRegistryUnderTest.getApplicationsList().get(0);

        // Run the test
        final ServiceProviderApplication result = applicationRegistryUnderTest.getApplicationByTin(tin);
        final ServiceProviderApplication resultNotFound=applicationRegistryUnderTest.getApplicationByTin(tinNotFound);

        // Verify the results
        assertEquals(expectedResult, result);
        assertEquals(null, resultNotFound);
    }

    @Test
    public void testValidateApplication() {
        // Setup
        final ServiceProviderApplication appl = applicationRegistryUnderTest.getApplicationsList().get(0);

        // Run the test
        final boolean result = applicationRegistryUnderTest.validateApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRegisterApplication() {
        // Setup
        final ServiceProviderApplication appl = new ServiceProviderApplication("Cristiano Ronaldo", "999999999", "977777777", "a@a.a", new PostalAddress("R. Acores", "4415-995", "Acores"));

        // Run the test
        final boolean result = applicationRegistryUnderTest.registerApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddApplication() {
        // Setup
        final ServiceProviderApplication appl = new ServiceProviderApplication("Cristiano Ronaldo", "999999999", "977777777", "a@a.a", new PostalAddress("R. Acores", "4415-995", "Acores"));;

        // Run the test
        final boolean result = applicationRegistryUnderTest.addApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = applicationRegistryUnderTest;
        final Object equals=new ApplicationRegistry(bs.getApplicationRegistry(cReg));
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = applicationRegistryUnderTest.equals(o);
        final boolean resultEq=applicationRegistryUnderTest.equals(equals);
        final boolean resultNul=applicationRegistryUnderTest.equals(nuller);
        final boolean resultCla=applicationRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }

}
