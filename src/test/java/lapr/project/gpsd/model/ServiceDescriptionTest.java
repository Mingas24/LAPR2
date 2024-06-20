package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceDescriptionTest {


    private Time duration=new Time(1);

    private Service service=new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id"));

    private ServiceDescription serviceDescriptionUnderTest;

    @BeforeEach
    public void setUp() {

        serviceDescriptionUnderTest = new ServiceDescription("description", duration, service);
    }

    @Test
    public void testGetCost() {
        // Setup
        final double expectedResult = 21;

        // Run the test
        final double result = serviceDescriptionUnderTest.getCost();

        // Verify the results
        assertEquals(expectedResult, result, 0.0001);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = serviceDescriptionUnderTest;
        final Object oEqual=new ServiceDescription("description", duration, service);
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = serviceDescriptionUnderTest.equals(o);
        final boolean rEqual=serviceDescriptionUnderTest.equals(oEqual);
        final boolean rNull=serviceDescriptionUnderTest.equals(oNull);
        final boolean rClass=serviceDescriptionUnderTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEqual);
        assertFalse(rNull);
        assertFalse(rClass);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Description = description\nDuration = 01:00:00 AM\nService = id\nStatus = WAITING";

        // Run the test
        final String result = serviceDescriptionUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetStatusAssigned() {
        // Setup

        // Run the test
        serviceDescriptionUnderTest.setStatusAssigned();

        // Verify the results
        assertEquals("ASSIGNED", serviceDescriptionUnderTest.getStatus());
    }
}
