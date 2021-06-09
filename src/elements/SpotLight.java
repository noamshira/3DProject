package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for spot light source
 */
public class SpotLight extends PointLight {
    private Vector direction; //the direction of the light from the spot light

    // ***************** Constructor ********************** //

    /**
     * constructor of spot light
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light source
     * @param direction the direction of the light
     */
    public SpotLight(Color intensity, Point3D position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalized();
    }

    // ***************** Overriders ********************** //

    @Override
    public Color getIntensity(Point3D p) {
        /**
         * 𝑰𝑳 =(𝑰𝟎 ∙ 𝒎𝒂𝒙( 𝟎, 𝒅𝒊𝒓 ∙ 𝒍))/(𝒌𝒄 + 𝒌𝒍∙ 𝒅 + 𝒌𝒒∙ 𝒅^2)
         */
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(super.getL(p))));
    }

    @Override
    public Vector getL(Point3D p) {
        return super.getL(p);
    }
}
