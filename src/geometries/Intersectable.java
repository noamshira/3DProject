package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;

/***
 * interface for intersection of ray with geometries
 */
public interface Intersectable {
    /**
     * find the points of the intersection between ray and the geometry
     *
     * @param ray the ray to check
     * @return list of point of the intersection
     */
    List<Point3D> findIntersections(Ray ray);
}
