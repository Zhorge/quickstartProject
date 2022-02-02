package org.WebUiTests.lesson4;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class FunctionsTest {
    private static Logger logger = LoggerFactory.getLogger(FunctionsTest.class);

    @BeforeAll
    static void beforeAll() {
        System.out.println("Выполнимся 1 раз перед всеми тестами, например загрузка данных в БД");

        //TRACE, DEBUG, INFO, ERROR
        logger.info("log data");
        logger.trace("trace log data");
        logger.error("err log data");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("Выполняется перед каждым тестом");
    }

    @Test
    @DisplayName("Проверка метода isPrime с простым числом")
    void givenPrimeNumberWhenRunIsPrimeMethodThenTrue() {
        boolean resultTrue = Functions.isPrime(7);
        assertTrue(resultTrue);
    }

    @ParameterizedTest
    @ValueSource(strings = {"1", "11", "1235321"})
    void isPolindromTest(String word) {
        assertEquals(true, Functions.isPalindrome(word));
    }

    @ParameterizedTest
    @CsvSource({"12121, true", "112, false"})
    void isPolindromAndNotPalidromTest(String word, Boolean result) {
        assertEquals(result, Functions.isPalindrome(word));
    }

    @ParameterizedTest
    @MethodSource("catDataProvider")
    void dataProviderTest(Cat testCat) {
        System.out.println(testCat);
    }

    private static List<Cat> catDataProvider() {
        return Arrays.asList(new Cat("Baksik", 10), new Cat("Plusha", 5));
    }

    @ParameterizedTest
    @MethodSource("catDataProviderWithBoolean")
    void dataProviderTest2(Cat testCat, Boolean result) {
        assertEquals(result, testCat.getAge() == 10);
        System.out.println(testCat);
    }

    private static Stream<Arguments> catDataProviderWithBoolean() {
        return Stream.of(
                Arguments.of(new Cat("Moris", 10), true),
                Arguments.of(new Cat("Barsik", 11), false)
        );
    }

    @Test
    void assumptionTest() {
        assumeTrue(1 == 2);
        assertTrue(false);
    }

    @Test
    void assertAllTest() {
        assertAll(
                () -> assertTrue(false),
                () -> assertFalse(true)
        );
    }

    @AfterEach
    void afterEach() {
        System.out.println("Закрытие браузера");
    }

    @AfterAll
    static void tearDown() {
        System.out.println("Метод 1 раз выполнится после всех тестов");
    }
}
