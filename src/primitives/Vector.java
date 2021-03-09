package primitives;

import static primitives.Util.isZero;

/**
 *
 */
public class Vector {
    private Point3D _head;

    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = head;
    }

    public Vector(double x, double y, double z) {
        if (x == 0 && y == 0 && z == 0) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = new Point3D(x, y, z);
    }

    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (x.equals(0) && y.equals(0) && z.equals(0)) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = new Point3D(x, y, z);
    }


    public Point3D getHead() {
        return _head;
    }

    public Vector add(Vector v) {
        return new Vector(_head.add(v));
    }

    public Vector subtract(Vector v) {
        return _head.subtract(v._head);
    }

    public Vector scale(double a) {
        return new Vector(a * _head._x, a * _head._y, a * _head._z);
    }

    public double dotProduct(Vector v) {
        return _head._x * v._head._x + _head._y * v._head._y + _head._z * v._head._z;
    }

    public Vector crossProduct(Vector v) {
        double x = _head._y * v._head._z - _head._z * v._head._y;
        double y = _head._z * v._head._x - _head._x * v._head._z;
        double z = _head._x * v._head._y - _head._y * v._head._x;
        return new Vector(x, y, z);
    }

    public double lengthSquared() {
        return _head._x * _head._x + _head._y * _head._y + _head._z * _head._z;
    }

    public double length() {
        return Math.sqrt(lengthSquared());
    }

    public Vector normalize() {
        double len = length();
        _head = new Point3D(_head._x / len, _head._y / len, _head._z / len);
        return this;
    }

    public Vector normalized() {
        Vector v = new Vector(_head._x, _head._y, _head._z);
        return v.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return _head.equals(other._head);
    }

    @Override
    public String toString() {
        return "head=" + _head.toString();
    }
}
