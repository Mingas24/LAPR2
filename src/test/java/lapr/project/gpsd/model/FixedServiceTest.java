package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class FixedServiceTest {

    private Category category=new Category("catID", "description");

    private ServiceType st = new ServiceType("Fixed", "1");

    private FixedService fixedServiceUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        fixedServiceUnderTest = new FixedService("strID", "strShortDescription", "strFullDescription", 0.0, category, st);
    }

    @Test
    public void testSetDuration() {
        // Setup
        final Time duration = new Time(1);

        // Run the test
        fixedServiceUnderTest.setDuration(duration);

        // Verify the results
        assertEquals(duration, fixedServiceUnderTest.getDuration());
    }

    @Test
    public void testHasID() {
        // Setup
        final String strId = "strID";

        // Run the test
        final boolean result = fixedServiceUnderTest.hasID(strId);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testNeedsExtraData() {
        // Setup

        // Run the test
        final boolean result = fixedServiceUnderTest.needsExtraData();

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testSetExtraData() {
        // Setup
        final String data = "15:00";

        // Run the test
        fixedServiceUnderTest.setExtraData(data);

        // Verify the results
        assertFalse(fixedServiceUnderTest.isOtherAtributeFlag());
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = fixedServiceUnderTest;//same reference
        final Object equal=new FixedService("strID", "strShortDescription", "strFullDescription", 0.0, category, st);
        final Object nuller=null;
        final Object difClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = fixedServiceUnderTest.equals(o);
        final boolean resultEq=fixedServiceUnderTest.equals(equal);
        final boolean resultNul = fixedServiceUnderTest.equals(nuller);
        final boolean resultCla=fixedServiceUnderTest.equals(difClass);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "strID - strShortDescription - strFullDescription - 0.0 - Category: catID - description";


        // Run the test
        final String result = fixedServiceUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHasId() {
        // Setup
        final String Id = "strID";

        // Run the test
        final boolean result = fixedServiceUnderTest.hasId(Id);

        // Verify the results
        assertTrue(result);
    }
}
