package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * class for tube in 3D space
 */
public class Tube implements Geometry {
    private Ray _axisRay;
    private double _radius;

    /**
     * constuctor for tube
     *
     * @param axisRay ray for the axis of the tube
     * @param radius  the radius of the tube
     */
    public Tube(Ray axisRay, double radius) {
        _axisRay = axisRay;
        _radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public Vector getNormal(Point3D p) {
        Vector pP0 = p.subtract(_axisRay.getP0());
        double t = _axisRay.getDir().dotProduct(pP0);
        Vector vT = _axisRay.getDir().scale(t);
        Vector o = new Vector(_axisRay.getP0().add(vT));
        return o;
    }

    @Override
    public String toString() {
        return "_axisRay=" + _axisRay +
                ", _radius=" + _radius;
    }
}
