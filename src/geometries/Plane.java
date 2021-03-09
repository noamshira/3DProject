package geometries;

import primitives.Point3D;
import primitives.Vector;

public class Plane implements Geometry {
    private Point3D _p0;
    private Vector _normal;

    public Plane(Point3D p0, Vector normal) {
        _p0 = p0;
        _normal = normal;
    }

    public Plane(Point3D p1, Point3D p2, Point3D p3) {
        _p0 = p1;
        _normal = null;
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getNormal() {
        return _normal;
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
