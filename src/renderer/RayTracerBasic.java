package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

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
        List<Point3D> l = _scene.geometries.findIntersections(ray);
        if (l == null || l.size() == 0)
            return _scene.background;
        return calcColor(ray.findClosestPoint(l));

    }

    /**
     * calculate the color of the given point
     *
     * @param p point to calculate her color
     * @return color of the point
     */
    private Color calcColor(Point3D p) {
        return _scene.ambientLight.getIntensity();
    }
}
