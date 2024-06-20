package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class GeographicalAreaRegistryTest {
    private Bootstrap bs=new Bootstrap();
    private ExternalServiceAdapter api=new ExternalServiceAdapter();
    private GeographicalAreaRegistry geographicalAreaRegistryUnderTest;

    @BeforeEach
    public void setUp() {

        geographicalAreaRegistryUnderTest = new GeographicalAreaRegistry(bs.getGeographicalAreaRegistry(api));
        geographicalAreaRegistryUnderTest.setApi(this.api);
    }

    @Test
    public void testGetGeographicalAreaByDesignation() {
        // Setup
        final String desig = "Gondomar-1";
        final GeographicalArea expectedResult = new GeographicalArea("Gondomar-1", 50.0, 10.f, new ZipCode("4420-002"), this.api);

        // Run the test
        final GeographicalArea result = geographicalAreaRegistryUnderTest.getGeographicalAreaByDesignation(desig);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetClosestGeographicalArea() {
        // Setup
        final ZipCode zipCode = new ZipCode("4420-002");
        final GeographicalArea expectedResult =new GeographicalArea("Gondomar-1", 50.0, 10.f, new ZipCode("4420-002"), this.api);

        // Run the test
        final GeographicalArea result = geographicalAreaRegistryUnderTest.getClosestGeographicalArea(zipCode);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewArea() {
        // Setup
        final String designation = "Maia-2";
        final ZipCode pAddress = new ZipCode("4470-057");
        final double tCost = 2;
        final float radius = 5000;
        final GeographicalArea expectedResult = new GeographicalArea(designation,tCost,radius,pAddress,this.api);

        // Run the test
        final GeographicalArea result = geographicalAreaRegistryUnderTest.newArea(designation, pAddress, tCost, radius);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRegisterGeographicalArea() {
        // Setup
        final String designation = "Maia-2";
        final ZipCode pAddress = new ZipCode("4470-057");
        final double tCost = 2;
        final float radius = 5000;
        final GeographicalArea expectedResult = new GeographicalArea(designation,tCost,radius,pAddress,this.api);

        // Run the test
        final boolean result = geographicalAreaRegistryUnderTest.registerGeographicalArea(expectedResult);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testValidateArea() {
        // Setup
        final String designation = "Maia-2";
        final ZipCode pAddress = new ZipCode("4470-057");
        final double tCost = 2;
        final float radius = 5000;
        final GeographicalArea expectedResult = new GeographicalArea(designation,tCost,radius,pAddress,this.api);

        // Run the test
        final boolean result = geographicalAreaRegistryUnderTest.validateArea(expectedResult);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddArea() {
        // Setup
        final String designation = "Maia-2";
        final ZipCode pAddress = new ZipCode("4470-057");
        final double tCost = 2;
        final float radius = 5000;
        final GeographicalArea expectedResult = new GeographicalArea(designation,tCost,radius,pAddress,this.api);

        // Run the test
        final boolean result = geographicalAreaRegistryUnderTest.addArea(expectedResult);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testContains() {
        // Setup
        final GeographicalArea ga =new GeographicalArea("Gondomar-1", 50.0, 10.f, new ZipCode("4420-002"), api);
        final GeographicalArea notContained=new GeographicalArea("Gondomar-20", 50.0, 10.f, new ZipCode("4420-002"), api);

        // Run the test
        final boolean result = geographicalAreaRegistryUnderTest.contains(ga);
        final boolean resultFalse=geographicalAreaRegistryUnderTest.contains(notContained);

        // Verify the results
        assertTrue(result);
        assertFalse(resultFalse);
    }

    @Test
    public void testEquals() {
        final Object o = geographicalAreaRegistryUnderTest;
        final Object equals=new GeographicalAreaRegistry(bs.getGeographicalAreaRegistry(this.api));
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = geographicalAreaRegistryUnderTest.equals(o);
        final boolean resultEq=geographicalAreaRegistryUnderTest.equals(equals);
        final boolean resultNul=geographicalAreaRegistryUnderTest.equals(nuller);
        final boolean resultCla=geographicalAreaRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }
}
