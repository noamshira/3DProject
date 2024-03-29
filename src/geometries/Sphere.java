package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static primitives.Util.*;

/**
 * class for sphere in 3D space
 */
public class Sphere extends Geometry {
    final Point3D _center; //the center of the sphere
    final double _radius; //the radius of the sphere

    // ***************** Constructor ********************** //

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

    // ***************** Getters ********************** //

    public Point3D getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }

    // ***************** Overrides ********************** //

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
    public List<GeoPoint> findGeoIntersections(Ray ray) {
         /*
        Ray points: 𝑃 = 𝑃0 + 𝑡 ∙ 𝑣, 𝑡 > 0
        Sphere points: |𝑃 − 𝑂|^2 − 𝑟^2 = 0
        𝑢 = 𝑂 − 𝑃0
        𝑡𝑚 = 𝑣 ∙ 𝑢
        𝑑 =sqrt( |𝑢|^2 − 𝑡𝑚^2) ⇨ if (𝒅 ≥ 𝒓) there are no intersections
        𝑡ℎ = sqrt(𝑟^2 − 𝑑^2)
        t1,t2 = 𝑡𝑚 ± 𝑡ℎ, 𝑃𝑖 = 𝑃0 + 𝑡𝑖 ∙ 𝑣, ⇨ take only 𝒕 > 0
        */
        Vector u;
        try {
            u = _center.subtract(ray.getP0());
        }
        // if the ray start on the center the result is zero, and we cant make vector from zero,
        // but by the formula in this case the result is p0 + v*r
        catch (IllegalArgumentException e) {
            List<GeoPoint> l = new ArrayList<GeoPoint>();
            l.add(new GeoPoint(this, ray.getPoint(_radius)));
            return l;
        }
        double tm = ray.getDir().dotProduct(u);
        double lenU = u.length();
        double d = Math.sqrt((lenU * lenU) - (tm * tm));
        if (d >= _radius) {
            return null;
        }
        double th = Math.sqrt((_radius * _radius) - (d * d));
        double t1 = Util.alignZero(tm + th);
        double t2 = Util.alignZero(tm - th);
        List<GeoPoint> l = null;
        if (t1 > 0) {
            l = new ArrayList<GeoPoint>();
            l.add(new GeoPoint(this, ray.getPoint(t1)));
        }
        if (t2 > 0) {
            if (l == null) {
                l = new ArrayList<GeoPoint>();
            }
            l.add(new GeoPoint(this, ray.getPoint(t2)));
        }
        return l;
    }
}
