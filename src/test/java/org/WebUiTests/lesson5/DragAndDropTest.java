package org.WebUiTests.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class DragAndDropTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String URL = "https://crossbrowsertesting.github.io/drag-and-drop.html";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(URL);
        driver.manage().window().maximize();
    }

    @Test
    void dragAndDropTest() {
        actions.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droppable")))
                .build()
                .perform();

        assertThat(driver.findElement(By.id("droppable")), hasText("Dropped!"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
