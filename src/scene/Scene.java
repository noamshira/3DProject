package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * class for scene, its name background color light and geometries
 */
public class Scene {
    public String name;
    public Color background;
    public AmbientLight ambientLight;
    public Geometries geometries;
    public List<LightSource> lights;

    // ***************** Constructors ********************** //

    /**
     * constructor of scene, make empty scene
     *
     * @param name the name of the scene
     */
    public Scene(String name) {
        this.name = name;
        background = new Color(Color.BLACK);
        ambientLight = new AmbientLight(new Color(Color.BLACK), 0);
        geometries = new Geometries();
        lights = new LinkedList<LightSource>();
    }

    /*************** Setters *****************/

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }
}

