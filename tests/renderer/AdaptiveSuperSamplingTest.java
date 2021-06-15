package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import scene.Scene;

public class AdaptiveSuperSamplingTest {
    private Scene scene = new Scene("Test scene");
    private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
            .setVpSize(200, 200).setVpDistance(1000);

    /**
     * test of shadow of triangle on sphere
     * with and without soft shadows
     */
    @Test
    public void TriangleOverSphereAdaptiveTest() {
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
                setImageWriter(new ImageWriter("AdaptiveSuperSampling triangle over sphere", 400, 400)) //
                .setCamera(camera) //
                .setMultithreading(3)
                .setRayTracer(new RayTracerBasic(scene)
                        .setSoftShadows(true)
                        .setAdaptiveSuperSampling(true)
                        .setSqrtBeamNum(8)
                );
        render.renderImage();
        render.writeToImage();

    }

    @Test
    public void TriangleOverSphereNoAdaptiveTest() {
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
                setImageWriter(new ImageWriter("AdaptiveSuperSampling triangle over sphere without", 400, 400)) //
                .setCamera(camera) //
                .setMultithreading(3)
                .setRayTracer(new RayTracerBasic(scene)
                        .setSoftShadows(true)
                        .setSqrtBeamNum(8)
                );
        render.renderImage();
        render.writeToImage();
    }

    @Test
    public void TrianglesTransparentSphereAdaptiveTest() {
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

        ImageWriter imageWriter = new ImageWriter("adaptive TrianglesTransparentSphere with", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer((new RayTracerBasic(scene)).setSqrtBeamNum(8))
                .setMultithreading(3)
                .setSoftShadows(true)
                .setAdaptiveSuperSampling(true);
        render.renderImage();
        render.writeToImage();


    }

    @Test
    public void TrianglesTransparentSphereNoAdaptiveTest() {
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

        ImageWriter imageWriter = new ImageWriter("adaptive TrianglesTransparentSphere without", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracer((new RayTracerBasic(scene)).setSqrtBeamNum(8))
                .setMultithreading(3)
                .setSoftShadows(true);
        render.renderImage();
        render.writeToImage();
    }

}
