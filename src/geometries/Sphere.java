package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

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

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        Vector u = _center.subtract(ray.getP0());
        double tm = ray.getDir().dotProduct(u);
        double lenU = u.length();
        double d = Math.sqrt((lenU * lenU) - (tm * tm));
        if (d >= _radius) {
            return null;
        }
        double th = Math.sqrt((_radius * _radius) - (d * d));
        double t1 = tm + th;
        double t2 = tm - th;
        List<Point3D> l = new LinkedList<Point3D>();
        if (t1 > 0) {
            l.add(ray.getP0().add(ray.getDir().scale(t1)));
        }
        if (t2 > 0) {
            l.add(ray.getP0().add(ray.getDir().scale(t2)));
        }
        return l;
    }

}
