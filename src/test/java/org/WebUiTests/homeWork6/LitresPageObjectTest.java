package org.WebUiTests.homeWork6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LitresPageObjectTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String LITRES_URL = "https://www.litres.ru/";
    private String email = "yagad95961@nahetech.com";
    private String password = "409:177";
    private String[] login = email.split("@");

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(LITRES_URL);
        driver.manage().window().maximize();
    }

    @DisplayName("Авторизация")
    @Test
    void AuthorizeTest() {
        new LoginPage(driver)
                .clickLoginButton()
                .clickEmailButton()
                .fillEmail(email)
                .clickContinueButton()
                .fillPassword(password)
                .clickSubmitButton()
                .checkProfileName(login);
    }

    @DisplayName("Переход на страницу 'Новинки'")
    @Test
    void GoToNewBooks() {
        new BasePage(driver)
                .goToNewBooksPage()
                .checkCurrentUlr();
    }

    @DisplayName("Отложенные")
    @ParameterizedTest
    @ValueSource(strings = {"Думай и богатей"})
    void AddFavoriteTest(String book) {
        new LoginPage(driver)
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
        driver.quit();
    }
}
