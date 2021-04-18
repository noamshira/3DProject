package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geometries implements Intersectable {
    List<Intersectable> lst;

    public Geometries(List<Intersectable> lst) {
        this.lst = new ArrayList<Intersectable>();
    }

    public Geometries(Intersectable... geometries) {
        lst = Arrays.asList(geometries.clone());
    }

    public void add(Intersectable... geometries) {
        for (int i = 0; i < geometries.length; i++) {
            lst.add(geometries[i]);
        }
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }

}
