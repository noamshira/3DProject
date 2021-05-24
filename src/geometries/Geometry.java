package geometries;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface to all the geometries
 */
public abstract class Geometry implements Intersectable {

    private Material material = new Material();
    protected Color emission = Color.BLACK;

    /*************** Getters *****************/

    public Color getEmission() {
        return emission;
    }

    /**
     * return the normal to the geometry
     *
     * @param p point on the geometry
     * @return normal to the geometry
     */
    public abstract Vector getNormal(Point3D p);

    public Material getMaterial() {
        return material;
    }

    /*************** Setters *****************/

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }
}
