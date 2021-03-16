package geometries;

import primitives.Point3D;

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
    public String toString() {
        return super.toString();
    }
}
