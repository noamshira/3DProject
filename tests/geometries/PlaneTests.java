package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Plane
 */
class PlaneTests {

    /**
     * test method for
     * {@link Plane#getNormal(Point3D)}
     */
    @Test
    void getNormal() {
        //test of get the right normal to the plane
        Plane p = new Plane(new Point3D(1, 2, 3), new Vector(5, 0, 4));
        assertEquals(new Vector(5.0, 0.0, 4.0), p.getNormal(null), "wrong normal");
    }
}