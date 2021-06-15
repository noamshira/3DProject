package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

/**
 * interface to ray tracers
 */
public abstract class RayTracerBase {
    protected Scene _scene;

    protected int _sqrtBeamNum = 9; //the squared root of number of the sample ray of shadows ray - for soft shadows
    protected boolean softShadows = false; // true to use soft shadows option
    protected boolean adaptiveSuperSampling = false;
    // ***************** Constructor ********************** //

    /**
     * constructor for ray tracer
     *
     * @param scene the scene of the rays
     */
    public RayTracerBase(Scene scene) {
        _scene = scene;
    }

    // ***************** Setters ********************** //

    /**
     * set the squared root number of the sample beam to use in soft shadows
     *
     * @param sqrtBeamNum squared root of the number of the sample beam
     * @return RayTracerBase itself for chaining
     */
    public RayTracerBase setSqrtBeamNum(int sqrtBeamNum) {
        _sqrtBeamNum = sqrtBeamNum;
        return this;
    }

    /**
     * set true to use soft shadow
     *
     * @param softShadows true to use soft shadow
     * @return RayTracerBase itself for chaining
     */
    public RayTracerBase setSoftShadows(boolean softShadows) {
        this.softShadows = softShadows;
        return this;
    }

    /**
     * set true to use Adaptive supersampling
     *
     * @param adaptiveSuperSampling true to use Adaptive supersampling
     * @return RayTracerBase itself for chaining
     */
    public RayTracerBase setAdaptiveSuperSampling(boolean adaptiveSuperSampling) {
        this.adaptiveSuperSampling = adaptiveSuperSampling;
        return this;
    }
    // ***************** Operation ********************** //

    /**
     * trace the color of ray in the scene
     *
     * @param ray ray to trace
     * @return the color of the ray in scene
     */
    public abstract Color traceRay(Ray ray);


}