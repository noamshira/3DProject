package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

/**
 * Class for basic ray tracer that find the color from the rays
 */
public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    // ***************** Operations ******************** //

    @Override
    public Color traceRay(Ray ray) {

        //find all the intersections of the ray with the geometries and calculate the ray color by them
        List<GeoPoint> l = _scene.geometries.findGeoIntersections(ray);
        if (l == null || l.size() == 0)
            return _scene.background;
        return calcColor(ray.findClosestGeoPoint(l));

    }

    /**
     * calculate the color of the given point
     *
     * @param p geoPoint to calculate her color
     * @return color of the point
     */
    private Color calcColor(GeoPoint p) {
        return p.geometry.getEmission().add(_scene.ambientLight.getIntensity());
    }
}
