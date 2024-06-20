package lapr.project.gpsd.controller;

import lapr.project.gpsd.model.ExternalServiceAdapter;
import lapr.project.gpsd.model.GeographicalArea;
import lapr.project.gpsd.model.ZipCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpecifyGeographicalAreaControllerTest {

    private SpecifyGeographicalAreaController specifyGeographicalAreaControllerUnderTest;
    ExternalServiceAdapter api=new ExternalServiceAdapter();

    @BeforeEach
    public void setUp() {

        specifyGeographicalAreaControllerUnderTest = new SpecifyGeographicalAreaController();
        specifyGeographicalAreaControllerUnderTest.newArea("areaTest",new ZipCode("4470-057"),5,1);
    }

    @Test
    public void testGetCoverageList() {
        // Setup
        final List<ZipCode> expectedResult = new ArrayList<>();
        expectedResult.add(new ZipCode("4470-057"));

        // Run the test
        final List<ZipCode> result = specifyGeographicalAreaControllerUnderTest.getCoverageList();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewArea() {
        // Setup
        final String designation = "designation";
        final ZipCode zipCode = new ZipCode("4470-057");
        final double tCost = 5f;
        final float radius = 1f;
        final GeographicalArea expectedResult = new GeographicalArea(designation, tCost,radius, zipCode, api );

        // Run the test
        final GeographicalArea result = specifyGeographicalAreaControllerUnderTest.newArea(designation, zipCode, tCost, radius);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRegisterArea() {
        // Setup

        // Run the test
        final boolean result = specifyGeographicalAreaControllerUnderTest.registerArea();

        // Verify the results
        assertTrue(result);


    }
}
