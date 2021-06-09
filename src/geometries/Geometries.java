package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * class for list of geometries
 */
public class Geometries implements Intersectable {

    List<Intersectable> lst;

    // ***************** Constructors ********************** //

    /**
     * default constructor for geometries
     * initialize with empty array list
     */
    public Geometries() {
        this.lst = new ArrayList<Intersectable>();
    }

    /**
     * constructor for geometries
     *
     * @param geometries geometries objects
     */
    public Geometries(Intersectable... geometries) {
        lst = new ArrayList<Intersectable>(List.of(geometries));
    }

    // ***************** Getter ********************** //
    public List<Intersectable> getLst() {
        return lst;
    }

    // ***************** Operation ********************** //

    /**
     * add geometry or geometries to the list
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        lst.addAll(new ArrayList<Intersectable>(List.of(geometries)));
    }

    // ***************** Overrider ********************** //

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable g : lst) {
            List<Intersectable.GeoPoint> geoIntersections = g.findGeoIntersections(ray);
            if (geoIntersections != null) {
                if (intersections != null) {
                    intersections.addAll(geoIntersections);
                } else {
                    intersections = geoIntersections;
                }
            }
        }
        return intersections;
    }
}
