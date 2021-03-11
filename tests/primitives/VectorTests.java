package primitives;

import org.junit.jupiter.api.Test;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

/**
 * Unit tests for primitives.Vector class
 *
 * @author Dan
 */
public class VectorTests {

    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
    public VectorTests() {
        try { // test zero vector
            new Vector(0, 0, 0);
            fail("ERROR: zero vector does not throw an exception");
        } catch (Exception e) {
        }

    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void testCrossProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);

        // ============ Equivalence Partitions Tests ==============
        Vector v3 = new Vector(0, 3, -2);
        Vector vr = v1.crossProduct(v3);

        // Test that length of cross-product is proper (orthogonal vectors taken for simplicity)
        assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");

        // Test cross-product result orthogonality to its operands
        assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
        assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");

        // =============== Boundary Values Tests ==================
        // test zero vector from cross-productof co-lined vectors
        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {
        }
    }


    @Test
    void add() {
    }

    @Test
    void subtract() {
    }

    @Test
    void scale() {
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    void dotProduct() {
        // test Dot-Product
        assertTrue(isZero(v1.dotProduct(v3)), "ERROR: dotProduct() for orthogonal vectors is not zero");
        assertTrue(isZero(v1.dotProduct(v2) + 28), "ERROR: dotProduct() wrong value");
    }


    @Test
    void lengthSquared() {
    }

    /**
     * Test method for {@link Vector#length()} (primitives.Vector)}.
     */
    @Test
    void length() {

        // test length..
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");

    }

    @Test
    void normalize() {
    }

    @Test
    void normalized() {
    }
}