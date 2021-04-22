package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing Geometries
 */
class GeometriesTests {

    /**
     * Test method for
     * {@link Geometries#add(Intersectable...)}.
     */
    @Test
    void add() {
        Geometries g = new Geometries(new Sphere(new Point3D(1, 0, 0), 1d),
                new Sphere(new Point3D(5, 0, 0), 1d));
        g.add(new Sphere(new Point3D(10, 0, 0), 1d));
        assertEquals(3, g.getLst().size(), "add to lst don't work");
    }


    /**
     * Test method for
     * {@link Geometries#findIntersections(Ray)}.
     */
    @Test
    void findIntersections() {

        // =============== Boundary Values Tests ==================
        //empty list
        Geometries g = new Geometries();
        assertNull(g.findIntersections(new Ray(new Point3D(1, 1, 1), new Vector(1, 0, 0))),
                "empty list");

        g.add(new Sphere(new Point3D(1, 0, 0), 1d));
        g.add(new Sphere(new Point3D(5, 0, 0), 1d));
        g.add(new Triangle(new Point3D(10, -1, 1), new Point3D(10, 2, 0), new Point3D(10, -1, -1)));
        //no intersection
        assertNull(g.findIntersections(new Ray(new Point3D(0, 10, 0), new Vector(1, 0, 0))),
                "no intersection");

        //only 1 geometry intersect
        assertEquals(2, g.findIntersections(new Ray(new Point3D(1, -3, 0), new Vector(0, 1, 0))).size(),
                "only 1 geometry intersect");

        //all the geometries intersect
        assertEquals(5, g.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 0, 0))).size(),
                "all the geometries intersect");

        // ============ Equivalence Partitions Tests ==============
        //some of the geometries intersect but not everyone
        assertEquals(4, g.findIntersections(new Ray(new Point3D(8, 0, 0), new Vector(-1, 0, 0))).size(),
                "some of the geometries intersect but not everyone");

    }


}