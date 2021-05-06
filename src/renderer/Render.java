package renderer;

import elements.Camera;
import primitives.Color;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter;
    Camera _camera;
    Scene _scene;
    RayTracerBase _rayTracer;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setScene(Scene scene) {
        _scene = scene;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    public Void renderImage() {
        if (_camera == null)
            throw new MissingResourceException("camera cant be null", "Render", "_camera");
        if (_rayTracer == null)
            throw new MissingResourceException("rayTracer cant be null", "Render", "_rayTracer");
        if (_scene == null)
            throw new MissingResourceException("scene cant be null", "Render", "_scene");
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter cant be null", "Render", "_imageWriter");

        throw new UnsupportedOperationException();
    }

    public Void printGrid(int interval, Color color) {
        return null;
    }

    public Void writeToImage() {
        return null;
    }
}
