/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.gpsd.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * @author ADMIN
 */
public class ServiceProviderApplicationTest {
    private ServiceProviderApplication instance;
    public ServiceProviderApplicationTest() {
    }

    @BeforeEach
    public void setUp() {

        this.instance = new ServiceProviderApplication("name","tin","tele", "email", "address", "0000-000" , "town");
    }

    /**
     * Test of newPostalAddress method, of class ServiceProviderApplication.
     */
    @Test
    public void testNewPostalAddress() {
        System.out.println("newPostalAddress");
        String strAddress = "add";
        String strZipCode = "0000-000";
        String strTown = "town";
        PostalAddress expResult = new PostalAddress(strAddress,strZipCode,strTown);
        PostalAddress result = ServiceProviderApplication.newPostalAddress(strAddress, strZipCode, strTown);
        assertEquals(expResult, result);
    }

    /**
     * Test of newAcademicQualification method, of class ServiceProviderApplication.
     */
    @Test
    public void testNewAcademicQualification() {
        System.out.println("newAcademicQualification");
        String strDesign = "design";
        String strDegree = "degree";
        String strClassific = "class";
        AcademicQualification acad=new AcademicQualification(strDesign,strDegree,strClassific);
        //String strName, String strTIN, String strTelephone, String strEmail, String strAddress, String strZipCode, String strTown
        instance.newAcademicQualification(strDesign, strDegree, strClassific);
        assertTrue(instance.getAcadQual().contains(acad));
    }

    /**
     * Test of newProfessionalQualification method, of class ServiceProviderApplication.
     */
    @Test
    public void testNewProfessionalQualification() {
        System.out.println("newProfessionalQualification");
        String description = "bery qualified";
        ProfessionalQualification toBeContained=new ProfessionalQualification(description);
        instance.newProfessionalQualification(description);
        assertTrue(instance.getProfQual().contains(toBeContained));
    }

    /**
     * Test of registerCategory method, of class ServiceProviderApplication.
     */
    @Test
    public void testRegisterCategory() {
        System.out.println("registerCategory");
        Category cat = new Category("id", "desc");
        instance.registerCategory(cat);
        assertTrue(instance.getCatList().contains(cat));
    }

    @Test
    void toStringAcademic() {
        String strDesign = "design";
        String strDegree = "degree";
        String strClassific = "class";
        AcademicQualification acad=new AcademicQualification(strDesign,strDegree,strClassific);
        //String strName, String strTIN, String strTelephone, String strEmail, String strAddress, String strZipCode, String strTown
        instance.newAcademicQualification(strDesign, strDegree, strClassific);
        assertEquals("design;degree;class", instance.toStringAcademic());
    }

    @Test
    void toStringProfessional() {
        String description = "bery qualified";
        ProfessionalQualification toBeContained=new ProfessionalQualification(description);
        instance.newProfessionalQualification(description);
        assertEquals(description, instance.toStringProfessional());
    }

    @Test
    void toStringCategory() {
        Category cat = new Category("id", "desc");
        instance.registerCategory(cat);
        assertEquals("id", instance.toStringCategory());
    }

    @Test
    void equals1() {
        // Setup
        final Object o = instance;
        final Object oEqual=new ServiceProviderApplication(instance);
        final Object oNull=null;
        final Object oClass=new FirstComeFirstServe();

        // Run the test
        final boolean result = instance.equals(o);
        final boolean rEqual=instance.equals(oEqual);
        final boolean rNull=instance.equals(oNull);
        final boolean rClass=instance.equals(oClass);

        // Verify the results
        assertTrue(result);
        assertTrue(rEqual);
        assertFalse(rNull);
        assertFalse(rClass);
    }
}
