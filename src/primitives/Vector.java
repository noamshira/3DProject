package primitives;

import static primitives.Util.isZero;

/**
 * class for vector in 3d space
 */
public class Vector {
    Point3D _head;

    // ***************** Constructors ********************** //

    /**
     * constructor of vector in 3d space
     *
     * @param head point of the head of the vector
     */
    public Vector(Point3D head) {
        if (head.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = head;
    }

    /**
     * constructor of vector in 3D space
     *
     * @param x x value of the head of the vector
     * @param y y value of the head of the vector
     * @param z z value of the head of the vector
     */
    public Vector(double x, double y, double z) {
        if (x == 0 && y == 0 && z == 0) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = new Point3D(x, y, z);
    }

    /**
     * constructor of vector in 3D space
     *
     * @param x x value of the head of the vector
     * @param y y value of the head of the vector
     * @param z z value of the head of the vector
     */
    public Vector(Coordinate x, Coordinate y, Coordinate z) {
        if (x.equals(0) && y.equals(0) && z.equals(0)) {
            throw new IllegalArgumentException("vector cant be zero");
        }
        this._head = new Point3D(x, y, z);
    }

    // ***************** Getter ********************** //


    public Point3D getHead() {
        return _head;
    }

    // ***************** Operations ********************** //

    /**
     * vector addition
     *
     * @param v the vector to add
     * @return the result vector
     */
    public Vector add(Vector v) {
        Point3D point3D = _head.add(v);
        if (point3D.equals(Point3D.ZERO)) {
            throw new IllegalArgumentException("cant create vector zero");
        }
        return new Vector(point3D);
    }

    /**
     * vector subtract
     *
     * @param v the vector to subtract
     * @return the result vector
     */
    public Vector subtract(Vector v) {
        return _head.subtract(v._head);
    }

    /**
     * multiply vector with scalar
     *
     * @param a the scalar
     * @return the result vector (its new vector)
     */
    public Vector scale(double a) {
        return new Vector(a * _head._x.coord, a * _head._y.coord, a * _head._z.coord);
    }

    /**
     * dot product of 2 vectors
     *
     * @param v vector
     * @return scalar - the result of the dot product
     */
    public double dotProduct(Vector v) {
        //calculate by the formula:
        //x1*x2+y1*y2+z1*z2
        return _head._x.coord * v._head._x.coord + _head._y.coord * v._head._y.coord + _head._z.coord * v._head._z.coord;
    }

    /**
     * cross product of 2 vectors
     *
     * @param v the vector
     * @return the result vector
     */
    public Vector crossProduct(Vector v) {
        //calculate bt the formula:
        //u X v is:
        //x = u2*v3 - u3*v2
        //y = u3*v1 - u1*v3
        //z = u1*v2 - u2*v1
        double x = _head._y.coord * v._head._z.coord - _head._z.coord * v._head._y.coord;
        double y = _head._z.coord * v._head._x.coord - _head._x.coord * v._head._z.coord;
        double z = _head._x.coord * v._head._y.coord - _head._y.coord * v._head._x.coord;
        return new Vector(x, y, z);
    }

    /**
     * the squared length of the vector
     *
     * @return the squared length
     */
    public double lengthSquared() {
        //calculate by the formula:
        //x^2 +y^2 + z^2
        return _head._x.coord * _head._x.coord + _head._y.coord * _head._y.coord + _head._z.coord * _head._z.coord;
    }

    /**
     * the length of the vector
     *
     * @return the length of the vector
     */
    public double length() {
        //calculate by the formula:
        //sqr(x^2 +y^2 + z^2)
        return Math.sqrt(lengthSquared());
    }

    /**
     * normalize the vector (change the original)
     *
     * @return the normalize vector
     */
    public Vector normalize() {
        double len = length();
        Point3D head = new Point3D(_head._x.coord / len, _head._y.coord / len, _head._z.coord / len);
        if (Point3D.ZERO.equals(head)) {
            throw new ArithmeticException("1 / length of the vector is equal to zero");
        }
        _head = head;
        return this;
    }

    /**
     * return normalized vector without change the original vector
     *
     * @return the normalized vector
     */
    public Vector normalized() {
        Vector v = new Vector(_head._x, _head._y, _head._z);
        return v.normalize();
    }

    // ***************** Overrides ********************** //

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
