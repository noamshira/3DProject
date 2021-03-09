package geometries;

import primitives.Ray;

public class Cylinder extends Tube {
    private double _height;

    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }

    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "_height=" + _height;
    }
}
