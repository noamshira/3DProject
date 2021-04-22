package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;


/**
 * testing Triangle
 */
class TriangleTests {


    /**
     * Test method for
     * {@link Triangle#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {
        Triangle t = new Triangle(new Point3D(1, 0, 0), new Point3D(2, 1, 0), new Point3D(3, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        //Inside triangle
        assertEquals(new Point3D(2, 0.5, 0),
                t.findIntersections(new Ray(new Point3D(2, 0.5, 1), new Vector(0, 0, -1))).get(0),
                "intersection Inside triangle");

        //Outside against edge
        assertNull(t.findIntersections(new Ray(new Point3D(1.5, 1.8, 1), new Vector(0, 0, -1))),
                "Outside against edge");

        //Outside against vertex
        assertNull(t.findIntersections(new Ray(new Point3D(2, 2, 1), new Vector(0, 0, -1))),
                "Outside against vertex");

        // =============== Boundary Values Tests ==================

        //the ray is on edge
        assertNull(t.findIntersections(new Ray(new Point3D(2, 0, 1), new Vector(0, 0, -1))),
                "the ray is on edge");

        //the ray is in vortex
        assertNull(t.findIntersections(new Ray(new Point3D(2, 1, 1), new Vector(0, 0, -1))),
                "the ray is in vortex");

        //the ray is on edge's continuation
        assertNull(t.findIntersections(new Ray(new Point3D(3, 2, 1), new Vector(0, 0, -1))),
                "the ray is on edge's continuation");

    }
}