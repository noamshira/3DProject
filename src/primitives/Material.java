package primitives;

public class Material {
    private double kD = 0;
    private double kS = 0;
    private int nShininess = 0;

    public double getkD() {
        return kD;
    }

    public double getkS() {
        return kS;
    }

    public int getnShininess() {
        return nShininess;
    }

    public Material setkD(double kD) {
        this.kD = kD;
        return this;
    }

    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
