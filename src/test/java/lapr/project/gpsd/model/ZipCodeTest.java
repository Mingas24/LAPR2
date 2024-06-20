package lapr.project.gpsd.model;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.BeforeClass;

public class ZipCodeTest {
    ZipCode zipCodeUnderTest = new ZipCode("4000-000");;
    ZipCode zipCodeAux=new ZipCode("4470-057");

    @Test
    public void testIsEmptyFalse() {
        // Setup
        // Run the test
        final boolean result = zipCodeUnderTest.isEmpty(zipCodeUnderTest);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testIsEmptyTrue() {
        // Setup
        ZipCode zip=null;
        // Run the test
        final boolean result = zipCodeUnderTest.isEmpty(zip);

        // Verify the results
        assertTrue(result);
    }





    @Test
    public void testEqualsEqual() {
        // Setup
        final ZipCode zipTest = new ZipCode("4000-000",0.0f,0.0f);

        // Run the test
        final boolean result = zipCodeUnderTest.equals(zipTest);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEqualsNull(){
        // Setup
        final ZipCode zipTestNull = null;

        // Run the test
        final boolean result = zipCodeUnderTest.equals(zipTestNull);

        // Verify the results
        assertFalse(result);
    }


    /**
     * Test of toString method, of class ZipCode.
     */
    @Test
    public void testToString() {
        //Setup
        String expResult = "4000-000";
        //Run the test
        String result = zipCodeUnderTest.toString();
        //Verify results
        assertEquals(expResult, result);
    }


    @Test
    void getCodPostal() {
        //Setup
        String expectedResult = "4000-000";

        //Run the test
        String result=zipCodeUnderTest.getZipCode();

        //Verify the results
        assertEquals(expectedResult,result);
    }

    @Test
    void setCodPostal() {
        //Setup
        String expectedResult="4000-000";
        //Run the test
        zipCodeAux.setCodPostal("4000-000");
        String result=zipCodeAux.getZipCode();
        //Verify results
        assertEquals(expectedResult,result);
    }

    @Test
    void isEmptyFalse() {
        assertFalse(zipCodeUnderTest.isEmpty(zipCodeUnderTest));
    }


    @Test
    void getLatitude() {

    }

    @Test
    void getLongitude() {
    }
}
