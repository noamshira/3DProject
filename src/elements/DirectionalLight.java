package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for directional light source
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    // ***************** Constructor ********************** //

    /**
     * constructor for directional light
     * @param intensity the intensity of the light
     * @param direction the direction of the light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        this.direction = direction.normalized();
    }

    // ***************** overrides ********************** //

    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    @Override
    public Vector getL(Point3D p) {
        return direction;
    }

    @Override
    public double getDistance(Point3D point) {
        return Double.POSITIVE_INFINITY;
    }
}
