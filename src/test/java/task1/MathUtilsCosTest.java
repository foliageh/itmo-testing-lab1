package task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.*;

class MathUtilsCosTest {
    private static final double DELTA = 1e-8; // Допустимая погрешность

    // Тесты для граничных значений
    @ParameterizedTest(name = "Граничное значение: cos({0}) должно быть {1}")
    @MethodSource("provideBoundaryValues")
    void testCosBoundaryValues(double x, double expected) {
        double actual = MathUtilsCos.cos(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideBoundaryValues() {
        return Stream.of(
                Arguments.of(0, 1.0), // cos(0) = 1
                Arguments.of(PI / 2, 0.0), // cos(π/2) = 0
                Arguments.of(PI, -1.0), // cos(π) = -1
                Arguments.of(3 * PI / 2, 0.0), // cos(3π/2) = 0
                Arguments.of(2 * PI, 1.0) // cos(2π) = 1
        );
    }

    // Тесты для положительных значений внутри периода
    @ParameterizedTest(name = "Положительное значение: cos({0}) должно быть близко к Math.cos")
    @MethodSource("providePositiveValuesInPeriod")
    void testCosPositiveValuesInPeriod(double x) {
        double expected = Math.cos(x);
        double actual = MathUtilsCos.cos(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> providePositiveValuesInPeriod() {
        return Stream.of(
                Arguments.of(PI / 4), // cos(π/4) = √2/2
                Arguments.of(PI / 3), // cos(π/3) = 0.5
                Arguments.of(PI / 6), // cos(π/6) = √3/2
                Arguments.of(3 * PI / 4), // cos(3π/4) = -√2/2
                Arguments.of(5 * PI / 4), // cos(5π/4) = -√2/2
                Arguments.of(7 * PI / 4) // cos(7π/4) = √2/2
        );
    }

    // Тесты для отрицательных значений (cos(-x) = cos(x))
    @ParameterizedTest(name = "Отрицательное значение: cos({0}) должно быть равно cos({1})")
    @MethodSource("provideNegativeValues")
    void testCosNegativeValues(double x, double expectedX) {
        double expected = Math.cos(expectedX);
        double actual = MathUtilsCos.cos(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideNegativeValues() {
        return Stream.of(
                Arguments.of(-PI / 4, PI / 4), // cos(-π/4) = cos(π/4)
                Arguments.of(-PI / 3, PI / 3), // cos(-π/3) = cos(π/3)
                Arguments.of(-PI / 6, PI / 6) // cos(-π/6) = cos(π/6)
        );
    }

    // Тесты для больших значений (проверка периодичности)
    @ParameterizedTest(name = "Большое значение: cos({0}) должно быть равно cos({1})")
    @MethodSource("provideLargeValues")
    void testCosLargeValues(double x, double equivalentX) {
        double expected = Math.cos(equivalentX);
        double actual = MathUtilsCos.cos(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideLargeValues() {
        return Stream.of(
                Arguments.of(1000.0, 1000.0 % (2 * PI)), // cos(1000) = cos(1000 mod 2π)
                Arguments.of(-999.0, -999.0 % (2 * PI)) // cos(-999) = cos(-999 mod 2π)
        );
    }

    // Тесты для очень маленьких значений
    @ParameterizedTest(name = "Маленькое значение: cos({0}) должно быть близко к Math.cos")
    @MethodSource("provideSmallValues")
    void testCosSmallValues(double x) {
        double expected = Math.cos(x);
        double actual = MathUtilsCos.cos(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideSmallValues() {
        return Stream.of(
                Arguments.of(1e-10), // Очень маленькое положительное значение
                Arguments.of(-1e-10) // Очень маленькое отрицательное значение
        );
    }

    @Test
    void testCosNaN() {
        double x = Double.NaN;
        double result = MathUtilsCos.cos(x);
        assertTrue(Double.isNaN(result), "cos(NaN) должно быть NaN");
    }

    @Test
    void testCosPositiveInfinity() {
        double x = Double.POSITIVE_INFINITY;
        double result = MathUtilsCos.cos(x);
        assertTrue(Double.isNaN(result), "cos(+Infinity) должно быть NaN");
    }

    @Test
    void testCosNegativeInfinity() {
        double x = Double.NEGATIVE_INFINITY;
        double result = MathUtilsCos.cos(x);
        assertTrue(Double.isNaN(result), "cos(-Infinity) должно быть NaN");
    }
}