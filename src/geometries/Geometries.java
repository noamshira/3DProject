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

    /**
     * default constructor for geometries
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

    //getter
    public List<Intersectable> getLst() {
        return lst;
    }

    /**
     * add geometry or geometries to the list
     *
     * @param geometries
     */
    public void add(Intersectable... geometries) {
        lst.addAll(new ArrayList<Intersectable>(List.of(geometries)));
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        //find the intersection of every geometry in lst and return the list of the points
        List<Point3D> pLst = null;
        List<Point3D> result;
        for (int i = 0; i < lst.size(); i++) {
            result = lst.get(i).findIntersections(ray);
            if (result != null) {
                //If these are the first points found
                if (pLst == null) pLst = result;
                else pLst.addAll(result);
            }
        }
        return pLst;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<GeoPoint> intersections = null;
        for (Intersectable g : lst) {
            var geoIntersections = g.findGeoIntersections(ray);
            if (geoIntersections != null) intersections.addAll(geoIntersections);
        }
        return intersections;
    }
}
