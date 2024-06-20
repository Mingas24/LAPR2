package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.ServiceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTypeRegistryTest {
    private Bootstrap bs=new Bootstrap();
    private ServiceTypeRegistry serviceTypeRegistryUnderTest;

    @BeforeEach
    public void setUp() {
        serviceTypeRegistryUnderTest = new ServiceTypeRegistry(bs.getServiceTypeRegistry());
    }

    @Test
    public void testRegisterServiceType() {
        // Setup
        final ServiceType st = new ServiceType("Limited", "SemiLimitedService");

        // Run the test
        final boolean result = serviceTypeRegistryUnderTest.registerServiceType(st);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetServiceTypeById() {
        // Setup
        final String id = "LimitedService";
        final ServiceType expectedResult = serviceTypeRegistryUnderTest.getServiceTypeList().get(0);

        // Run the test
        final ServiceType result = serviceTypeRegistryUnderTest.getServiceTypeById(id);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
