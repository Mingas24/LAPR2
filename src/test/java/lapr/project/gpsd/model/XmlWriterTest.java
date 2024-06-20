package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class XmlWriterTest {
    private Date date=new Date(2019,12,31) ;
    private PostalAddress address= new PostalAddress("add", new ZipCode("4000-008"), "town");
    private Client cli=new Client("strName", "strTIN", "strTelephone", "strEmail", address);

    private ServiceRequest sr = new ServiceRequest(1,date, cli,address, new ArrayList<>());

    private ServiceProvider sp= new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private AssignedSchedule sch = new AssignedSchedule(new Date(2019,12,31), new Time(15), new Time(16));

    private AssignedService as=new AssignedService(sp, sd, sch);

    private ExecutionOrder executionOrderUnderTest;

    private XmlWriter xmlWriterUnderTest;

    @BeforeEach
    public void setUp() {
        executionOrderUnderTest = new ExecutionOrder(0, date, sr, as, sp);
        xmlWriterUnderTest = new XmlWriter();
    }

    @Test
    public void testWriteExecutionOrders() {
        // Setup
        final List<ExecutionOrder> leo = new ArrayList<>();
        leo.add(executionOrderUnderTest);

        // Run the test
        final boolean result = xmlWriterUnderTest.writeExecutionOrders(leo);

        // Verify the results
        assertTrue(result);
    }
}
