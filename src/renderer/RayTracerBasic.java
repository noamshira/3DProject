package renderer;

import elements.AreaLightSource;
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
    private static final int MAX_CALC_COLOR_LEVEL = 10;  // represents Max recursive repeats
    private static final double MIN_CALC_COLOR_K = 0.001;
    private static final double INITIAL_K = 1.0;

    // ***************** Constructor ********************** //

    /**
     * constructor of ray tracer basic
     *
     * @param scene the scene to use in the trace
     */
    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    // ***************** Override ******************** //

    @Override
    public Color traceRay(Ray ray) {

        //find all the intersections of the ray with the geometries and calculate the ray color by them
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? _scene.background : calcColor(closestPoint, ray);
    }

    // ***************** Operations ********************** //

    /**
     * recursive calculate the color of the given point
     *
     * @param geopoint geoPoint to calculate her color
     * @param ray      the ray from he camera to the geometry
     * @return color of the point
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(_scene.ambientLight.getIntensity());
    }

    /**
     * recursive function. calculate the color of the ray. add the emission ,and use the function calc local effect
     * to calculate the diffusive and the specular, and if the recursive counter > 1
     * calculate the shadows,the reflection and the refraction
     *
     * @param intersection the intersection point of the ray and the geometry
     * @param ray          the ray to the geometry
     * @param level        the level of the recursive
     * @param k
     * @return return the calculated color of the ray
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {
        Color color = intersection.geometry.getEmission();
        color = color.add(calcLocalEffects(intersection, ray, k));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getDir(), level, k));
    }

    /**
     * calculate the effect of the diffusive and the specular in the given ray intersection
     *
     * @param intersection the intersection of the ray and the geometry
     * @param ray          the from the camera to the geometry
     * @param k
     * @return return the color of the ray after calculating the local effect
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


    /**
     * calculate the transparency of the geometry by the given light, 1 is max transparency and 0 is no transparency
     *
     * @param light    the light source
     * @param l        the direction of the light
     * @param n        the normal to the geometry
     * @param geopoint the point of the intersection
     * @return dounle of the value of the transparency
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        if (softShadows && light instanceof AreaLightSource)
            return calcSoftShadows((AreaLightSource) light, lightDirection, n, geopoint);
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);//add delta
        double lightDistance = light.getDistance(geopoint.point);
        return Visibility(lightDistance, lightRay, geopoint);
    }

    /**
     * check if the ray from the light source are visible from the point
     * return 1 if yes and 0 if no
     *
     * @param lightDistance the distance of the point from the light source
     * @param lightRay      ray to check from the point to the light source
     * @param geopoint      the point to check
     * @return 1 is visible and 0 if no
     */
    private double Visibility(double lightDistance, Ray lightRay, GeoPoint geopoint) {
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

    /**
     * calculate the soft shadows shadows
     * shoot sample rays to the light source and check if its visible from the origin point or not
     * the number of the sample rays is  _sqrtBeamNum ^2 + 1 (the +1 is the original ray)
     * the soft shadows calculate by average the results (sum of the sample /num of sample)
     * the points on the light that the function use to the ray calculate by divide the light area to squares
     * the number of the squares is _sqrtBeamNum^2
     * than in every square choose point randomly and shoot to there ray
     *
     * @param light          area light source
     * @param lightDirection the direction of the light of the center of the light
     * @param n              normal to the geometry of the origin point
     * @param geopoint       the origin point
     * @return multiplier for soft shadows (between 0 and 1)
     */
    private double calcSoftShadows(AreaLightSource light, Vector lightDirection, Vector n, GeoPoint geopoint) {
        //calc ther visibility of the basic ray from the origin point
        Ray lightRay = new Ray(geopoint.point, lightDirection, n);
        double lightDistance = light.getDistance(geopoint.point);
        double ktr = Visibility(lightDistance, lightRay, geopoint);
        //calc the sample rays and add the result of every ray to ktr
        Point3D sample;
        double size = light.getEdges(); //the size of edge of the light
        //create random points on the light
        //calculate by divide the light area to squares and add choose random point in the square
        //the number of the square are _sqrtBeamNum*_sqrtBeamNum
        double rand[][] = new double[_sqrtBeamNum * _sqrtBeamNum][2];
        double div = 1 / (double) _sqrtBeamNum;
        for (int i = 0; i < _sqrtBeamNum; i++) {
            for (int j = 0; j < _sqrtBeamNum; j++) {
                rand[i * _sqrtBeamNum + j][0] = Util.random(i * div, (i + 1) * div);
                rand[i * _sqrtBeamNum + j][1] = Util.random(j * div, (j + 1) * div);
            }
        }
        //construct ray to every random point and calculate its visibility
        Point3D center = light.getCenter();
        Vector v = light.getV();
        Vector u = light.getU();
        for (int i = 0; i < _sqrtBeamNum * _sqrtBeamNum; i++) {
            sample = center.add(u.scale((0.5 - rand[i][0]) * size).add(v.scale((0.5 - rand[i][1]) * size)));
            lightRay = new Ray(geopoint.point, sample.subtract(geopoint.point), n);//add delta to the shadow ray
            lightDistance = geopoint.point.distance(sample);
            ktr += Visibility(lightDistance, lightRay, geopoint);
        }
        //the soft shadows calculate by average the results (the +1 is the original ray)
        return ktr / ((_sqrtBeamNum * _sqrtBeamNum) + 1);
    }


    /**
     * recursive function.
     * calculate the global effect of the rest of the scene.
     * calculate the reflection , the shadows and the refraction
     *
     * @param gp    the point of the intersection of the ray and the geometry
     * @param v     the the direction of the ray
     * @param level the level of the recursive
     * @param k
     * @return return the color of the global effect
     */
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

    /**
     * calculate the color of the constructed ray from refraction or reflection
     *
     * @param ray   the ray of from the refraction or the refraction
     * @param level the level of the recursive
     * @param kx
     * @param kkx
     * @return return the color of the global effect
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene.background : calcColor(gp, ray, level - 1, kkx)
        ).scale(kx);
    }

    /**
     * construct new ray of reflection
     *
     * @param gp the intersection of the ray and the geometry
     * @param n  the normal to the geometry
     * @param l  the direction of the light
     * @return the ray of reflection
     */
    private Ray constructReflectedRay(GeoPoint gp, Vector n, Vector l) {
        double nl = n.dotProduct(l);
        Vector r = l.subtract(n.scale(nl * 2));
        return new Ray(gp.point, r, n);
    }

    /**
     * construct new ray of refraction
     *
     * @param gp the intersection of the ray and the geometry
     * @param n  the normal to the geometry
     * @param l  the direction of the light
     * @return the ray of refraction
     */
    private Ray constructRefractedRay(GeoPoint gp, Vector n, Vector l) {
        return new Ray(gp.point, l, n);

    }

    /**
     * find the closer point of intersection of the given ray and any of the geometries in the scene
     *
     * @param ray the ray to find it intersection
     * @return the closer point of intersection
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> l = _scene.geometries.findGeoIntersections(ray);
        if (l == null || l.size() == 0) return null;
        return ray.findClosestGeoPoint(l);

    }
}
