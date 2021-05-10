package elements;

import primitives.Color;

/**
 * Class for the ambient light of the scene
 */
public class AmbientLight {
    Color intensity;

    // ***************** Constructor ********************** //

    /**
     * constructor of ambient light
     *
     * @param ia
     * @param ka
     */
    public AmbientLight(Color ia, double ka) {
        //intensity = ia * ka
        intensity = ia.scale(ka);
    }

    // ***************** Getter ********************** //

    public Color getIntensity() {
        return intensity;
    }
}
