package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class for plane in 3D space
 */
public class Plane implements Geometry {
    final Point3D _p0;
    final Vector _normal;

    /**
     * constructor of plane
     *
     * @param p0     point on the plane
     * @param normal normal vector to the plane
     */
    public Plane(Point3D p0, Vector normal) {
        _p0 = p0;
        _normal = normal;
    }

    /**
     * constructor of plane
     *
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _p0 = p1;
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector normal = v1.crossProduct(v2);
        normal.normalize();
        _normal = normal;
    }

    public Point3D getP0() {
        return _p0;
    }


    @Override
    public String toString() {
        return "_p0=" + _p0.toString() + ", _normal=" + _normal.toString();
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }
}
