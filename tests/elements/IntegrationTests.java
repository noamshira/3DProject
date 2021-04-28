package elements;

import geometries.Geometry;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * class for test the integration of the camera and the intersection of the geometries
 */
public class IntegrationTests {

    double nY = 3;
    double nX = 3;

    Vector vUp = new Vector(0, 1, 0);
    Vector vTo = new Vector(0, 0, -1);

    Camera camera1 = new Camera(new Point3D(0, 0, 0), vTo, vUp).setVpDistance(1)
            .setVpSize(3, 3);

    Camera camera2 = new Camera(new Point3D(0, 0, 0.5), vTo, vUp).setVpDistance(1)
            .setVpSize(3, 3);

    @Test
    public void testsphere() {
        //test method for intersection of the rays from the camera and sphere

        //small sphere against the center of the view plane
        Sphere sphere = new Sphere(new Point3D(0, 0, -3), 1);
        int result = constructRayThroughAllPixels(camera1, nY, nX, sphere);
        assertEquals(2, result, "wrong result in sphere of radius 1 with 2 intersections");

        //big sphere against the center of the view plane
        sphere = new Sphere(new Point3D(0, 0, -2.5), 2.5);
        result = constructRayThroughAllPixels(camera2, nY, nX, sphere);
        assertEquals(18, result, "wrong result in sphere of radius 2.5 with 18 intersections");

        //medium sphere against the center of the view plane
        sphere = new Sphere(new Point3D(0, 0, -2), 2);
        result = constructRayThroughAllPixels(camera2, nY, nX, sphere);
        assertEquals(10, result, "wrong result in sphere of radius 2 with 10 intersections");

        //the sphere include the view plane
        sphere = new Sphere(new Point3D(0, 0, -0.5), 4);
        result = constructRayThroughAllPixels(camera2, nY, nX, sphere);
        assertEquals(9, result, "wrong result in sphere of radius 4 with 9 intersections");

        //the sphere behind the camera
        sphere = new Sphere(new Point3D(0, 0, 1), 0.5);
        result = constructRayThroughAllPixels(camera2, nY, nX, sphere);
        assertEquals(0, result, "wrong result in sphere before the camera without intersection");
    }

    @Test
    public void testPlane() {
        //test method for intersection of the rays from the camera and plane

        //the plane parallel to the view plane
        Plane plane = new Plane(new Point3D(0, 0, -5), vTo);
        int result = constructRayThroughAllPixels(camera2, nY, nX, plane);
        assertEquals(9, result, "plane parallel to the view plane");

        //plane not parallel to the view plane with 9 intersections
        plane = new Plane(new Point3D(0, 0, -1), new Vector(0, 0.1, -5));
        result = constructRayThroughAllPixels(camera2, nY, nX, plane);
        assertEquals(9, result, "plane not parallel to the view plane with 9 intersections");

        //plane not parallel to the view plane with 6 intersections
        plane = new Plane(new Point3D(0, 0, -5), new Vector(0, -1, 0.5));
        result = constructRayThroughAllPixels(camera2, nY, nX, plane);
        assertEquals(6, result, "plane not parallel to the view plane with 6 intersections");

    }

    @Test
    public void testTriangle() {
        //test method for intersection of the rays from the camera and triangle

        //small triangle against the center of the view plane
        Triangle triangle = new Triangle(new Point3D(0, 1, -2), new Point3D(-1, -1, -2),
                new Point3D(1, -1, -2));
        int result = constructRayThroughAllPixels(camera2, nY, nX, triangle);
        assertEquals(1, result, "small triangle against the center of the view plane");

        //big triangle against the center of the view plane
        triangle = new Triangle(new Point3D(0, 20, -2), new Point3D(-1, -1, -2),
                new Point3D(1, -1, -2));
        result = constructRayThroughAllPixels(camera2, nY, nX, triangle);
        assertEquals(2, result, "big triangle against the center of the view plane");

    }

    /**
     * method construct rays through the view plane
     * and count the number of the intersections of the rays with the geometry
     *
     * @param camera the camera withe the view plane set
     * @param nY     number of pixel's columns
     * @param nX     number of pixel's rows
     * @param g      geometry to check
     * @return number of intersections
     */
    public int constructRayThroughAllPixels(Camera camera, double nY, double nX, Geometry g) {
        List<Ray> l = new ArrayList<Ray>();
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                l.add(camera.constructRayThroughPixel(nX, nY, j, i));
            }
        }
        int counter = 0;
        List<Point3D> res;
        for (int i = 0; i < l.size(); i++) {
            res = g.findIntersections(l.get(i));
            if (res != null) counter += res.size();
        }
        return counter;
    }

}
