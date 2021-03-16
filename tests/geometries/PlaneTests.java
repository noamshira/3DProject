package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTests {

    @Test
    void getNormal() {
        Plane p = new Plane(new Point3D(1, 2, 3), new Vector(5, 0, 4));
        assertEquals(new Vector(5.0, 0.0, 4.0), p.getNormal(null));
    }
}