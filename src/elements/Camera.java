package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

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
    public Camera(Point3D p0, Vector vTo, Vector vUp) {
        this._p0 = p0;
        this._vUp = vUp.normalize();
        this._vTo = vTo.normalize();
        if (!Util.isZero(vUp.dotProduct(vTo))) {
            throw new IllegalArgumentException("the camera vectors must be orthogonal the one to the other");
        }
        Vector v = vTo.crossProduct(vUp);
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

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getDistance() {
        return distance;
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

    /**
     * construct ray ray from the camera through the center of the pixel
     *
     * @param nX number of pixel's columns
     * @param nY number of pixel's rows
     * @param j  index of the row of the pixel
     * @param i  index of the culomn of the pixel
     * @return ray from the camera through the center of the pixel
     */
    public Ray constructRayThroughPixel(double nX, double nY, double j, double i) {
        /*
         Image center
         𝑃𝑐 = 𝑃0 + 𝑑 ∙ Vto
         */
        Point3D pCenter = _p0.add(_vTo.scale(distance));
        /*
         Ratio (pixel width & height)
         𝑅𝑦 = ℎ/𝑁𝑦
         𝑅𝑥 = 𝑤/𝑁𝑥
         */
        double rY = height / nY;
        double rX = width / nX;
        /*
         Pixel[i,j] center
         𝑃𝑖,𝑗 = 𝑃𝑐 + (𝑥𝑗∙ V𝑟𝑖𝑔ℎ𝑡 + 𝑦𝑖∙ V𝑢𝑝)
         𝑦𝑖 = −(𝑖 – (𝑁𝑦 − 1)/2) ∙ 𝑅𝑦
         𝑥𝑗 = (𝑗 – (𝑁𝑥 − 1)/2) ∙ 𝑅𝑥
         */
        double yI = -1 * (i - (nY - 1) / 2) * rY;
        double xJ = (j - (nX - 1) / 2) * rX;
        Point3D pIJ = pCenter;
        if (xJ != 0) pIJ = pIJ.add(_vRight.scale(xJ));
        if (yI != 0) pIJ = pIJ.add(_vUp.scale(yI));
        /*
         𝒗𝒊,𝒋 = 𝑷𝒊,𝒋 − 𝑷𝟎
         𝑹𝒂𝒚: 𝒑𝟎 = 𝑷𝟎, 𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏 = 𝒗𝒊,𝒋
         */
        Vector vIJ = pIJ.subtract(_p0);
        return new Ray(_p0, vIJ);
    }

    public List<Ray> constructBeamThroughPixel(double nX, double nY, double j, double i, int bemNum) {
        List<Ray> lst = new ArrayList<Ray>();
        /*
         Image center
         𝑃𝑐 = 𝑃0 + 𝑑 ∙ Vto
         */
        Point3D pCenter = _p0.add(_vTo.scale(distance));
        /*
         Ratio (pixel width & height)
         𝑅𝑦 = ℎ/𝑁𝑦
         𝑅𝑥 = 𝑤/𝑁𝑥
         */
        double rY = height / nY;
        double rX = width / nX;
        /*
         Pixel[i,j] center
         𝑃𝑖,𝑗 = 𝑃𝑐 + (𝑥𝑗∙ V𝑟𝑖𝑔ℎ𝑡 + 𝑦𝑖∙ V𝑢𝑝)
         𝑦𝑖 = −(𝑖 – (𝑁𝑦 − 1)/2) ∙ 𝑅𝑦
         𝑥𝑗 = (𝑗 – (𝑁𝑥 − 1)/2) ∙ 𝑅𝑥
         */
        double yI = -1 * (i - (nY - 1) / 2) * rY;
        double xJ = (j - (nX - 1) / 2) * rX;
        Point3D pixelCenter = pCenter;
        if (xJ != 0) pixelCenter = pixelCenter.add(_vRight.scale(xJ));
        if (yI != 0) pixelCenter = pixelCenter.add(_vUp.scale(yI));
        /*
         𝒗𝒊,𝒋 = 𝑷𝒊,𝒋 − 𝑷𝟎
         𝑹𝒂𝒚: 𝒑𝟎 = 𝑷𝟎, 𝒅𝒊𝒓𝒆𝒄𝒕𝒊𝒐𝒏 = 𝒗𝒊,𝒋
         */
        Vector vIJ = pixelCenter.subtract(_p0);
        lst.add(new Ray(_p0, vIJ));


        rY = height / nY * bemNum;
        rX = width / nX * bemNum;
        for (int a = 0; a < bemNum; a++) {
            for (int b = 0; b < bemNum; b++) {
                yI = -1 * (i + a - (nY - 1) / 2) * rY;
                xJ = (j + b - (nX - 1) / 2) * rX;
                Point3D pIJ = pCenter;
                if (xJ != 0) pIJ = pIJ.add(_vRight.scale(xJ));
                if (yI != 0) pIJ = pIJ.add(_vUp.scale(yI));
                vIJ = pIJ.subtract(_p0);
                lst.add(new Ray(_p0, vIJ));
            }
        }
        return lst;
    }
}
