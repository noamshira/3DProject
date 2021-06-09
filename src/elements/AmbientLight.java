package elements;

import primitives.Color;

/**
 * Class for the ambient light of the scene
 */
public class AmbientLight extends Light {


    // ***************** Constructor ********************** //

    /**
     * constructor of ambient light
     *
     * @param ia color for the caclculate of the intensity
     * @param ka multiplier for calculating the intensity
     */
    public AmbientLight(Color ia, double ka) {
        //intensity = ia * ka
        super(ia.scale(ka));
    }

    /**
     * default constructor fro ambient light with color of black
     */
    public AmbientLight() {
        super(Color.BLACK);
    }
}
