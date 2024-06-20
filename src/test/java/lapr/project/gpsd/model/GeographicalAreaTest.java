package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class GeographicalAreaTest {

    ZipCode zipCodeCenter=new ZipCode("4470-057");

    ExternalService api=new ExternalServiceAdapter();

    GeographicalArea geographicalAreaUnderTest=new GeographicalArea("desig", 0.0, 0.0f, zipCodeCenter, api);

    @Test
    public void testHasGeographicalArea() {
        // Setup
        final String area = "desig";

        // Run the test
        final boolean result = geographicalAreaUnderTest.hasGeographicalArea(area);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testEquals() {
        // Setup
        final GeographicalArea geoTest = new GeographicalArea("desig", 0.0, 0.0f, zipCodeCenter, api);
        final Object o=geographicalAreaUnderTest;//reference
        final Object nuller=null;
        final Object difClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = geographicalAreaUnderTest.equals(geoTest);
        final boolean resultRef=geographicalAreaUnderTest.equals(o);
        final boolean resultNul=geographicalAreaUnderTest.equals(nuller);
        final boolean resultDif=geographicalAreaUnderTest.equals(difClass);

        // Verify the results
        assertTrue(result);
        assertTrue(resultRef);
        assertFalse(resultNul);
        assertFalse(resultDif);
    }

    @Test
    void getAreaID() {

    }

    @Test
    void hasID() {
    }

    @Test
    void getDesig() {
        assertEquals("desig", geographicalAreaUnderTest.getDesig());
    }

    @Test
    void hasGeographicalArea() {
        assertTrue(geographicalAreaUnderTest.hasGeographicalArea("desig"));
    }

    @Test
    void getTransportationCost() {
        assertEquals(0, geographicalAreaUnderTest.getTransportationCost());
    }

    @Test
    void getRadius() {
        assertEquals(0, geographicalAreaUnderTest.getRadius());
    }

    @Test
    void getCoverageList() {
        List<ZipCode> lista = new ArrayList<>();
        lista.add(new ZipCode("4470-057"));
        assertEquals(lista, geographicalAreaUnderTest.getCoverageList());
    }

    @Test
    void getCenterZipCode() {
        assertEquals(new ZipCode("4470-057"), geographicalAreaUnderTest.getCenterZipCode());
    }

    @Test
    void toString1() {
        assertEquals(" 0 - desig (4470-057)", geographicalAreaUnderTest.toString());
    }
}
