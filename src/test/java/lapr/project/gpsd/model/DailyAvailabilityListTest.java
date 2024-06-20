package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DailyAvailabilityListTest {

    private DailyAvailabilityList dailyAvailabilityListUnderTest;

    private Date initDate=new Date(2019,01,01);

    private Time initTime = new Time(15);

    private Date endDate=new Date(2019,12,31);

    private Time endTime=new Time(16);

    private Availability availabilityUnderTest=new Availability(initDate, initTime, endDate, endTime);

    @BeforeEach
    public void setUp() {
        List<Availability> aux = new ArrayList<>();
        aux.add(availabilityUnderTest);
        dailyAvailabilityListUnderTest = new DailyAvailabilityList(aux);
    }

    @Test
    public void testGetAvailability() {
        // Setup
        final int index = 0;
        final Availability expectedResult = availabilityUnderTest;

        // Run the test
        final Availability result = dailyAvailabilityListUnderTest.getAvailability(index);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetAvailability() {
        // Setup
        final int index = 0;
        final Availability availability = new Availability(new Date(2019, 01,01), new Time(16), new Date(2019, 01, 31), new Time(20));
        final Availability expectedResult = new Availability(initDate, initTime, endDate, endTime);

        // Run the test
        final Availability result = dailyAvailabilityListUnderTest.setAvailability(index, availability);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testNewAvailability() {
        // Setup
        final Date initDate = new Date(2019, 01,01);
        final Time initTime = new Time(16);
        final Date endDate = new Date(2019, 01, 31);
        final Time endTime = new Time(20);
        final Availability expectedResult = new Availability(initDate, initTime, endDate, endTime);

        // Run the test
        final Availability result = dailyAvailabilityListUnderTest.newAvailability(initDate, initTime, endDate, endTime);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testRegisterAvailability() {
        // Setup
        final Date initDate = new Date(2019, 01,01);
        final Time initTime = new Time(16);
        final Date endDate = new Date(2019, 01, 31);
        final Time endTime = new Time(20);
        final Availability availability = new Availability(initDate, initTime, endDate, endTime);

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.registerAvailability(availability);
        final boolean resultFalse=dailyAvailabilityListUnderTest.registerAvailability(availabilityUnderTest);

        // Verify the results
        assertTrue(result);
        assertFalse(resultFalse);
    }

    @Test
    public void testValidatesAvailability() {
        // Setup
        final Availability availability = null;

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.validatesAvailability(availability);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddAvailability() {
        // Setup
        final Date initDate = new Date(2019, 01,01);
        final Time initTime = new Time(16);
        final Date endDate = new Date(2019, 01, 31);
        final Time endTime = new Time(20);
        final Availability availability = new Availability(initDate, initTime, endDate, endTime);

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.addAvailability(availability);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRemoveAvailability() {
        // Setup

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.removeAvailability(availabilityUnderTest);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testGetIndex() {
        // Setup
        final int expectedResult = 0;

        // Run the test
        final int result = dailyAvailabilityListUnderTest.getIndex(availabilityUnderTest);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testClear() {
        // Setup

        // Run the test
        dailyAvailabilityListUnderTest.clear();

        // Verify the results
        assertTrue(dailyAvailabilityListUnderTest.getListDispD().size()==0);
    }

    @Test
    public void testIsEmpty() {
        // Setup

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.isEmpty();

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Initial date: Tuesday, 1 de January de 2019\n" +
                "End date: Tuesday, 31 de December de 2019\n" +
                "Initial time: 03:00:00 PM\n" +
                "End time: 04:00:00 PM";

        // Run the test
        final String result = dailyAvailabilityListUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        List<Availability> aux = new ArrayList<>();
        aux.add(availabilityUnderTest);
        final Object outroObjeto = new DailyAvailabilityList(aux);
        final Object nullTest=null;
        final Object classTest=new FirstComeFirstServe();

        // Run the test
        final boolean result = dailyAvailabilityListUnderTest.equals(outroObjeto);
        final boolean resultNull=dailyAvailabilityListUnderTest.equals(nullTest);
        final boolean resultClass=dailyAvailabilityListUnderTest.equals(classTest);
        final boolean resultSame=dailyAvailabilityListUnderTest.equals(dailyAvailabilityListUnderTest);

        // Verify the results
        assertTrue(result);
        assertFalse(resultClass);
        assertFalse(resultNull);
        assertTrue(resultSame);
    }

    @Test
    public void testGetAvailabilityInDate() {
        // Setup
        final Date date = new Date(2019,03,05);
        final Time[] expectedResult = new Time[2];
        expectedResult[0]=new Time(15);
        expectedResult[1]=new Time(16);

        // Run the test
        final Time[] result = dailyAvailabilityListUnderTest.getAvailabilityInDate(date);

        // Verify the results
        assertArrayEquals(expectedResult, result);
    }
}
