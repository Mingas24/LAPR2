/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jee ^^
 */
public class ExternalServiceTest {
    
    public ExternalServiceTest() {
    }
    


    /**
     * Test of obtainCoverage method, of class ExternalService.
     */
    @Test
    public void testObtainCoverage() {
        System.out.println("obtainCoverage");
        ZipCode center = new ZipCode("4470-057");
        double radius = 1;
        ExternalService instance = new ExternalServiceImpl();
        List<ZipCode> expResult = new ArrayList<>();
        expResult.add(center);
        List<ZipCode> result = instance.obtainCoverage(center, radius);
        assertEquals(expResult, result);
    }

    public class ExternalServiceImpl implements ExternalService {
        ExternalServiceByCsv api=new ExternalServiceByCsv();
        public List<ZipCode> obtainCoverage(ZipCode center, double radius) {
            return  new ArrayList<>(api.obtainCoverage(center,radius));
        }
    }
    
}
