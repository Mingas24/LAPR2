package lapr.project.gpsd.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExternalServiceAdapterTest {

    ExternalServiceAdapter api=new ExternalServiceAdapter();



    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testObtainCoverage() {
        // Setup
        final ZipCode center = new ZipCode("4470-057",41.20833f, -8.60787f);
        final double radius = 1;
        final List<ZipCode> expectedResult = new ArrayList<>();
        expectedResult.add(center);

        // Run the test
        final List<ZipCode> result = api.obtainCoverage(center, radius);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    //test equals
}
