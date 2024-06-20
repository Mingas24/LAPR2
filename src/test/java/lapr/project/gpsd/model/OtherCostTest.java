package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OtherCostTest {

    private OtherCost otherCostUnderTest;
    private double value=0.0;

    @BeforeEach
    public void setUp() {
        otherCostUnderTest = new OtherCost("designation", value);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Designation - " + "designation" +
                "\nValue - " + value;

        // Run the test
        final String result = otherCostUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = otherCostUnderTest;
        final Object oEqual=new OtherCost("designation", value);
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = otherCostUnderTest.equals(o);
        final boolean rEqual=otherCostUnderTest.equals(oEqual);
        final boolean rNull=otherCostUnderTest.equals(oNull);
        final boolean rClass=otherCostUnderTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEqual);
        assertFalse(rNull);
        assertFalse(rClass);
    }
}
