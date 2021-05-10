package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * interface to ray tracers
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    /**
     * constructor for ray tracer
     *
     * @param scene the scene of the rays
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    /**
     * trace the color of ray in the scene
     *
     * @param ray ray to trace
     * @return the color of the ray in scene
     */
    public abstract Color traceRay(Ray ray);
}