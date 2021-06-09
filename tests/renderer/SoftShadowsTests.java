package renderer;


import org.junit.jupiter.api.Test;

import elements.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 * Testing soft shadows
 */
public class SoftShadowsTests {

    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVpSize(200, 200).setVpDistance(1000);

    /**
     * test of shadow of triangle on sphere
     * with and without soft shadows
     */
    @Test
    public void softShadowsTriangleOverSphereTest() {
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
                        .setEdgesSize(10) //
                        .setKl(1E-5).setKq(1.5E-7).setEdgesSize(10).setUV(camera.getVRight(), camera.getVUp()));
        Render render = new Render(). //
                setImageWriter(new ImageWriter("softShadows triangle over sphere with", 400, 400)) //
                .setCamera(camera) //

                .setRayTracer(new RayTracerBasic(scene)
                        .setSoftShadows(true)
                );
        render.renderImage();
        render.writeToImage();
        //without soft shadows
        render.setSoftShadows(false)
                .setImageWriter(new ImageWriter("softShadows triangle over sphere without", 400, 400));
        render.renderImage();
        render.writeToImage();

    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a Sphere
     * producing a shading
     * with and without soft shadows
     */
    @Test
    public void softShadowsTrianglesSphereTest() {
        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKs(0.8).setShininess(60)), //
                new Sphere(new Point3D(0, 0, -115), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)) //
        );
        scene.lights.add( //
                new SpotLight(new Color(700, 400, 400), new Point3D(40, 40, 115), new Vector(-1, -1, -4)) //
                        .setKl(4E-4).setKq(2E-5)
                        .setEdgesSize(30).setUV(camera.getVRight(), camera.getVUp()));

        Render render = new Render() //
                .setImageWriter(new ImageWriter("softShadow Sphere on Triangles with", 600, 600)) //
                .setCamera(camera) //
                .setRayTracer(new RayTracerBasic(scene)
                        .setSoftShadows(true));
        render.renderImage();
        render.writeToImage();
        //without soft shadows
        render.setSoftShadows(false)
                .setImageWriter(new ImageWriter("softShadow Sphere on Triangles without", 600, 600));
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce a picture of a two triangles lighted by a spot light with a partially
     * transparent Sphere producing partial shadow
     * with and without soft shadows
     */
    @Test
    public void softShadowsTrianglesTransparentSphereTest() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVpSize(200, 200).setVpDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point3D(60, 50, -50), 30) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0)));

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0, -1)) //
                .setKl(4E-5).setKq(2E-7)
                .setEdgesSize(30).setUV(camera.getVRight(), camera.getVUp()));

        ImageWriter imageWriter = new ImageWriter("softShadows TrianglesTransparentSphere with", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer((new RayTracerBasic(scene)).setSqrtBeamNum(9))
                .setSoftShadows(true);

        render.renderImage();
        render.writeToImage();

        //without soft shadows
        imageWriter = new ImageWriter("softShadows TrianglesTransparentSphere without", 600, 600);
        render.setImageWriter(imageWriter) //
                .setSoftShadows(false);
        render.renderImage();
        render.writeToImage();
    }

    /**
     * Produce with 8 geometries and 3 lights sources
     * with and without soft shadows
     */
    @Test
    public void softShadowsMultiElementsTest() {
        Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVpSize(250, 250).setVpDistance(1000);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.geometries.add( //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(150, -150, -135), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Triangle(new Point3D(-150, -150, -115), new Point3D(-70, 70, -140), new Point3D(75, 75, -150)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
                new Sphere(new Point3D(-60, -50, -50), 30) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.3).setKs(0.3).setShininess(30).setKt(0.5)),
                new Sphere(new Point3D(10, -20, -100), 25) //
                        .setEmission(new Color(java.awt.Color.GREEN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.6)), //
                new Triangle(new Point3D(-70, -40, 0), new Point3D(-40, -70, 0), new Point3D(-68, -68, -4)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30).setKt(0.3)),//

                new Triangle(new Point3D(-30, 0, 0), new Point3D(0, -30, 0), new Point3D(-38, -38, -4)) //
                        .setEmission(new Color(java.awt.Color.CYAN)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30)),//


                new Sphere(new Point3D(-95, -90, -100), 20) //
                        .setEmission(new Color(java.awt.Color.PINK)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Triangle(new Point3D(150, -150, -150), new Point3D(-150, 150, -150),
                        new Point3D(80, 80, 300)) //
                        .setEmission(new Color(20, 20, 20)) //
                        .setMaterial(new Material().setKr(1))

        );

        scene.lights.add(new SpotLight(new Color(700, 400, 400), new Point3D(60, 50, 0), new Vector(0, 0.2, -1)) //
                .setKl(4E-5).setKq(2E-7)
                .setEdgesSize(50).setUV(camera.getVRight(), camera.getVUp()));
        scene.lights.add( //
                new PointLight(new Color(1000, 600, 0), new Point3D(-100, -100, 300)) //
                        .setKl(0.0004).setKq(0.00006)
                        .setEdgesSize(30).setUV(camera.getVRight(), camera.getVUp()));
        scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-75, -75, -15), new Vector(-1, -1, -4)) //
                .setKl(0.00001).setKq(0.00005)
                .setEdgesSize(40).setUV(camera.getVRight(), camera.getVUp()));


        ImageWriter imageWriter = new ImageWriter("softShadows 8 elements with", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer((new RayTracerBasic(scene)).setSqrtBeamNum(9))
                .setSoftShadows(true);

        render.renderImage();
        render.writeToImage();

        //without soft shadows
        imageWriter = new ImageWriter("softShadows 8 elements without", 600, 600);
        render.setImageWriter(imageWriter) //
                .setSoftShadows(false);
        render.renderImage();
        render.writeToImage();
    }

}
