package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * interface to all the geometries
 */
public interface Geometry {

    /**
     * return the normal to the geometry
     *
     * @param p point on the geometry
     * @return normal to the geometry
     */
    public Vector getNormal(Point3D p);
}
