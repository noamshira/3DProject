package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * class represent triangle In three-dimensional space
 */
public class Triangle extends Polygon {


    /**
     * constructor for triangle
     *
     * @param vertices list of vertices according to their order by edge path
     */
    public Triangle(Point3D... vertices) {
        super(vertices);
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        /*
         there is intersection only if the ray intersects with the plane of the triangle
         if the point is on the plane check if the intersection point with its plane is inside the triangle
         𝑣1 = 𝑃1 − 𝑃0
         𝑣2 = 𝑃2 − 𝑃0
         𝑣3 = 𝑃3 − 𝑃0
         𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣1 × 𝑣2)
         𝑁2 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣2 × 𝑣3)
         𝑁3 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣3 × 𝑣1)
         The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
         if one or more are 0.0 – no intersection
        */
        Point3D p1 = vertices.get(0);
        Point3D p2 = vertices.get(1);
        Point3D p3 = vertices.get(2);
        Point3D p0 = ray.getP0();
        Vector v = ray.getDir();
        //only if the ray intersects with the plane of the triangle
        Plane p = new Plane(p1, p2, p3);
        List<Point3D> l = p.findIntersections(ray);
        if (l == null) return null;
        // 𝑣1 = 𝑃1 − 𝑃0
        // 𝑣2 = 𝑃2 − 𝑃0
        // 𝑣3 = 𝑃3 − 𝑃0
        Vector v1 = p1.subtract(p0);
        Vector v2 = p2.subtract(p0);
        Vector v3 = p3.subtract(p0);
        // 𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣1 × 𝑣2)
        // 𝑁2 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣2 × 𝑣3)
        // 𝑁3 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣3 × 𝑣1)
        Vector n1 = v1.crossProduct(v2).normalize();
        Vector n2 = v2.crossProduct(v3).normalize();
        Vector n3 = v3.crossProduct(v1).normalize();
        // 𝑁1 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣1 × 𝑣2)
        // 𝑁2 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣2 × 𝑣3)
        // 𝑁3 = 𝑛𝑜𝑟𝑚𝑎𝑙𝑖𝑧𝑒 (𝑣3 × 𝑣1)
        double n1v = Util.alignZero(n1.dotProduct(v));
        double n2v = Util.alignZero(n2.dotProduct(v));
        double n3v = Util.alignZero(n3.dotProduct(v));
        //if one or more are 0.0 – no intersection
        if (n1v == 0 || n2v == 0 || n3v == 0) return null;
        //The point is inside if all 𝒗 ∙ 𝑵𝒊 have the same sign (+/-)
        if (Util.checkSign(n1v, n2v) && Util.checkSign(n1v, n3v)) return l;
        else return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        List<Point3D> l = findIntersections(ray);
        if (l == null) return null;
        return List.of(new GeoPoint(this, l.get(0)));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
