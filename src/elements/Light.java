package elements;

import primitives.Color;

/**
 * abstract class for light in scene
 */
abstract class Light {
    private Color intensity;

    // ***************** Constructor ********************** //

    /**
     * constructor of light
     *
     * @param intensity the intensity of the light
     */
    protected Light(Color intensity) {
        this.intensity = intensity;
    }

    // ***************** Getter ********************** //

    /**
     * return the intensity of the light
     *
     * @return color - the intensity of the light
     */
    public Color getIntensity() {
        return intensity;
    }
}
