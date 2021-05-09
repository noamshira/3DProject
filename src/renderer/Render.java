package renderer;

import elements.Camera;
import primitives.Color;
import primitives.Ray;
import scene.Scene;

import java.util.MissingResourceException;

public class Render {
    ImageWriter _imageWriter;
    Camera _camera;
    RayTracerBase _rayTracer;

    public Render setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    public Render setCamera(Camera camera) {
        _camera = camera;
        return this;
    }

    public Render setRayTracer(RayTracerBase rayTracer) {
        _rayTracer = rayTracer;
        return this;
    }

    public void renderImage() {
        if (_camera == null)
            throw new MissingResourceException("camera cant be null", "Render", "_camera");
        if (_rayTracer == null)
            throw new MissingResourceException("rayTracer cant be null", "Render", "_rayTracer");
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter cant be null", "Render", "_imageWriter");
        Ray ray;
        Color c;
        for (int i = 0; i < _imageWriter.getNx(); i++)
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                ray = _camera.constructRayThroughPixel(_imageWriter.getNx(), _imageWriter.getNy(), j, i);
                c = _rayTracer.traceRay(ray);
                _imageWriter.writePixel(i, j, c);
            }
    }

    public void printGrid(int interval, Color color) {
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter cant be null", "Render", "_imageWriter");
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                if (i % interval == 0 || j % interval == 0) {
                    _imageWriter.writePixel(i, j, color);
                }
            }
        }
    }

    public void writeToImage() {

        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter cant be null", "Render", "_imageWriter");
        _imageWriter.writeToImage();
    }
}
