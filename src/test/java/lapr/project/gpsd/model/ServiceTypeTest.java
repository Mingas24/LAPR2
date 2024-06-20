package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTypeTest {

    private ServiceType serviceTypeUnderTest;

    @BeforeEach
    public void setUp() {
        serviceTypeUnderTest = new ServiceType("Fixed", "servTypeId");
    }

    @Test
    public void testGetPath() {
        // Setup
        final String designation = "Fixed";
        final String expectedResult = "lapr.project.gpsd.model.FixedService";

        // Run the test
        final String result = serviceTypeUnderTest.getPath(designation);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewService() {
        // Setup
        final String id = "id";
        final String sDesc = "sDesc";
        final String fDesc = "fDesc";
        final double cost = 0.0;
        final Category cat = new Category("a","b");
        final Service expectedResult = new FixedService(id,sDesc,fDesc,cost,cat,serviceTypeUnderTest);

        // Run the test
        final Service result = serviceTypeUnderTest.newService(id, sDesc, fDesc, cost, cat);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHasID() {
        // Setup
        final String id = "servTypeId";

        // Run the test
        final boolean result = serviceTypeUnderTest.hasID(id);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Fixed";

        // Run the test
        final String result = serviceTypeUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
