package primitives;
import static primitives.Util.*;

/**
 * Class Point3D is basic class for point in 3 Dimension
 */
public class Point3D {
    final double _x;
    final double _y;
    final double _z;

    public static Point3D ZERO = new Point3D(0,0,0);

    /**
     * Point in 3D
     * @param x x axis value
     * @param y y axis value
     * @param z z axis value
     */
    public Point3D(double x, double y, double z) {
        this._x = x;
        this._y = y;
        this._z = z;
    }

    /***
     * Point in 3D
     * @param x x axis value
     * @param y y axis value
     * @param z z axis value
     */
    public Point3D(Coordinate x, Coordinate y, Coordinate z){
        this._x=x.coord;
        this._y=y.coord;
        this._z=z.coord;
    }
/**  function that calculates the distance between two points squared*/
    public double distanceSquared (Point3D p){
        return (_x-p._x)*(_x-p._x) +(_y-p._y)*(_y-p._y) +(_z-p._z)*(_z-p._z);
    }
/** A function that calculates the distance between two points */
    public  double distance(Point3D p){
        return Math.sqrt(distanceSquared(p));
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D) obj;
        return isZero(_x-other._x)&&isZero(_y-other._y)&&isZero(_z-other._z) ;
    }

    @Override
    public String toString() {
        return  "_x=" + _x +
                ", _y=" + _y +
                ", _z=" + _z ;
    }

    /**
     * vector addiction
     * @param v the vector to add
     * @return point of the head of the new vector
     */
    public Point3D add(Vector v) {
        return new Point3D(_x+v.getHead()._x,_y+v.getHead()._y,_z+v.getHead()._z);
    }

    /**
     * vector subtract
     * @param p the head of the vector to subtract
     * @return result vector
     */
    public Vector subtract(Point3D p) {
        return new Vector(_x-p._x,_y-p._y,_z-p._z);
    }
}
