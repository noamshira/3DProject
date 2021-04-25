package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

/**
 * class for camera
 */
public class Camera {
    private final Point3D _p0;
    private final Vector _vUp;
    private final Vector _vTo;
    private final Vector _vRight;
    private double width;
    private double height;
    private double distance;

    /**
     * constructor of camera
     *
     * @param p0  -location of the camera
     * @param vUp - up direction vector
     * @param vTo - forward direction vector
     */
    public Camera(Point3D p0, Vector vUp, Vector vTo) {
        this._p0 = p0;
        this._vUp = vUp.normalize();
        this._vTo = vTo.normalize();
        if (!Util.isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("the camera vectors must be orthogonal the one to the other");
        }
        Vector v = vUp.crossProduct(vTo);
        _vRight = v.normalize();
    }

    public Point3D getP0() {
        return _p0;
    }

    public Vector getvUp() {
        return _vUp;
    }

    public Vector getvTo() {
        return _vTo;
    }

    public Vector getvRight() {
        return _vRight;
    }

    /**
     * set the view plane width and height
     *
     * @param width
     * @param height
     * @return the camera
     */
    public Camera setVpSize(double width, double height) {
        this.height = height;
        this.width = width;
        return this;
    }

    /**
     * set the view plane distance
     *
     * @param distance of the view plane of the camera
     * @return the camera
     */
    public Camera setVpDistance(double distance) {
        this.distance = distance;
        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        return null;
    }
}
