package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PostalAddressTest {

    PostalAddress postalAddressUnderTest=new PostalAddress("strAddress", "4000-008", "strTown");;

    @Test
    public void testEquals() {
        // Setup
        final PostalAddress postalTest = new PostalAddress("strAddress", "4000-008", "strTown");
        final Object oRef=postalAddressUnderTest;
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = postalAddressUnderTest.equals(postalTest);
        final boolean rRef=postalTest.equals(oRef);
        final boolean rNull=postalTest.equals(oNull);
        final boolean rClass=postalTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rRef);
        assertFalse(rNull);
        assertFalse(rClass);
    }
}
