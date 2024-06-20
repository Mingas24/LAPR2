package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import static org.mockito.MockitoAnnotations.initMocks;

public class ServiceProviderLabelTest {


    private ServiceProvider serviceProviderUnderTest;
    private PostalAddress address=new PostalAddress("a",new ZipCode("4470-057"),"r");

    private ServiceProviderLabel serviceProviderLabelUnderTest;

    @BeforeEach
    public void setUp() {
        serviceProviderUnderTest = new ServiceProvider("name", "abrevName", "tin", "mechNumber", "telephone", "email", address, new ArrayList<>(), new ArrayList<>());
        serviceProviderLabelUnderTest = new ServiceProviderLabel(2, 4, serviceProviderUnderTest);
    }

}
