package lapr.project.gpsd.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class EvaluationTest {

    private Evaluation evaluationUnderTest;

    @BeforeEach
    public void setUp() {
        evaluationUnderTest = new Evaluation(false);
    }

    @Test
    public void testReportEndService() {
        // Setup
        final String just = "just";
        final String sol = "sol";
        final Evaluation evaFalse=new Evaluation(false);

        // Run the test
        final boolean result = evaluationUnderTest.reportEndService(just, sol);
        final boolean result2=evaFalse.reportEndService(just,sol);

        // Verify the results
        assertTrue(result);
        assertTrue(result2);
    }

    @Test
    public void testValidateDesc() {
        // Setup
        final String just = "just";
        final String sol = "sol";

        // Run the test
        final boolean result = evaluationUnderTest.validateDesc(just, sol);

        // Verify the results
        assertTrue(result);
    }

    @Test
    public void testAddDesc() {
        // Setup
        final String just = "just";
        final String sol = "sol";

        // Run the test
        evaluationUnderTest.addDesc(just, sol);

        // Verify the results
    }
}
