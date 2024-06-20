package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CategoryTest {

    private Category categoryUnderTest;

    @BeforeEach
    public void setUp() {
        categoryUnderTest = new Category("catID", "description");
    }

    @Test
    public void testHasID() {
        // Setup
        final String catID = "catID";

        // Run the test
        final boolean result = categoryUnderTest.hasID(catID);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = categoryUnderTest.getCatID() + " - " + categoryUnderTest.getDescription();

        // Run the test
        final String result = categoryUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o =new Category("catID", "description");

        // Run the test
        final boolean result = categoryUnderTest.equals(o);

        // Verify the results
        assertTrue(result);
    }
}
