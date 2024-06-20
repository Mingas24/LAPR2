package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientTest {


    private PostalAddress address= new PostalAddress("add", new ZipCode("4000-008"), "town");

    private Client clientUnderTest;

    @BeforeEach
    public void setUp() {
        clientUnderTest = new Client("strName", "strTIN", "strTelephone", "strEmail", address);
    }

    @Test
    public void testHasEmail() {
        // Setup
        final String strEmail = "strEmail";

        // Run the test
        final boolean result = clientUnderTest.hasEmail(strEmail);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddPostalAddress() {
        // Setup
        final PostalAddress address = new PostalAddress("new add", new ZipCode("4470-057"), "new town");

        // Run the test
        final boolean result = clientUnderTest.addPostalAddress(address);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAssociateNewAddress() {
        // Setup
        final PostalAddress address = new PostalAddress("new add", new ZipCode("4470-057"), "new town");

        // Run the test
        final boolean result = clientUnderTest.associateNewAddress(address);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRemovePostalAddress() {
        // Setup
        final PostalAddress address = new PostalAddress("new add", new ZipCode("4470-057"), "new town");
        clientUnderTest.addPostalAddress(address);

        // Run the test
        final boolean result = clientUnderTest.removePostalAddress(address);

        // Verify the results
        assertTrue(result);
    }


    @Test
    public void testEquals() {
        // Setup
        final Object o = new Client("strName", "strTIN", "strTelephone", "strEmail", address);

        // Run the test
        final boolean result = clientUnderTest.equals(o);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testToString() {
        // Setup
        //"%s - %s - %s - %s", this.name, this.m_strTIN, this.m_strTelephone, this.m_strEmail
        final String expectedResult = clientUnderTest.getName() +" - "+clientUnderTest.getTIN()+" - "+clientUnderTest.getTelephone()+" - "+clientUnderTest.getEmail()+"\nAddress:\n" + clientUnderTest.getPostalAddress("add", new ZipCode("4000-008"), "town").toString();

        // Run the test
        final String result = clientUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

//    @Test
//    public void testToStringPostalAddresses() {
//        // Setup
//        final String expectedResult ="add/4000-008/town";
//
//        // Run the test
//        final String result = clientUnderTest.toStringPostalAddresses();
//
//        // Verify the results
//        assertEquals(expectedResult, result);
//    }

    @Test
    public void testGetPostalAddress() {
        // Setup
        //"add", new ZipCode("4000-008"), "town"
        final String addr = "add";
        final ZipCode zipCode = new ZipCode("4000-008");
        final String town = "town";
        final PostalAddress expectedResult =new PostalAddress(addr, zipCode, town);

        // Run the test
        final PostalAddress result = clientUnderTest.getPostalAddress(addr, zipCode, town);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewPostalAddress() {
        // Setup
        final String strAddress = "strAddress";
        final String strZipCode = "4000-009";
        final String strTown = "strTown";
        final PostalAddress expectedResult = new PostalAddress(strAddress,strZipCode,strTown);

        // Run the test
        final PostalAddress result = Client.newPostalAddress(strAddress, strZipCode, strTown);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
