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
        assertEquals(new Vector(-1.0, -2.0, -3.0), v1.add(v2), "add vector don't work");

    }

    @Test
    void subtract() {
        assertEquals(new Vector(3.0, 6.0, 9.0), v1.subtract(v2), "subtract vector don't work, " +
                "the first vector positive the second negative");
        assertEquals(new Vector(-3.0, -6.0, -9.0), v2.subtract(v1), "subtract vector don't work, " +
                "the first vector negative the second positive");
    }

    @Test
    void scale() {
        assertEquals(new Vector(-1, -2, -3), v1.scale(-1), "scale -1 don't flip the direction");
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
        assertTrue(isZero(v1.lengthSquared() - 14), "ERROR: lengthSquared() wrong value");
    }

    /**
     * Test method for {@link Vector#length()} (primitives.Vector)}.
     */
    @Test
    void length() {
        assertTrue(isZero(new Vector(0, 3, 4).length() - 5), "ERROR: length() wrong value");
    }

    @Test
    void normalize() {
        Vector vCopy = new Vector(v1.getHead());
        Vector normalizeCopy = vCopy.normalize();
        assertEquals(vCopy, normalizeCopy, "normalize vector create new vector");
        assertTrue(isZero(vCopy.length() - 1), "normalize vector don't create unit vector");
    }

    @Test
    void normalized() {
        Vector vCopy = new Vector(v1.getHead());
        Vector normalizeCopy = vCopy.normalized();
        assertNotEquals(vCopy, normalizeCopy, "normalized vector change the source vector");
        assertTrue(isZero(normalizeCopy.length() - 1), "normalized vector don't create unit vector");

    }
}