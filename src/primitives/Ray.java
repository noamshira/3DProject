package primitives;

/**
 * class for ray in 3D space
 */
public class Ray {
    private Point3D _p0;
    private Vector _dir;

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


    public Point3D getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

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
