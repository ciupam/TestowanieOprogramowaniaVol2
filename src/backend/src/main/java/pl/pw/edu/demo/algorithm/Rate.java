package pl.pw.edu.demo.algorithm;

public class Rate {

    private static final int CONST = 0;
    private static final int PERCENT = 1;

    Vertex vertexOut;
    double rateValue;
    int rateProvisionType;
    double rateProvision;

    Rate(Vertex vartexOut, double rateValue, String rateProvisionType, double rateProvision) {
        this.vertexOut = vartexOut;
        this.rateValue = rateValue;
        if (rateProvisionType.equals("PROC")) {
            this.rateProvisionType = PERCENT;
        } else {
            this.rateProvisionType = CONST;
        }
        this.rateProvision = rateProvision;
    }

    double calculateRateValue(double baseValue) {
        return baseValue * rateValue - getProvision(baseValue * rateValue);
    }

    double getProvision(double baseValue) {
        if (rateProvisionType == PERCENT) {
            return baseValue * rateProvision;
        } else {
            return rateProvision;
        }
    }
}
