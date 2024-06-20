package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AvailabilityTest {

    private Date initDate=new Date(2019,03,03);

    private Time initTime = new Time(15);

    private Date endDate=new Date(2019,03,03);

    private Time endTime=new Time(16);

    private Availability availabilityUnderTest;

    @BeforeEach
    public void setUp() {

        availabilityUnderTest = new Availability(initDate, initTime, endDate, endTime);
    }

    @Test
    public void testSetInitTime() {
        // Setup
        final Time initTime = new Time(14);

        // Run the test
        availabilityUnderTest.setInitTime(initTime);

        // Verify the results
        assertEquals(initTime, availabilityUnderTest.getInitTime());
    }

    @Test
    public void testSetEndTime() {
        // Setup
        final Time endTime = new Time(17);

        // Run the test
        availabilityUnderTest.setEndTime(endTime);

        // Verify the results
        assertEquals(endTime, availabilityUnderTest.getEndTime());
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Initial date: " + availabilityUnderTest.getInitDate() + "\nEnd date: " + availabilityUnderTest.getEndDate() + "\nInitial time: " + availabilityUnderTest.getInitTime() + "\nEnd time: " + availabilityUnderTest.getEndTime();

        // Run the test
        final String result = availabilityUnderTest.toString();
        System.out.println(result);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object otherObject = new Availability(initDate, initTime, endDate, endTime);

        // Run the test
        final boolean result = availabilityUnderTest.equals(otherObject);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCompareTo() {
        // Setup
        final Availability otherAvailabilityEqual = new Availability(initDate, initTime, endDate, endTime);
        final Availability otherAvailabilityGreater = new Availability(new Date(2019,12,12), new Time(21), new Date(2019,12,12),new Time(22));
        final Availability otherAvailabilityLesser = new Availability(new Date(2019,01,01), new Time(11), new Date(2019,01,01),new Time(12));
        final int expectedResultEqual = 0;
        final int expectedResultGreater=-1;
        final int expectedResultLesser=1;

        // Run the test
        final int resultEqual = availabilityUnderTest.compareTo(otherAvailabilityEqual);
        final int resultGreater = availabilityUnderTest.compareTo(otherAvailabilityGreater);
        final int resultLesser = availabilityUnderTest.compareTo(otherAvailabilityLesser);

        // Verify the results
        assertEquals(expectedResultEqual, resultEqual);
        assertEquals(expectedResultGreater, resultGreater);
        assertEquals(expectedResultLesser, resultLesser);
    }
}
