package primitives;

import static primitives.Util.*;

/**
 * Class Point3D is basic class for point in 3 Dimension
 */
public class Point3D {
    final Coordinate _x;
    final Coordinate _y;
    final Coordinate _z;

    public static Point3D ZERO = new Point3D(0, 0, 0);

    /**
     * Point in 3D
     *
     * @param x x axis value
     * @param y y axis value
     * @param z z axis value
     */
    public Point3D(double x, double y, double z) {
        this._x = new Coordinate(x);
        this._y = new Coordinate(y);
        this._z = new Coordinate(z);
    }

    /***
     * Point in 3D
     * @param x x axis value
     * @param y y axis value
     * @param z z axis value
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        this._x = x;
        this._y = y;
        this._z = z;
    }

    /**
     * function that calculates the distance between two points squared
     */
    public double distanceSquared(Point3D p) {
        return (_x.coord - p._x.coord) * (_x.coord - p._x.coord) + (_y.coord - p._y.coord) * (_y.coord - p._y.coord)
                + (_z.coord - p._z.coord) * (_z.coord - p._z.coord);
    }

    /**
     * A function that calculates the distance between two points
     */
    public double distance(Point3D p) {
        return Math.sqrt(distanceSquared(p));
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D) obj;
        return _x.equals(other._x) && _y.equals(other._y) && _z.equals(other._z);
    }

    @Override
    public String toString() {
        return "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z;
    }

    /**
     * vector addiction
     *
     * @param v the vector to add
     * @return point of the head of the new vector
     */
    public Point3D add(Vector v) {
        return new Point3D(_x.coord + v.getHead()._x.coord,
                _y.coord + v.getHead()._y.coord,
                _z.coord + v.getHead()._z.coord);
    }

    /**
     * vector subtract
     *
     * @param p the head of the vector to subtract
     * @return result vector
     */
    public Vector subtract(Point3D p) {
        return new Vector(_x.coord - p._x.coord, _y.coord - p._y.coord, _z.coord - p._z.coord);
    }
}
