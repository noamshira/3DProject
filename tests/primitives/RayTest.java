package primitives;

import geometries.Intersectable;
import geometries.Sphere;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * test method for
     * {@link Ray#findClosestPoint(List)}
     */
    @Test
    void findClosestPoint() {
        Ray r = new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0));
        List<Point3D> lst = new ArrayList<Point3D>();
        lst.add(new Point3D(20, 0, 0));
        lst.add(new Point3D(22, 0, 0));
        lst.add(new Point3D(2, 0, 0));
        lst.add(new Point3D(223, 0, 0));
        lst.add(new Point3D(122, 0, 0));

        Point3D p = r.findClosestPoint(lst);
        assertEquals(new Point3D(2, 0, 0), p, "find closer point in the middle of the list");

        assertNull(r.findClosestPoint(new ArrayList<Point3D>()), "return null to empty list");

        lst = new ArrayList<Point3D>();
        lst.add(new Point3D(2, 0, 0));
        lst.add(new Point3D(20, 0, 0));
        lst.add(new Point3D(22, 0, 0));
        lst.add(new Point3D(223, 0, 0));
        lst.add(new Point3D(122, 0, 0));
        p = r.findClosestPoint(lst);
        assertEquals(new Point3D(2, 0, 0), p, "find closer point at the head of the list");

        lst = new ArrayList<Point3D>();
        lst.add(new Point3D(20, 0, 0));
        lst.add(new Point3D(22, 0, 0));
        lst.add(new Point3D(223, 0, 0));
        lst.add(new Point3D(122, 0, 0));
        lst.add(new Point3D(2, 0, 0));
        p = r.findClosestPoint(lst);
        assertEquals(new Point3D(2, 0, 0), p, "find closer point at the tail of the list");

    }


    /**
     * test method for
     * {@link Ray#findClosestGeoPoint(List)}
     */
    @Test
    void findClosestGeoPoint() {
        Ray r = new Ray(new Point3D(1, 0, 0), new Vector(1, 0, 0));
        Sphere s = new Sphere(new Point3D(0, 0, 1), 1);
        List<Intersectable.GeoPoint> lst = new ArrayList<Intersectable.GeoPoint>();
        lst.add(new Intersectable.GeoPoint(s, new Point3D(20, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(22, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(223, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(122, 0, 0)));

        Intersectable.GeoPoint p = r.findClosestGeoPoint(lst);
        assertEquals(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)), p, "find closer GeoPoint in the middle of the list");

        assertNull(r.findClosestGeoPoint(new ArrayList<Intersectable.GeoPoint>()), "return null to empty list");

        lst = new ArrayList<Intersectable.GeoPoint>();
        lst.add(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(20, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(22, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(223, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(122, 0, 0)));
        p = r.findClosestGeoPoint(lst);
        assertEquals(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)), p, "find closer GeoPoint at the head of the list");

        lst = new ArrayList<Intersectable.GeoPoint>();
        lst.add(new Intersectable.GeoPoint(s, new Point3D(20, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(22, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(223, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(122, 0, 0)));
        lst.add(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)));
        p = r.findClosestGeoPoint(lst);
        assertEquals(new Intersectable.GeoPoint(s, new Point3D(2, 0, 0)), p, "find closer GeoPoint at the tail of the list");

    }

}