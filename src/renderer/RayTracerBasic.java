package renderer;

import geometries.Geometry;
import geometries.Intersectable;
import primitives.Color;
import primitives.Point3D;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    @Override
    public Color traceRay(Ray ray) {

        List<Point3D> l = _scene.geometries.findIntersections(ray);
        if (l == null || l.size() == 0)
            return _scene.background;
        return calcColor(ray.findClosestPoint(l));

    }

    private Color calcColor(Point3D p) {
        return _scene.ambientLight.getIntensity();
    }
}
