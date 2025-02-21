package task1;

public class MathUtilsAtan {
    public static double atan(double x) {
        if (Double.isNaN(x))
            return Double.NaN;
        if (Double.isInfinite(x))
            return Math.signum(x) * Math.PI / 2;
        if (Math.abs(x) == 1)
            return Math.signum(x) * Math.PI / 4;

        // Используем свойство арктангенса для |x| > 1
        if (Math.abs(x) > 1)
            return x > 0 ? Math.PI / 2 - atan(1 / x) : -Math.PI / 2 - atan(1 / x);

        // Разложение в ряд Тейлора для |x| < 1
        double result = x;
        double term = x;
        int n = 1;

        while (Math.abs(term) > 1e-10) {
            term *= -x * x * (2 * n - 1) / (2 * n + 1);
            result += term;
            n++;
        }

        return result;
    }
}
