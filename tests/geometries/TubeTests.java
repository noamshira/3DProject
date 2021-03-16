package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing Tube
 */
class TubeTests {

    /**
     * test method for
     * {@link Tube#getNormal(Point3D)}
     */
    @Test
    void getNormal() {
        //testing getting the right normal to the tube
        Ray ray = new Ray(new Point3D(0, 1, 0), new Vector(0, 1, 0));
        Tube tb = new Tube(ray, 2);

        assertEquals(tb.getNormal(new Point3D(0, 0, 2)), new Vector(0, 0, 1), "wrong normal");
    }
}