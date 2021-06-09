package elements;

import primitives.Point3D;
import primitives.Vector;

/*
interface for light source with size (only squares)
 */
public interface AreaLightSource extends LightSource {
    /**
     * the size of the edges of the light
     *
     * @return double the size of the edges
     */
    double getEdges();

    /**
     * the position of the center
     *
     * @return point of the position of the center
     */
    public Point3D getCenter();

    /**
     * getter to the tangent vector of the light source
     *
     * @return tangent vector
     */
    public Vector getU();

    /**
     * getter to the tangent vector of the light source
     *
     * @return tangent vector
     */
    public Vector getV();
}
