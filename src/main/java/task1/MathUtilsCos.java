package task1;

public class MathUtilsCos {
    public static double cos(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x))
            return Double.NaN;

        x = x % (2 * Math.PI);

        double result = 1.0;
        double term = 1.0;
        int n = 1;

        while (Math.abs(term) > 1e-10) {
            term *= -x * x / ((2 * n) * (2 * n - 1));
            result += term;
            n++;
        }

        return result;
    }
}
