package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PreferedScheduleTest {


    private Date date=new Date(2019,04,25);

    private Time time=new Time(15);

    private PreferedSchedule preferedScheduleUnderTest;

    @BeforeEach
    public void setUp() {
        preferedScheduleUnderTest = new PreferedSchedule(0, date, time);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Date = Thursday, 25 de April de 2019\nTime = 03:00:00 PM";

        // Run the test
        final String result = preferedScheduleUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = preferedScheduleUnderTest;
        final Object oEqual=new PreferedSchedule(0, date, time);
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = preferedScheduleUnderTest.equals(o);
        final boolean rEqual=preferedScheduleUnderTest.equals(oEqual);
        final boolean rNull=preferedScheduleUnderTest.equals(oNull);
        final boolean rClass=preferedScheduleUnderTest.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEqual);
        assertFalse(rNull);
        assertFalse(rClass);
    }
}
