package task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;
import static java.lang.Math.*;

import java.util.stream.Stream;

class MathUtilsAtanTest {
    private static final double DELTA = 1e-8; // Допустимая погрешность

    // Тесты для граничных значений
    @ParameterizedTest(name = "Граничное значение: atan({0}) должно быть {1}")
    @MethodSource("provideBoundaryValues")
    void testAtanBoundaryValues(double x, double expected) {
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideBoundaryValues() {
        return Stream.of(
                Arguments.of(0, 0.0), // atan(0) = 0
                Arguments.of(1, PI / 4), // atan(1) = π/4
                Arguments.of(-1, -PI / 4) // atan(-1) = -π/4
        );
    }

    // Тесты для положительных значений |x| <= 1
    @ParameterizedTest(name = "Положительное значение: atan({0}) должно быть близко к Math.atan")
    @MethodSource("providePositiveValuesInRange")
    void testAtanPositiveValuesInRange(double x) {
        double expected = Math.atan(x);
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> providePositiveValuesInRange() {
        return Stream.of(
                Arguments.of(0.5),
                Arguments.of(0.7),
                Arguments.of(0.99)
        );
    }

    // Тесты для отрицательных значений |x| <= 1
    @ParameterizedTest(name = "Отрицательное значение: atan({0}) должно быть близко к Math.atan")
    @MethodSource("provideNegativeValuesInRange")
    void testAtanNegativeValuesInRange(double x) {
        double expected = Math.atan(x);
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideNegativeValuesInRange() {
        return Stream.of(
                Arguments.of(-0.5),
                Arguments.of(-0.7),
                Arguments.of(-0.99)
        );
    }

    // Тесты для значений |x| > 1
    @ParameterizedTest(name = "Значение |x| > 1: atan({0}) должно быть близко к Math.atan")
    @MethodSource("provideValuesOutsideRange")
    void testAtanValuesOutsideRange(double x) {
        double expected = Math.atan(x);
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideValuesOutsideRange() {
        return Stream.of(
                Arguments.of(2.0),
                Arguments.of(10.0),
                Arguments.of(-2.0),
                Arguments.of(-10.0)
        );
    }

    // Тесты для очень маленьких значений
    @ParameterizedTest(name = "Маленькое значение: atan({0}) должно быть близко к Math.atan")
    @MethodSource("provideSmallValues")
    void testAtanSmallValues(double x) {
        double expected = Math.atan(x);
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideSmallValues() {
        return Stream.of(
                Arguments.of(1e-10),
                Arguments.of(-1e-10)
        );
    }

    @ParameterizedTest(name = "Большое значения: atan({0}) должно быть близко к Math.atan")
    @MethodSource("provideLargeValues")
    void testAtanLargeValues(double x) {
        double expected = Math.atan(x);
        double actual = MathUtilsAtan.atan(x);
        assertEquals(expected, actual, DELTA);
    }

    private static Stream<Arguments> provideLargeValues() {
        return Stream.of(
                Arguments.of(100),
                Arguments.of(999),
                Arguments.of(-100),
                Arguments.of(-999)
        );
    }

    @Test
    void testAtanNaN() {
        double x = Double.NaN;
        double result = MathUtilsAtan.atan(x);
        assertTrue(Double.isNaN(result), "atan(NaN) должно быть NaN");
    }

    @Test
    void testAtanPositiveInfinity() {
        double x = Double.POSITIVE_INFINITY;
        double result = MathUtilsAtan.atan(x);
        assertEquals(PI / 2, result, DELTA, "atan(+Infinity) должно быть π/2");
    }

    @Test
    void testAtanNegativeInfinity() {
        double x = Double.NEGATIVE_INFINITY;
        double result = MathUtilsAtan.atan(x);
        assertEquals(-PI / 2, result, DELTA, "atan(-Infinity) должно быть -π/2");
    }
}