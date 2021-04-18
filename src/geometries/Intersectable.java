package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/***
 * interface for intersection of ray with geometries
 */
public interface Intersectable {
    /**
     * find the pion of the intersection between ray and the geometry
     *
     * @param ray
     * @return list of point of the intersection
     */
    List<Point3D> findIntersections(Ray ray);
}
