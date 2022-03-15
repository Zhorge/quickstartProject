package org.WebUiTests.homeWork8;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class LitresPageObjectTest {
    private final static String LITRES_URL = "https://www.litres.ru/";
    private String email = "yagad95961@nahetech.com";
    private String password = "409:177";
    private String[] login = email.split("@");

    //TODO Разобраться, как добавить скриншоты для Аллюр
    @BeforeAll
    static void setupAllureReports() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void initDriver() {
        open(LITRES_URL);
    }

    @DisplayName("Авторизация")
    @Description("Проверка, что после авторизации имя профиля имеет значение равное логину")
    @Test
    void AuthorizeTest() {
        new ManePage()
                .clickLoginButton()
                .clickEmailButton()
                .fillEmail(email)
                .clickContinueButton()
                .fillPassword(password)
                .clickSubmitButton()
                .checkProfileName(login);
    }

    @DisplayName("Переход на страницу 'Новинки'")
    @Description("Проверка перехода на страницу 'Новинки книг'")
    @Test
    void GoToNewBooks() {
        new ManePage()
                .goToNewBooksPage()
                .checkCurrentUlr();
    }

    @DisplayName("Поиск книги, добавить/удалить в 'Отложенные'")
    @Feature("Статус книги")
    @Story("Отложенные")
    @Description("Пользовательский сценарий по поиску определенной книги, перехода на её страницу и добавлению книги" +
            " в 'Отложенные' с последующим удалением ее из этого статуса на странице 'Мои книги' --> 'Отложенные' ")
    @ParameterizedTest
    @ValueSource(strings = {"Думай и богатей"})
    void AddFavoriteTest(String book) {
        new ManePage()
                .clickLoginButton()
                .clickEmailButton()
                .fillEmail(email)
                .clickContinueButton()
                .fillPassword(password)
                .clickSubmitButton()
                .searchForBook(book)
                .addBookToFavorites()
                .goToMyBooksPage()
                .openFavoriteBooks()
                .countNumberOfFavBooks()
                .deleteBookFromFavorite(book)
                .checkNumberOfFavBooksAfterDelete();
    }

    @AfterEach
    void tearDown() {
        LogEntries logEntries = getWebDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry log : logEntries) {
            Allure.addAttachment("Лог браузера", log.getMessage());
        }

        closeWebDriver();
    }
}
