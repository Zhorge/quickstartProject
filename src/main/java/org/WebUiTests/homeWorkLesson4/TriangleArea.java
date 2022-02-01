package org.WebUiTests.homeWorkLesson4;

public class TriangleArea {
    public static double calcArea(double sideA, double sideB, double sideC) throws Exception {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new Exception("Сторона треугольника не может быть равна или меньше 0");
        }

        if (sideA >= sideB + sideC || sideB >= sideA + sideC || sideC >= sideA + sideB) {
            throw new OneSideLongerThenSummOfOthersException("Одна из сторон треугольника больше суммы двух других");
        }

        double halfP = (sideA + sideB + sideC) / 2;
        double square = (halfP * (halfP - sideA) * (halfP - sideB) * (halfP - sideC));
        return Math.sqrt(square);
    }
}
