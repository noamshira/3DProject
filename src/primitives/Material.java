package primitives;

/**
 * class for material details
 */
public class Material {
    private double kD = 0; // Diffusion attenuation coefficient
    private double kS = 0; // Specular attenuation coefficient
    private int nShininess = 0;
    public double kT = 0.0; // Refraction coefficient (1 for transparent)
    public double kR = 0.0; // Reflection coefficient (1 for mirror)

    // ***************** Getters ********************** //
    public double getKd() {
        return kD;
    }

    public double getKs() {
        return kS;
    }

    public int getShininess() {
        return nShininess;
    }

    // ***************** Setters ********************** //

    public Material setKd(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }

    public Material setKt(double kT) {
        this.kT = kT;
        return this;
    }

    public Material setKr(double kR) {
        this.kR = kR;
        return this;
    }
}
