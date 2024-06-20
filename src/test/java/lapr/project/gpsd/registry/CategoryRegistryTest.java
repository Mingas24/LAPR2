package lapr.project.gpsd.registry;

import lapr.project.gpsd.controller.Bootstrap;
import lapr.project.gpsd.model.Category;
import lapr.project.gpsd.model.FirstComeFirstServe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CategoryRegistryTest {
    private Bootstrap bs = new Bootstrap();

    private CategoryRegistry categoryRegistryUnderTest;

    @BeforeEach
    public void setUp() {
        categoryRegistryUnderTest = new CategoryRegistry(bs.getCategoryRegistry());
    }

    @Test
    public void testGetCategoryByID() {
        // Setup
        final String catID = "01";
        final Category expectedResult = new Category("01", "Plumber");

        // Run the test
        final Category result = categoryRegistryUnderTest.getCategoryByID(catID);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testGetCategoryByDesignation() {
        // Setup
        final String desig = "Plumber";
        final Category expectedResult = new Category("01", "Plumber");

        // Run the test
        final Category result = categoryRegistryUnderTest.getCategoryByDesignation(desig);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testEquals() {
        // Setup
        final Object o = categoryRegistryUnderTest;
        final Object equals=new CategoryRegistry(bs.getCategoryRegistry());
        final Object nuller=null;
        final Object classer=new FirstComeFirstServe();

        // Run the test
        final boolean result = categoryRegistryUnderTest.equals(o);
        final boolean resultEq=categoryRegistryUnderTest.equals(equals);
        final boolean resultNul=categoryRegistryUnderTest.equals(nuller);
        final boolean resultCla=categoryRegistryUnderTest.equals(classer);

        // Verify the results
        assertTrue(result);
        assertTrue(resultEq);
        assertFalse(resultNul);
        assertFalse(resultCla);
    }

    @Test
    public void testNewCategory() {
        // Setup
        final String catCode = "10";
        final String desc = "Lapr2 final project test making";
        final Category expectedResult = new Category("10", "Lapr2 final project test making");

        // Run the test
        final Category result = categoryRegistryUnderTest.newCategory(catCode, desc);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testValidateCategory() {
        // Setup
        final String catCode = "10";
        final String desc = "Lapr2 final project test making";
        final Category cat = new Category("10", "Lapr2 final project test making");
        // Run the test
        final boolean result = categoryRegistryUnderTest.validateCategory(cat);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testRegisterCategory() {
        final String catCode = "10";
        final String desc = "Lapr2 final project test making";
        final Category cat = new Category("10", "Lapr2 final project test making");

        // Run the test
        final boolean result = categoryRegistryUnderTest.registerCategory(cat);

        // Verify the results
        assertTrue(result);
    }
}
