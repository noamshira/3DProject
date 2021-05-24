package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * class for tube in 3D space
 */
public class Tube extends Geometry {
    final Ray _axisRay;
    final double _radius;

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
        //normal to tube calculate by the formula P - O
        //P is point on the tube
        //O is projection of P on cylinder's ray
        //O is calculate by:
        //t = v * (P - P0)
        //O = P0 + t * v
        //where v is the ray direction and P0 is point on the ray
        Vector pP0 = p.subtract(_axisRay.getP0());
        double t = _axisRay.getDir().dotProduct(pP0);
        Vector vT = _axisRay.getDir().scale(t);
        Vector o;
        try {
            o = new Vector(_axisRay.getP0().add(vT));
        } catch (IllegalArgumentException e) {
            //if the point o is (0,0,0) it can't be vector but this point exist so calculate it
            return p.subtract(Point3D.ZERO).normalize();
        }
        return p.subtract(o.getHead()).normalize();
    }

    @Override
    public String toString() {
        return "_axisRay=" + _axisRay +
                ", _radius=" + _radius;
    }


    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return null;
    }
}
