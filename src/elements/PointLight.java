package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class for point light source
 */
public class PointLight extends Light implements LightSource {

    private Point3D position;
    private double kC = 1;
    private double kL = 0;
    private double kQ = 0;
    private double radius = 0;

    // ***************** Constructor ********************** //

    /**
     * constructor for point light
     *
     * @param intensity the intensity of the light
     * @param position  the position of the light source
     */
    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
    }

    // ***************** Setters ********************** //
    public PointLight setKc(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setKl(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setKq(double kQ) {
        this.kQ = kQ;
        return this;
    }

    public PointLight setRadius(double r) {
        radius = r;
        return this;
    }
    // ***************** Overriders ********************** //


    @Override
    public Color getIntensity(Point3D p) {
        /**
         * 𝑰𝑳 =𝑰𝟎/(𝒌𝒄 + 𝒌𝒍∙ 𝒅 + 𝒌𝒒∙ 𝒅^2)
         */
        double distance = p.distance(position);
        //add for soft shadows - if there is a radius the distance is from the surface of the light
        //there for we need to subtract the radius from the distance
        distance -= radius;
        return getIntensity().scale(1 / (kC + kL * distance + kQ * distance * distance));
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalized();
    }

    @Override
    public double getDistance(Point3D point) {
        double distance = point.distance(position);
        //add for soft shadows - if there is a radius the distance is from the surface of the light
        //there for we need to subtract the radius from the distance
        distance -= radius;
        return distance;
    }
}
