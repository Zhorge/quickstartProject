package org.WebUiTests.homeWorkLesson4;

import org.WebUiTests.lesson4.FunctionsTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.WebUiTests.homeWorkLesson4.TriangleArea.calcArea;
import static org.junit.jupiter.api.Assertions.*;


public class TriangleTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    @BeforeAll
    static void beforeAll() {
        //TRACE, DEBUG, INFO, ERROR
        logger.info("Запуск тестов...");
        logger.trace("trace log data");
        logger.error("err log data");
    }

    @Test
    @DisplayName("Позитивный кейс - Вычисление площади")
    void positiveTest() {
        assertAll(
                () -> assertEquals(0.4330127018922193, calcArea(1, 1, 1)),
                () -> assertEquals(2608.0928736339124, calcArea(70, 80, 85)),
                () -> assertEquals(160.9035036908768, calcArea(30, 30, 59)),
                () -> assertEquals(160.9035036908768, calcArea(30, 59, 30)),
                () -> assertEquals(160.9035036908768, calcArea(59, 30, 30))
        );
        logger.info("\"Позитивный кейс - Вычисление площади\"");
    }

    @Test
    @DisplayName("Негативный кейс- Нулевые числа")
    void negativeZeroTest() {
        assertAll(
                () -> assertThrows(Exception.class, () -> calcArea(0, 5, 7)),
                () -> assertThrows(Exception.class, () -> calcArea(60, 0, 8)),
                () -> assertThrows(Exception.class, () -> calcArea(70, 40, 0)),
                () -> assertThrows(Exception.class, () -> calcArea(0, 0, 0))
        );

        logger.info("\"Негативный кейс- Нулевые числа\"");
    }

    @Test
    @DisplayName("Негативный кейс- Открицательные числа")
    void negativeNumbersTest() {
        assertAll(
                () -> assertThrows(Exception.class, () -> calcArea(-1, 8, 10)),
                () -> assertThrows(Exception.class, () -> calcArea(7, -1, 2)),
                () -> assertThrows(Exception.class, () -> calcArea(99, 100, -1)),
                () -> assertThrows(Exception.class, () -> calcArea(-500, -700, -999))
        );

        logger.info("\"Негативный кейс- Отрицательные числа\"");
    }

    @Test
    @DisplayName("Негативный кейс - 1 сторона больше суммы двух других")
    void oneSideLongerThemSummOfOtherTest() {
        assertAll(
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(1, 1, 2)),
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(15, 77, 1)),
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(300, 10, 10)),
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(333, 4, 999)),
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(1, 2, 1)),
                () -> assertThrows(OneSideLongerThenSummOfOthersException.class, () -> calcArea(2, 1, 1))
        );

        logger.info("\"Негативный кейс- Проверка, что 1 сторона равна или больше суммы двух других\"");
    }
}
