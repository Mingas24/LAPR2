/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Jee ^^
 */
public class ProfessionalQualificationTest {
    
    public ProfessionalQualificationTest() {
    }

    /**
     * Test of getDescription method, of class ProfessionalQualification.
     */
    @Test
    public void testGetDescription() {
        ProfessionalQualification instance = new ProfessionalQualification("description test");
        String expResult = "description test";
        String result = instance.getDescription();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDescription method, of class ProfessionalQualification.
     */
    @Test
    public void testSetDescription() {
        String description = "new desciption test";
        ProfessionalQualification instance = new ProfessionalQualification("description test");
        instance.setDescription(description);
        assertEquals(description, instance.getDescription());
    }

    /**
     * Test of toString method, of class ProfessionalQualification.
     */
    @Test
    public void testToString() {
        ProfessionalQualification instance = new ProfessionalQualification("description test");
        String expResult = "Professional Qualification: description test";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class ProfessionalQualification.
     */
    @Test
    public void testEqualsFalse() {
        Object o = null;
        ProfessionalQualification instance = new ProfessionalQualification("description test");
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of equals method, of class ProfessionalQualification.
     */
    @Test
    public void testEqualsTrue() {
        Object o = new ProfessionalQualification("description test");
        ProfessionalQualification instance = new ProfessionalQualification("description test");
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
}
