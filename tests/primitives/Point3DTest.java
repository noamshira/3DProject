package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * testing Point3D
 */
class Point3DTest {

    /**
     * test method for
     * {@link Point3D#distanceSquared(Point3D)}
     */
    @Test
    void distanceSquared() {
        //testing calculate squared distance
        Point3D p1 = new Point3D(2, 0, 0);
        assertEquals(4, p1.distanceSquared(Point3D.ZERO), "wrong squared distance");
    }

    /**
     * test method for
     * {@link Point3D#distance(Point3D)}
     */
    @Test
    void distance() {
        //testing calculate distance
        Point3D p1 = new Point3D(2, 0, 0);
        assertEquals(2, p1.distance(Point3D.ZERO), "wrong distance");

    }

    /**
     * test method for
     * {@link Point3D#add(Vector)}
     */
    @Test
    void add() {
        //testing of add vector to point
        Point3D p1 = new Point3D(1, 1, 1);
        Vector p2 = new Vector(3, 4, 5);

        assertEquals(new Point3D(4, 5, 6), p1.add(p2), "wrong result of add vector to point");
    }

    /**
     * test method for
     * {@link Point3D#subtract(Point3D)}
     */
    @Test
    void subtract() {
        //testing of subtract points
        Point3D p1 = new Point3D(3, 4, 5);
        Point3D p2 = new Point3D(1, 1, 1);

        assertEquals(new Vector(2, 3, 4), p1.subtract(p2), "wrong result of subtract points");
    }
}