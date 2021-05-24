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
     * @param ia
     * @param ka
     */
    public AmbientLight(Color ia, double ka) {
        //intensity = ia * ka
        super(ia.scale(ka));
    }

    public AmbientLight() {
        super(Color.BLACK);
    }
}
