package lapr.project.gpsd.model;

import lapr.project.gpsd.controller.ApplicationGPSD;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompanyTest {

    private Company companyUnderTest;

    @BeforeEach
    public void setUp() {
        companyUnderTest = ApplicationGPSD.getInstance().getCompany();
    }

    @Test
    public void testNotifiesServiceProvider() {
        // Setup
        final List<ServiceProvider> servProvList = Arrays.asList();

        // Run the test
        companyUnderTest.notifiesServiceProvider(servProvList);

        // Verify the results
    }

    @Test
    public void testNewApplication() {
        // Setup
        final String strName = "strName";
        final String strTIN = "strTIN";
        final String strTelephone = "strTelephone";
        final String strEmail = "strEmail";
        final PostalAddress addr1 = new PostalAddress("add", new ZipCode("4000-008"), "town");
        final ServiceProviderApplication expectedResult = new ServiceProviderApplication(strName,strTIN,strTelephone,strEmail,addr1);

        // Run the test
        final ServiceProviderApplication result = companyUnderTest.newApplication(strName, strTIN, strTelephone, strEmail, addr1);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetApi() {
        // Setup
        final ExternalService expectedResult = new ExternalServiceAdapter();
        // Run the test
        final ExternalService result = companyUnderTest.getApi();
        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = companyUnderTest;
        final Object equal=ApplicationGPSD.getInstance().getCompany();
        final Object nuller=null;
        final Object difClass= new FirstComeFirstServe();

        // Run the test
        final boolean result = companyUnderTest.equals(o);
        final boolean rEq=companyUnderTest.equals(equal);
        final boolean resultNull = companyUnderTest.equals(nuller);
        final boolean resultDif=companyUnderTest.equals(difClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEq);
        assertFalse(resultNull);
        assertFalse(resultDif);
    }

    @Test
    public void testValidateApplication() {
        // Setup
        //String strName, String strTIN, String strTelephone, String strEmail, String strAddress, String strZipCode, String strTown
        final ServiceProviderApplication appl = new ServiceProviderApplication("name", "tin", "tele", "ema", "add", "4000-009", "town");

        // Run the test
        final boolean result = companyUnderTest.validateApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRegisterApplication() {
        // Setup
        final ServiceProviderApplication appl = new ServiceProviderApplication("name", "tin", "tele", "ema", "add", "4000-009", "town");

        // Run the test
        final boolean result = companyUnderTest.registerApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddApplication() {
        // Setup
        final ServiceProviderApplication appl = new ServiceProviderApplication("name", "tin", "tele", "ema", "add", "4000-009", "town");

        // Run the test
        final boolean result = companyUnderTest.addApplication(appl);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetAlgorithmList() {
        // Setup
        final List<SchedulingAlgorithm> expectedResult = new ArrayList<>();
        expectedResult.add(new FirstComeFirstServe());
        expectedResult.add(new RandomScheduling());

        // Run the test
        final List<SchedulingAlgorithm> result = companyUnderTest.getAlgorithmList();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
