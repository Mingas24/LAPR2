package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class LimitedServiceTest {

    private Category category=new Category("catID", "description");

    private ServiceType st = new ServiceType("Limited", "1");

    private LimitedService limitedServiceUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        limitedServiceUnderTest = new LimitedService("strID", "strShortDescription", "strFullDescription", 0.0, category, st);
    }

    @Test
    public void testHasID() {
        // Setup
        final String strId = "strId";

        // Run the test
        final boolean result = limitedServiceUnderTest.hasID(strId);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testNeedsExtraData() {
        // Setup

        // Run the test
        final boolean result = limitedServiceUnderTest.needsExtraData();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testSetExtraData() {
        String data = "data";
        limitedServiceUnderTest.setExtraData(data);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = limitedServiceUnderTest;//reference
        final Object oNull=null;
        final Object oEqual=new LimitedService("strID", "strShortDescription", "strFullDescription", 0.0, category, st);
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = limitedServiceUnderTest.equals(o);
        final boolean rNull=limitedServiceUnderTest.equals(oNull);
        final boolean rEqual=limitedServiceUnderTest.equals(oEqual);
        final boolean rClass=limitedServiceUnderTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertFalse(rNull);
        assertTrue(rEqual);
        assertFalse(rClass);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "strID - strShortDescription - strFullDescription - 0.0 - Category: catID - description";

        // Run the test
        final String result = limitedServiceUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testHasId() {
        // Setup
        final String Id = "strID";

        // Run the test
        final boolean result = limitedServiceUnderTest.hasId(Id);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void hasID() {
        assertTrue(limitedServiceUnderTest.hasID("strID"));
        assertFalse(limitedServiceUnderTest.hasID("not the actual id"));
    }

    @Test
    void getID() {
        assertEquals("strID", limitedServiceUnderTest.getID());
    }

    @Test
    void getStrShortDescription() {
        assertEquals("strShortDescription", limitedServiceUnderTest.getStrShortDescription());
    }

    @Test
    void getStrFullDescription() {
        assertEquals("strFullDescription", limitedServiceUnderTest.getStrFullDescription());
    }

    @Test
    void getCategory() {
        assertEquals(category,limitedServiceUnderTest.getCategory());
    }

    @Test
    void getHourlyCost() {
        assertEquals(0,limitedServiceUnderTest.getHourlyCost());
    }

    @Test
    void needsExtraData() {
        assertFalse(limitedServiceUnderTest.needsExtraData());
    }

    @Test
    void getOtherAtributes() {
        assertEquals("No Other Atributes", limitedServiceUnderTest.getOtherAtributes());
    }

    @Test
    void getServiceType() {
        assertEquals(st,limitedServiceUnderTest.getServiceType());
    }

    @Test
    void hasId() {
        assertTrue(limitedServiceUnderTest.hasID("strID"));
    }
}
