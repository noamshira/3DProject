package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
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

    @Test
    void findIntsersections() {
        //test intersection with the plane, not parallel and not orthogonal
        Plane p = new Plane(new Point3D(1, 0, 0), new Vector(0, 1, 0));
        assertEquals(new Point3D(0, 0, 0), p.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, -1, 0))), "not find basic intersection plane");

        //test tay that don't intersection with the plane
        assertNull(p.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 1, 0))), "the ray not intersection with the palne");

        //----- BVA Case -----

        //group 1: ray  to the plane
        //ray don't include in the plane
        assertNull(p.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))), "the right answer is that the ray is parallel and don't intersection twith the palne");

        //ray is include in the plane
        assertEquals(new Point3D(2, 0, 0), p.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(1, 0, 0))), "not find basic intersection plane");

        //group 2: the ray is orthogonal to the plane
        //the ray is before the plane
        assertEquals(new Point3D(1, 0, 0), p.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, 1, 0))).get(0), "dont find the intersection with ray orthogonal to the plane, before the plane ");

        //the ray is in the plane
        assertEquals(new Point3D(1, 0, 0), p.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(0, 1, 0))).get(0), "dont find the intersection with ray orthogonal to the plane, in the plane ");

        //the ray is after the plane
        assertNull(p.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(0, 1, 0))), "the ray is orthogonal to the plane, after the plane");
    }
}