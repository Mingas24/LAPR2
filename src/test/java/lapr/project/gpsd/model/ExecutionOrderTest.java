package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExecutionOrderTest {


    private Date date=new Date(2019,12,31) ;
    private PostalAddress address= new PostalAddress("add", new ZipCode("4000-008"), "town");
    private Client cli=new Client("strName", "strTIN", "strTelephone", "strEmail", address);

    private ServiceRequest sr = new ServiceRequest(1,date, cli,address, new ArrayList<>());

    private ServiceProvider sp= new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private AssignedSchedule sch = new AssignedSchedule(new Date(2019,12,31), new Time(15), new Time(16));

    private AssignedService as=new AssignedService(sp, sd, sch);

    private ExecutionOrder executionOrderUnderTest;

    @BeforeEach
    public void setUp() {
        executionOrderUnderTest = new ExecutionOrder(0, date, sr, as, sp);
    }

    @Test
    public void testHasServiceProvider() {
        // Setup
        final ServiceProvider sp = this.sp;

        // Run the test
        final boolean result = executionOrderUnderTest.hasServiceProvider(sp);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testHasNum() {
        // Setup
        final int num = 0;
        final int numFalse=99;

        // Run the test
        final boolean result = executionOrderUnderTest.hasNum(num);
        final boolean resultFalse=executionOrderUnderTest.hasNum(numFalse);

        // Verify the results
        assertTrue(result);
        assertFalse(resultFalse);
    }

    @Test
    public void testChooseEvaluation() {
        // Setup
        final boolean answer = false;
        final Evaluation expectedResult = new Evaluation(false);

        // Run the test
        final Evaluation result = executionOrderUnderTest.chooseEvaluation(answer);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetToFinished() {
        // Setup

        // Run the test
        executionOrderUnderTest.setToFinished();

        // Verify the results
        assertTrue(executionOrderUnderTest.getStatus());
    }
}
