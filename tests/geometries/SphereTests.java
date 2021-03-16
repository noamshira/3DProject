package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class SphereTests {

    /**
     * test method for
     * {@link Sphere#getNormal(Point3D)}
     */
    @Test
    void getNormal() {
        //test of hetting the right normal to the sphere
        Sphere s = new Sphere(new Point3D(0, 0, 1), 1);
        assertEquals(new Vector(0.0, 1.0, 0.0), s.getNormal(new Point3D(0, 1, 1)), "wrong normal");
    }
}