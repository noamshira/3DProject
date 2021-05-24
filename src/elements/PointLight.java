package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class PointLight extends Light implements LightSource {

    private Point3D position;
    private double kC;
    private double kL;
    private double kQ;

    public PointLight(Color intensity, Point3D position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkL(double kL) {
        this.kL = kL;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point3D p) {
        double distance = position.distance(p);
        return getIntensity().reduce(kC + (kL * distance) + (kQ * (distance * distance)));
    }

    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position);
    }
}
