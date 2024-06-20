package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingTest {

    private Rating ratingUnderTest;
    private ServiceProvider sp=new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

    private Date date=new Date(2019,12,31) ;
    private PostalAddress address= new PostalAddress("add", new ZipCode("4000-008"), "town");
    private Client cli=new Client("strName", "strTIN", "strTelephone", "strEmail", address);

    private ServiceRequest sr = new ServiceRequest(1,date, cli,address, new ArrayList<>());

    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private AssignedSchedule sch = new AssignedSchedule(new Date(2019,12,31), new Time(15), new Time(16));

    private AssignedService as=new AssignedService(sp, sd, sch);

    private ExecutionOrder executionOrderUnderTest;
    @BeforeEach
    public void setUp() {

        ratingUnderTest = new Rating(3);
        executionOrderUnderTest=new ExecutionOrder(0, date, sr, as, sp);
        ratingUnderTest.setExecutionOrder(executionOrderUnderTest);
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Rating: 3\nExecution Order:" + executionOrderUnderTest.toString();

        // Run the test
        final String result = ratingUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void getServProv() {
        assertEquals(executionOrderUnderTest, ratingUnderTest.getServProv());
    }

    @Test
    void getRate() {
        assertEquals(3,ratingUnderTest.getRate());
    }

    @Test
    void setServProv() {
        ExecutionOrder newOrder=new ExecutionOrder(1, date, sr, as, sp);
        ratingUnderTest.setExecutionOrder(newOrder);
        assertEquals(newOrder, ratingUnderTest.getServProv());
    }

    @Test
    void setRate() {
        ratingUnderTest.setRate(5);
        assertEquals(5, ratingUnderTest.getRate());
    }
}
