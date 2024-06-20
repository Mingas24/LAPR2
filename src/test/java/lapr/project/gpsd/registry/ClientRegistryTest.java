package lapr.project.gpsd.registry;

import lapr.project.authorization.AuthorizationFacade;
import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.Client;
import lapr.project.gpsd.model.FirstComeFirstServe;
import lapr.project.gpsd.model.PostalAddress;
import lapr.project.gpsd.model.ZipCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ClientRegistryTest {
    private Bootstrap bs =new Bootstrap();
    private ClientRegistry clientRegistryUnderTest;

    @BeforeEach
    public void setUp() {
        clientRegistryUnderTest = new ClientRegistry(bs.getClientRegistry());
    }

    @Test
    public void testNewClient() {
        // Setup
        final String strName = "strName";
        final String strTIN = "strTIN";
        final String strTelephone = "strTelephone";
        final String strEmail = "strEmail";
        final String password = "password";
        final PostalAddress address = new PostalAddress("addr", new ZipCode("4470-057"), "town");
        final Client expectedResult = new Client(strName,strTIN,strTelephone,strEmail,address);
        expectedResult.setPassword(password);

        // Run the test
        final Client result = clientRegistryUnderTest.newClient(strName, strTIN, strTelephone, strEmail, password, address);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateClient() {
        // Setup
        final String strName = "strName";
        final String strTIN = "strTIN";
        final String strTelephone = "strTelephone";
        final String strEmail = "strEmail";
        final String password = "password";
        final PostalAddress address = new PostalAddress("addr", new ZipCode("4470-057"), "town");
        final Client client = new Client(strName,strTIN,strTelephone,strEmail,address);
        client.setPassword(password);

        // Run the test
        final boolean result = clientRegistryUnderTest.validateClient(client, password);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRegisterClient() {
        // Setup
        final String strName = "strName";
        final String strTIN = "strTIN";
        final String strTelephone = "strTelephone";
        final String strEmail = "strEmail";
        final String password = "password";
        final PostalAddress address = new PostalAddress("addr", new ZipCode("4470-057"), "town");
        final Client client = new Client(strName,strTIN,strTelephone,strEmail,address);
        client.setPassword(password);
        final AuthorizationFacade af = new AuthorizationFacade();

        // Run the test
        final boolean result = clientRegistryUnderTest.registerClient(client, password, af);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddClient() {
        // Setup
        final String strName = "strName";
        final String strTIN = "strTIN";
        final String strTelephone = "strTelephone";
        final String strEmail = "strEmail";
        final String password = "password";
        final PostalAddress address = new PostalAddress("addr", new ZipCode("4470-057"), "town");
        final Client cli = new Client(strName,strTIN,strTelephone,strEmail,address);
        cli.setPassword(password);

        // Run the test
        final boolean result = clientRegistryUnderTest.addClient(cli);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetClientByEmail() {
        // Setup
        final String email = "jSantos@isep.ipp.pt";
        final Client expectedResult = new Client("Joana Santos", "210975020", "966545644", "jSantos@isep.ipp.pt", new PostalAddress("R. Cegonheira, nº 3", "4470-528", "Maia"));

        // Run the test
        final Client result = clientRegistryUnderTest.getClientByEmail(email);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetClientByTIN() {
        // Setup
        final String tin = "210975020";
        final Client expectedResult = new Client("Joana Santos", "210975020", "966545644", "jSantos@isep.ipp.pt", new PostalAddress("R. Cegonheira, nº 3", "4470-528", "Maia"));

        // Run the test
        final Client result = clientRegistryUnderTest.getClientByTIN(tin);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = clientRegistryUnderTest;
        final Object equals=new ClientRegistry(bs.getClientRegistry());
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = clientRegistryUnderTest.equals(o);
        final boolean resultEq=clientRegistryUnderTest.equals(equals);
        final boolean resultNul=clientRegistryUnderTest.equals(nuller);
        final boolean resultCla=clientRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }
}
