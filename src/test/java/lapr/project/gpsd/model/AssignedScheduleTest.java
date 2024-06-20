package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class AssignedScheduleTest {


    private Date day= new Date(2019,04,25);

    private Time startTime = new Time(15,30);

    private Time endTime=new Time(16,00);

    private AssignedSchedule assignedScheduleUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        assignedScheduleUnderTest = new AssignedSchedule(day, startTime, endTime);
    }

    @Test
    public void testSetStartTime() {
        // Setup
        final Time startTime = new Time(15,45);

        // Run the test
        assignedScheduleUnderTest.setStartTime(startTime);

        // Verify the results
        assertEquals(startTime, assignedScheduleUnderTest.getStartTime());
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Day: " + assignedScheduleUnderTest.getDay().toString() + "\nStart time: " + assignedScheduleUnderTest.getStartTime();

        // Run the test
        final String result = assignedScheduleUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object otherObject = new AssignedSchedule(this.day, this.startTime, this.endTime) ;

        // Run the test
        final boolean result = assignedScheduleUnderTest.equals(otherObject);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testCompareTo() {
        // Setup
        final AssignedSchedule assignedScheduleEqual =new AssignedSchedule(this.day, this.startTime, this.endTime) ;
        final AssignedSchedule assignedScheduleGreater =new AssignedSchedule(new Date(2019,12,31) ,new Time(21), new Time(22)) ;
        final AssignedSchedule assignedScheduleLesser =new AssignedSchedule(new Date(2019, 01,01), new Time(12), new Time(13)) ;
        final int expectedResultEqual = 0;
        final int expectedResultGreater = 250;
        final int expectedResultLesser = 114;

        // Run the test
        final int resultEqual = assignedScheduleUnderTest.compareTo(assignedScheduleEqual);
        final int resultGreater = assignedScheduleUnderTest.compareTo(assignedScheduleGreater);
        final int resultLesser = assignedScheduleUnderTest.compareTo(assignedScheduleLesser);

        // Verify the results
        assertEquals(expectedResultEqual, resultEqual);
        assertEquals(expectedResultGreater, resultGreater);
        assertEquals(expectedResultLesser, resultLesser);
    }
}
