package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * Class for basic ray tracer that find the color from the rays
 */
public class RayTracerBasic extends RayTracerBase {
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    // ***************** Operations ******************** //

    @Override
    public Color traceRay(Ray ray) {

        //find all the intersections of the ray with the geometries and calculate the ray color by them
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
    }


    /**
     * calculate the color of the given point
     *
     * @param geopoint geoPoint to calculate her color
     * @param ray      the ray from he camera to the geometry
     * @return color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * calculate the effect of the local light source and reflections
     *
     * @param intersection the point of the intersection of the ray and the geometry
     * @param ray          the ray that intersect with the geometry
     * @return the color of the local effects
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray, double k) {
        //check if the ray is visible
        Vector v = ray.getDir();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : _scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            //check if the ray is in the the opposite direction
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    ;
                    color = color.add(calcDiffusive(kd, nl, lightIntensity),
                            calcSpecular(ks, l, n, v, nl, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }


    /**
     * Calculate the specular of the geometry with the light
     * Process:
     * 1. multiple the dot product of l in normal By 2
     * 2. scale the normal By line 1
     * 3. subtract the normal from l
     * 4. power the dot product of v in l by the geometry shininess
     * 5. double 4 By the ks
     * 6. divide the values of lightIntensity by line 5 and return the color.
     *
     * @param ks             doubel of the geometry matirial
     * @param v              Vector from intersection point To the Camera
     * @param n              Vector normal of the geometry at the intersection point
     * @param l              Vector from the light source to the intersection point
     * @param nl             double the result of dot product of n & l
     * @param nShininess     double of the geometry shininess
     * @param lightIntensity Color of the light in the intersection point
     * @return Color of specular Light in the intersection point
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, double nl, int nShininess, Color lightIntensity) {
        Vector r = l.subtract(n.scale(nl * 2));
        double vr = v.scale(-1).dotProduct(r);
        vr = Math.max(0, vr);
        vr = Math.pow(vr, nShininess);
        Color color = lightIntensity.scale(ks * vr);
        return color;
    }

    /**
     * Calculate the diffusive light with the geometry
     * Process:
     * 2. multiple the dot product of the light vector and the normal by the kd of the geometry material
     * 3. multiple the values of the light by line 2
     * 4. return the Color.
     *
     * @param kd             double represents the geometry material kd Value
     * @param ln             the dot product of the light vector and the normal
     * @param lightIntensity Color represents the color of the light.
     * @return Color represents the diffusive light for this point
     */
    private Color calcDiffusive(double kd, double ln, Color lightIntensity) {
        Color color = lightIntensity.scale(kd * Math.abs(ln));
        return color;
    }


    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        List<GeoPoint> intersections = _scene.geometries.findGeoIntersections(lightRay);
        if (intersections == null) return 1.0;
        double ktr = 1.0;
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0) {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;
    }


    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp, n, v), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp, n, v), level, material.kT, kkt));
        return color;
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    private Ray constructReflectedRay(GeoPoint gp, Vector n, Vector l) {
        double nl = n.dotProduct(l);
        Vector r = l.subtract(n.scale(nl * 2));
        return new Ray(gp.point, r, n);
    }

    private Ray constructRefractedRay(GeoPoint gp, Vector n, Vector l) {
        return new Ray(gp.point, l, n);
        //TODO:maybe a wrong calculate
    }

    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> l = _scene.geometries.findGeoIntersections(ray);
        if (l == null || l.size() == 0) return null;
        return ray.findClosestGeoPoint(l);

    }
}
