package util;

public class Activation {

    public static double sigmoid(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    public static double tanh(double val) {
        return Math.tanh(val);
    }

    public static double relu(double val) {
        return Math.max(0, val);
    }

    public static double sigmoidDerivative(double val) {
        return sigmoid(val) * (1 - sigmoid(val));
    }

    public static double tanhDerivative(double val) {
        return 1 - tanh(val) * tanh(val);
    }

    public static double reluDerivative(double val) {
        return val > 0 ? 1: 0;
    }

}
