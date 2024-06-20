package lapr.project.gpsd.model;

import lapr.project.gpsd.utils.Date;
import lapr.project.gpsd.utils.Time;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

public class AssignedServiceTest {


    private ServiceProvider sp = new ServiceProvider("name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());
    private ServiceDescription sd=new ServiceDescription("desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );

    private AssignedSchedule sch = new AssignedSchedule(new Date(2019,12,31), new Time(15), new Time(16));

    private AssignedService assignedServiceUnderTest;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        assignedServiceUnderTest = new AssignedService(sp, sd, sch);
        assignedServiceUnderTest.setStatus("Pending");
    }

    @Test
    public void testToString() {
        // Setup
        final String expectedResult = "Assigned Service: \nService Description: " + this.sd + "\nService Provider: " + this.sp + "\nAssigned Schedule: "+this.sch+"\nStatus: "+assignedServiceUnderTest.getStatus() + " ";

        // Run the test
        final String result = assignedServiceUnderTest.toString();

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void getSchedule() {
        //Setup
        final AssignedSchedule expectedResult = sch;

        //Run the test
        final AssignedSchedule result = assignedServiceUnderTest.getSchedule();

        //Verify the results
        assertEquals(expectedResult,result);
    }

    @Test
    void setSchedule() {
        //Setup
        final AssignedSchedule expectedResult = new AssignedSchedule(new Date(2019,12,30), new Time(15), new Time(16));

        //Run the test
        assignedServiceUnderTest.setSchedule(expectedResult);
        final AssignedSchedule result = assignedServiceUnderTest.getSchedule();

        //Verify the result
        assertEquals(expectedResult,result);
    }

    @Test
    void getServiceProvider() {
        //Setup
        final ServiceProvider expectedResult=sp;

        //Run the test
        final ServiceProvider result=assignedServiceUnderTest.getServiceProvider();

        //Verify the results
        assertEquals(expectedResult,result);
    }

    @Test
    void setServiceProvider() {
        //Setup
        final ServiceProvider expectedResult=new ServiceProvider("new name", "n", "123456789", "1238498", "91000000", "a@a.a", new PostalAddress("address", new ZipCode("4000-008"), "town"), new ArrayList<>());

        //Run the test
        assignedServiceUnderTest.setServiceProvider(expectedResult);
        final ServiceProvider result=assignedServiceUnderTest.getServiceProvider();

        //Verify the results
        assertEquals(expectedResult,result);
    }

    @Test
    void getServiceDescription() {
        final ServiceDescription expectedResult=sd;
        final ServiceDescription result=assignedServiceUnderTest.getServiceDescription();
        assertEquals(expectedResult,result);
    }

    @Test
    void setServiceDescription() {
        final ServiceDescription expectedResult=new ServiceDescription("new desc", new Time(1), new FixedService("id", "desc", "full", 21, new Category("id", "desc"), new ServiceType("Fixed", "id")) );
        assignedServiceUnderTest.setServiceDescription(expectedResult);
        final ServiceDescription result=assignedServiceUnderTest.getServiceDescription();
        assertEquals(expectedResult,result);
    }
}
