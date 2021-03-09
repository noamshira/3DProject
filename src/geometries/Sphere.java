package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Sphere implements Geometry{
    private Point3D _center;
    private double _radius;

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
        return null;
    }

    @Override
    public String toString() {
        return "_center=" + _center +
                ", _radius=" + _radius;
    }
}
