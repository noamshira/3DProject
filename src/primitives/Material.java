package primitives;

/**
 * class for material details
 */
public class Material {
    private double kD = 0;
    private double kS = 0;
    private int nShininess = 0;
    public double kT = 0.0;
    public double kR = 0.0;

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
