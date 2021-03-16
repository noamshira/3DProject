package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * class for sphere in 3D space
 */
public class Sphere implements Geometry {
    final Point3D _center;
    final double _radius;

    /**
     * constructor for sphere
     *
     * @param center the center of the sphere
     * @param radius the radius of the sphere
     */
    public Sphere(Point3D center, double radius) {
        _center = center;
        _radius = radius;
    }

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector v = p.subtract(_center);
        try {
            v.normalize();
        } catch (ArithmeticException e) {
            return null;
        }
        return v;
    }

    @Override
    public String toString() {
        return "_center=" + _center +
                ", _radius=" + _radius;
    }
}
