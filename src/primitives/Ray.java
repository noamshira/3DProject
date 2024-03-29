package primitives;

import geometries.Intersectable;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * class for ray in 3D space
 */
public class Ray {
    final Point3D _p0;
    final Vector _dir;
    private static final double DELTA = 0.1; //const for the shift of the shadow rays

    // ***************** Constructors ********************** //

    /**
     * constructor for ray
     *
     * @param p0  point on the ray
     * @param dir direction vector for the ray
     */
    public Ray(Point3D p0, Vector dir) {
        _p0 = p0;
        _dir = dir.normalized();
    }

    /**
     * constructor ray with delta for ray of shadows or reflection or refraction
     *
     * @param head      the head of the ray
     * @param direction the direction of the ray
     * @param normal    the normal of the geometry to calculate the sign of the delta
     */
    public Ray(Point3D head, Vector direction, Vector normal) {
        Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : -DELTA);
        _p0 = head.add(delta);
        _dir = direction.normalized();
    }

    // ***************** Operations ********************** //

    /**
     * find the closer point of intersection of ray from list of points
     *
     * @param lst lit of point of intersection with the ray
     * @return the closer point
     */
    public Point3D findClosestPoint(List<Point3D> lst) {
        if (lst == null || lst.size() == 0)
            return null;
        Point3D p = lst.get(0);
        for (int i = 1; i < lst.size(); i++) {
            if (_p0.distance(p) > _p0.distance(lst.get(i)))
                p = lst.get(i);
        }
        return p;
    }

    /**
     * find the closer point of intersection of ray from list of GeoPoints
     *
     * @param lst lit of point of intersection with the ray
     * @return the closer GeoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> lst) {
        if (lst == null || lst.size() == 0)
            return null;
        GeoPoint p = lst.get(0);
        for (int i = 1; i < lst.size(); i++) {
            if (_p0.distance(p.point) > _p0.distance(lst.get(i).point))
                p = lst.get(i);
        }
        return p;
    }

    // ***************** Operation ********************** //

    /**
     * get point on the ray by the scalar t
     *
     * @param t scalar for the direction vector
     * @return Point3D on the ray
     */
    public Point3D getPoint(double t) {
        return _p0.add(_dir.scale(t));
    }

    // ***************** Getters ********************** //

    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    // ***************** Overrides ********************** //

    @Override
    public String toString() {
        return "_p0=" + _p0.toString() + ", _dir=" + _dir.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return _p0.equals(other._p0) && _dir.equals(other._dir);
    }
}
