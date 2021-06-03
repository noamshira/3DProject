package renderer;


import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

public class SoftShadowsTests {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVpSize(200, 200).setVpDistance(1000);

    @Test
    public void softShadowsTest() {
        scene.geometries.add( //
                new Sphere(new Point3D(0, 0, -200), 60) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(400, 240, 0), new Point3D(-100, -100, 200), new Vector(1, 1, -3))//
                        .setRadius(10) //
                        .setKl(1E-5).setKq(1.5E-7));

        Render render = new Render(). //
                setImageWriter(new ImageWriter("softShadows", 400, 400)) //
                .setCamera(camera) //
                .setSoftShadows(true)
                .setRayTracer(new RayTracerBasic(scene));
        render.renderImage();
        render.writeToImage();
    }

}
