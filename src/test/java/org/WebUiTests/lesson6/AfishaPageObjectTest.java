package org.WebUiTests.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AfishaPageObjectTest {
    WebDriver driver;

    private final static String AFISHA_URL = "https://afisha.ru";
    private final static String LOGIN = "spartalex1993";
    private final static String PASSWORD = "Test4test";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        driver.get(AFISHA_URL);
        driver.manage().window().maximize();
    }

    @Test
    void likeRandomFilmTest() {

        new ManePage(driver)
                .clickLoginButton()
                .switchToIframe()
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .clickLoginButton()
                .clcikFilmByName("Падение Луны")
                .likeFilm()
                .checkFilmAddedToFavorites();
    }

    @AfterEach
    void killDriver() {
        driver.quit();
    }

}
