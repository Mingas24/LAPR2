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
public class AcademicQualificationTest {


    /**
     * Test of getDesignation method, of class AcademicQualification.
     */
    @Test
    public void testGetDesignation() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String expResult = "designation test";
        String result = instance.getDesignation();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDegree method, of class AcademicQualification.
     */
    @Test
    public void testGetDegree() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String expResult = "degree test";
        String result = instance.getDegree();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassification method, of class AcademicQualification.
     */
    @Test
    public void testGetClassification() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String expResult = "classification test";
        String result = instance.getClassification();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDesignation method, of class AcademicQualification.
     */
    @Test
    public void testSetDesignation() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String strDesignation = "new designation test";
        instance.setDesignation(strDesignation);
        assertEquals(strDesignation, instance.getDesignation());
    }

    /**
     * Test of setDegree method, of class AcademicQualification.
     */
    @Test
    public void testSetDegree() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String strDegree = "new degree test";
        instance.setDegree(strDegree);
        assertEquals(strDegree, instance.getDegree());
    }

    /**
     * Test of setClassification method, of class AcademicQualification.
     */
    @Test
    public void testSetClassification() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String strClassification = "new classification test";
        instance.setClassification(strClassification);
        assertEquals(strClassification, instance.getClassification());
    }

    /**
     * Test of toString method, of class AcademicQualification.
     */
    @Test
    public void testToString() {
        AcademicQualification instance = new AcademicQualification("designation test", "degree test", "classification test");
        String expResult = "Academic Qualifications: Designation: designation test, Degree: degree test, Classification: classification test";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    @Test
    void equals1() {
    }
}
