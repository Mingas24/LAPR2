package lapr.project.gpsd.model;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

class ExternalServiceByCsvTest {
    ExternalServiceByCsv externalServiceByCsv = new ExternalServiceByCsv();

    @Test
    void testObtainCoverage() {
        // Setup
        final ZipCode center = new ZipCode("4470-057",41.20833f, -8.60787f);
        final double radius = 1;
        final List<ZipCode> expectedResult = new ArrayList<>();
        expectedResult.add(center);

        // Run the test
        final List<ZipCode> result = externalServiceByCsv.obtainCoverage(center, radius);

        // Verify the results
        assertEquals(expectedResult, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme