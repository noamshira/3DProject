package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for light source
 */
public interface LightSource {
    /**
     * return the intensity of the light source
     */
    public Color getIntensity(Point3D p);

    /*
     *return the vector form the light to the point
     * @param p point to check
     * @return the vector from the light to the point
     */
    public Vector getL(Point3D p);

    /*
     *return the distance from the point to the light source
     * @param point
     * @return
     */
    double getDistance(Point3D point);

}
