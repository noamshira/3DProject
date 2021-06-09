package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    default List<Point3D> findIntersections(Ray ray) {
        List<Intersectable.GeoPoint> geoList = findGeoIntersections(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }


    /**
     * find the points of the intersection between ray and the geometry
     * and return the point and the geometry
     *
     * @param ray ray to the geometry
     * @return list of the the intersections of the ray and the geometries
     */
    List<GeoPoint> findGeoIntersections(Ray ray);

    /**
     * inner class for construct of geometry and intersection point
     */
    public static class GeoPoint {
        public Geometry geometry; //the intersected geometries
        public Point3D point; //the point of the intersection

        // ***************** Constructors ********************** //

        /**
         * constructor of GeoPoint
         *
         * @param geometry the in
         * @param point    //the point of the intersection
         */
        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        // ***************** Override ********************** //

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.getClass() == geoPoint.geometry.getClass() && point.equals(geoPoint.point);
        }


    }


}
