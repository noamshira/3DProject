package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface to all the geometries
 */
public abstract class Geometry implements Intersectable {

    protected Color emmission = Color.BLACK;

    public Color getEmmission() {
        return emmission;
    }

    public Geometry setEmmission(Color emmission) {
        this.emmission = emmission;
        return this;
    }

    /**
     * return the normal to the geometry
     *
     * @param p point on the geometry
     * @return normal to the geometry
     */
    public abstract Vector getNormal(Point3D p);
}
