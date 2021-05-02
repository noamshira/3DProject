package elements;

import primitives.Color;

public class AmbientLight {
    Color intensity;

    public AmbientLight(Color ia, double ka) {
        intensity = ia.scale(ka);
    }

    public Color getIntensity() {
        return intensity;
    }
}
