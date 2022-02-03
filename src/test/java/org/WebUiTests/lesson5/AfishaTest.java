package org.WebUiTests.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AfishaTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;
    private final static String AFISHA_URL = "https://afisha.ru";

    @BeforeAll
    static void registerDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void initDriver() {
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        driver.get(AFISHA_URL);
        driver.manage().window().maximize();
    }

    @Test
    void windowsTest() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(driver.getWindowHandles()); // список всех вкладок
        driver.switchTo().window(tabs.get(0));
        Thread.sleep(3000);
        driver.switchTo().window(tabs.get(1));
        driver.get("https://ya.ru");
        Thread.sleep(3000);
        driver.switchTo().window(tabs.get(0));
        ((JavascriptExecutor)driver).executeScript("alert(\"FDSFSDFDSFVCX\")");
        Thread.sleep(3000);
        driver.switchTo().alert().accept();
        Thread.sleep(2000);
    }

    @Test
    void hoverMenuTest() throws InterruptedException {
        ((JavascriptExecutor) driver).executeScript("var badTableEval = document.evaluate (\n" +
                String.format("    \"%s\",\n", "//a[.='ВЫСТАВКИ']") +
                "    document.documentElement,\n" +
                "    null,\n" +
                "    XPathResult.FIRST_ORDERED_NODE_TYPE,\n" +
                "    null\n" +
                ");\n" +
                "\n" +
                "if (badTableEval  &&  badTableEval.singleNodeValue) {\n" +
                "    var badTable  = badTableEval.singleNodeValue;\n" +
                "    badTable.parentNode.removeChild (badTable);\n" +
                "}\n");

        Thread.sleep(5000);

        actions.moveToElement(driver.findElement(By.xpath("//a[.='КИНО']")))
                .build()
                .perform();
        driver.findElement(By.xpath("//div[@data-test='SUGGEST']//a[.='IMAX кинотеатры']")).click();
        Assertions.assertTrue(driver.getCurrentUrl().contains("imax"));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
