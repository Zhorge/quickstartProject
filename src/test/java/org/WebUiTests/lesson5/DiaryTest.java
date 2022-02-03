package org.WebUiTests.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DiaryTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String DIARY_URL = "https://diary.ru/";

    //TestingLogin
    //TestingPassword
    //email xaloj25153@mannawo.com

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(DIARY_URL);
        driver.manage().window().maximize();
    }

    @Test
    void diaryCookieTest() throws InterruptedException {
        Cookie cookie = new Cookie("_identity_", "b74af7e5364ac9400b83899536b817016154266e1aa3ac3fba67f" +
                "93d4f4a35e7a%3A2%3A%7Bi%3A0%3Bs%3A10%3A%22_identity_%22%3Bi%3A1%3Bs%3A52%3A%22%5B3562993%2C%22UUMb" +
                "OGhVdMQ51qzBz5UxObtJUW0xIHfd%22%2C2592000%5D%22%3B%7D");

        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        Thread.sleep(5000);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
