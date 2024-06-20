package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExpandableServiceTest {


    private Category category=new Category("catID", "description");

    private ServiceType st = new ServiceType("Expandable", "1");

    private ExpandableService expandableServiceUnderTest;

    @BeforeEach
    public void setUp() {

        expandableServiceUnderTest = new ExpandableService("strID", "strShortDescription", "strFullDescription", 5, category, st);
    }

    @Test
    public void testHasID() {
        // Setup
        final String strId = "strId";

        // Run the test
        final boolean result = expandableServiceUnderTest.hasID(strId);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testNeedsExtraData() {
        // Setup

        // Run the test
        final boolean result = expandableServiceUnderTest.needsExtraData();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testSetExtraData() {
        // Setup
        final String data = "data";

        // Run the test
        expandableServiceUnderTest.setExtraData(data);

        // Verify the results
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = null;
        final Object equal=new ExpandableService("strID", "strShortDescription", "strFullDescription", 5, category, st);
        final Object difClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = expandableServiceUnderTest.equals(o);
        final boolean resultEq=expandableServiceUnderTest.equals(equal);
        final boolean resultCl=expandableServiceUnderTest.equals(difClass);

        // Verify the results
        assertFalse(result);
        assertTrue(resultEq);
        assertFalse(resultCl);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "strID - strShortDescription - strFullDescription - "+ expandableServiceUnderTest.getHourlyCost() +" - Category: catID - description";

        // Run the test
        final String result = expandableServiceUnderTest.toString();

        //Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHasId() {
        // Setup
        final String Id = "strID";

        // Run the test
        final boolean result = expandableServiceUnderTest.hasId(Id);

        // Verify the results
        assertTrue(result);
    }
}
