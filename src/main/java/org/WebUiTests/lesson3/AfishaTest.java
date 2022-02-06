package org.WebUiTests.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AfishaTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebElement loginButton;
        WebElement iframeLogin;

        WebDriver driver = new ChromeDriver();
        driver.get("https://afisha.ru");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        loginButton = driver.findElement(By.xpath("//button[.='Войти']"));
        loginButton.click();

        iframeLogin = driver.findElement(By.xpath("//iframe[contains(@src, 'login')]")); // наши iframe логина
        driver.switchTo().frame(iframeLogin); // переключились в iframe
        //driver.switchTo().parentFrame() - если нужно вернуться назад

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
        driver.findElement(By.id("login")).sendKeys("spartalex1993");
        driver.findElement(By.id("password")).sendKeys("Test4test");
        webDriverWait.until(d -> d.findElement(By.id("login")).getAttribute("value").contains("@rambler.ru")); // передаем в Lamda выражение условие, котороые мы хотим дождатся
        driver.findElement(By.xpath("//span[.='Войти']")).click();

        Thread.sleep(1000);

        List<WebElement> filmsList = driver.findElements(By.xpath("//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']")); // находим все фильмы
        filmsList.stream().filter(f -> f.getText().contains("Мы — монстры-2")).findFirst().get().click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")));
        driver.findElement(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']")).click();
        Thread.sleep(5000);
        driver.quit();
    }
}
