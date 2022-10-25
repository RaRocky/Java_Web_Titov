package org.example.lesson4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class TriangleTest {

    @ParameterizedTest
    @CsvSource({"3, 3, 3", "3, 2, 3", "30, 40, 50"})
    @DisplayName("Вычисление площади треугольника. Позитивный тест.")
    public void positiveTest(int sideA, int sideB, int sideC) throws SideEqualZeroException,
            LengthIsNegativeException, CanNotBeTriangleException {

        // Ожидаемый результат.
        double p = (double) (sideA + sideB + sideC) / 2;
        double expected = Math.sqrt(p * (p - sideA) * (p - sideB) * (p - sideC));

        // Фактический результат.
        Triangle triangle = new Triangle(sideA, sideB, sideC);

        Assertions.assertEquals(expected, triangle.countSquare());

        System.out.println("Ожидаемый результат --> " + expected);
        System.out.println("Фактический результат --> " + triangle.countSquare());
        System.out.println("===================================================");
    }


    @ParameterizedTest
    @CsvSource({"0, 3, 3", "3, 0, 3", "3, 3, 0"})
    @DisplayName("Вычисление площади треугольника. Длина хотя бы одной из сторон равна 0.")
    public void sideEqualZeroTest(int sideA, int sideB, int sideC) {

        Triangle triangle = new Triangle(sideA, sideB, sideC);

        Throwable exception = Assertions.assertThrows(SideEqualZeroException.class, () ->
                triangle.countSquare());

        System.out.println(exception.getMessage());
        System.out.println("====================================================");
    }


    @ParameterizedTest
    @CsvSource({"-3, 3, 3", "3, -2, 3", "30, 40, -50"})
    @DisplayName("Вычисление площади треугольника. Длина хотя бы одной из сторон имеет отрицательное значение.")
    public void lengthIsNegativeTest(int sideA, int sideB, int sideC) {

        Triangle triangle = new Triangle(sideA, sideB, sideC);

        Throwable exception = Assertions.assertThrows(LengthIsNegativeException.class, () ->
                triangle.countSquare());

        System.out.println(exception.getMessage());
        System.out.println("====================================================");
    }


    @ParameterizedTest
    @CsvSource({"8, 3, 3", "2, 5, 3", "30, 40, 100"})
    @DisplayName("Вычисление площади треугольника. Длина одной из сторон слишком велика, чтобы получился треугольник.")
    public void canNotBeTriangleTest(int sideA, int sideB, int sideC) {

        Triangle triangle = new Triangle(sideA, sideB, sideC);

        Throwable exception = Assertions.assertThrows(CanNotBeTriangleException.class, () ->
                triangle.countSquare());

        System.out.println(exception.getMessage());
        System.out.println("====================================================");
    }
}
