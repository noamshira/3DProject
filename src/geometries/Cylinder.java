package geometries;

import primitives.Ray;

/**
 * class fot cylinder in 3D space
 */
public class Cylinder extends Tube {
    final double _height;

    /**
     * constructor for cylinder
     *
     * @param axisRay ray for the axis of the cylinder
     * @param radius  the radius of the cylinder
     * @param height  the heigth of the cylinder
     */
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
