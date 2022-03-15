package org.WebUiTests.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.WebUiTests.lesson7.CustomLoggerNew;
import org.WebUiTests.lesson7.CustomLoggerThirdSelenium;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Iterator;

@Story("Работа с фильмами")
public class AfishaPageObjectTest {
    WebDriver driver;
    //EventFiringWebDriver eventFiringWebDriver;   Selenium 3

    private final static String AFISHA_URL = "https://afisha.ru";
    private final static String LOGIN = "spartalex1993";
    private final static String PASSWORD = "Test4test";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        //driver = new ChromeDriver();
        //eventFiringWebDriver = new EventFiringWebDriver(new ChromeDriver());
        //eventFiringWebDriver.register(new CustomLoggerThirdSelenium());     Selenium 3
        driver = new EventFiringDecorator(new CustomLoggerNew()).decorate(new ChromeDriver());
        driver.get(AFISHA_URL);
        driver.manage().window().maximize();
    }

    @Test
    @Feature("Избранное")
    @Description("Проверка добавления в избранное фильма по имени")
    void likeRandomFilmTest() {
        new ManePage(driver)
                .clickLoginButton()
                .switchToIframe()
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .clickLoginButton()
                .clcikFilmByName("Анчартед: На картах не значится")
                .likeFilm()
                .checkFilmAddedToFavorites();
    }

    @AfterEach
    void killDriver() {


        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER); // запись логов из консоли разработчика
//        Iterator<LogEntry> iterator = logEntries.iterator();
//
//        while (iterator.hasNext()) { //пока в итераторе, что то есть
//            Allure.addAttachment("Лог браузера:", iterator.next().getMessage());  //будем в Allure добавлять информацию
//        }

        for (LogEntry log: logEntries) {
            Allure.addAttachment("Лог браузера:", log.getMessage());
        }

        driver.quit();
    }

}
