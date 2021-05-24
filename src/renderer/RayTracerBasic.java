package renderer;

import elements.LightSource;
import primitives.Color;
import primitives.Material;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

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
        return calcColor(ray.findClosestGeoPoint(l), ray);

    }

    /**
     * calculate the color of the given point
     *
     * @param p   geoPoint to calculate her color
     * @param ray the ray from he camera to the geometry
     * @return color of the point
     */
    private Color calcColor(GeoPoint p, Ray ray) {
        return _scene.ambientLight.getIntensity()
                .add(p.geometry.getEmission())
                // add calculated light contribution from all light sources)
                .add(calcLocalEffects(p, ray));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getnShininess();
        double kd = material.getkD(), ks = material.getkS();
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, nl, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(l.dotProduct(n) * 2));
        return lightIntensity.scale(ks * Math.pow(v.scale(-1).dotProduct(r), nShininess));
    }

    private Color calcDiffusive(double kd, double ln, Color lightIntensity) {
        return lightIntensity.scale(kd * Math.abs(ln));
    }

}
