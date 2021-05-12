package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * class for plane in 3D space
 */
public class Plane extends Geometry {
    final Point3D _q0;
    final Vector _normal;

    /**
     * constructor of plane
     *
     * @param p0     point on the plane
     * @param normal normal vector to the plane
     */
    public Plane(Point3D p0, Vector normal) {
        _q0 = p0;
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
        _q0 = p1;

        //calculate normal to the plane with 3 points
        //v1 = p2-p1
        //v2= p3-p1
        //the normal is : v1 X v2
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        Vector normal = v1.crossProduct(v2);

        normal.normalize();
        _normal = normal;
    }

    public Point3D getQ0() {
        return _q0;
    }

    @Override
    public Vector getNormal(Point3D p) {
        return _normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        /*
        Ray points: 𝑃 = 𝑃0 + 𝑡 ∙ 𝑣, 𝑡 > 0
                𝑡 = (𝑁 ∙ (𝑄0 − 𝑃0)) / (𝑁 ∙ 𝑣)
        */
        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();

        //if q0 == p0 t is 0
        if (p0.equals(_q0)) return null;
        //𝑁 ∙ (𝑄0 − 𝑃0)
        double nQMinusP0 = _normal.dotProduct(_q0.subtract(p0));
        //if 𝑁 ∙ (𝑄0 − 𝑃0) is 0 -> t is 0
        if (isZero(nQMinusP0)) return null;
        //𝑁 ∙ 𝑣
        double nv = _normal.dotProduct(v);
        //if 𝑁 ∙ 𝑣 is 0 t is undefine and there is no intersection
        if (isZero(nv)) return null;
        double t = alignZero(nQMinusP0 / nv);
        if (t <= 0) return null;
        //𝑃 = 𝑃0 + 𝑡 ∙ 𝑣
        Point3D p = ray.getPoint(t);
        List<Point3D> l = new ArrayList<Point3D>();
        l.add(p);
        return l;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<Point3D> l = findIntersections(ray);
        if (l == null) return null;
        return List.of(new GeoPoint(this, l.get(0)));
    }

    @Override
    public String toString() {
        return "_p0=" + _q0.toString() + ", _normal=" + _normal.toString();
    }
}
