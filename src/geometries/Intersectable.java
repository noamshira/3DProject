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


    List<GeoPoint> findGeoIntersections(Ray ray);

    public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        public GeoPoint(Geometry geometry, Point3D point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return geometry.getClass() == geoPoint.geometry.getClass() && point.equals(geoPoint.point);
        }


    }

}
