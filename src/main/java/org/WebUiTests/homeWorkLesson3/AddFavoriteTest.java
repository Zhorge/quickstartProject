package org.WebUiTests.homeWorkLesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AddToFavoriteTest {
    public static void main(String[] args) throws InterruptedException {

        String bookForSearch = "Думай и богатей";

        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(chromeOptions);
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        //Авторизация
        driver.get("https://www.litres.ru/");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/pages/login/']")).click();
        driver.findElement(By.xpath("//div[.='Электронная почта']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("yagad95961@nahetech.com");
        driver.findElement(By.xpath("//div[.='Продолжить']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pwd']")));
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys("409:177");
        driver.findElement(By.xpath("//button[@type='submit']//div[.='Войти']")).click();

        //Удаление pop-up
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='AuthorizationPopup-module__wrapper']")));
        driver.findElement(By.xpath("//a[@class='AuthorizationPopup-module__close-icon']")).click();

        //Поиск и переход на страницу книги
        driver.findElement(By.xpath("//input[@class='Search-module__input']")).sendKeys(bookForSearch);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@class='SellableItem-module__wrapper']//div[@class='SellableItem-module__details']")));
        List<WebElement> resultsOfSearchList = driver.findElements(By.xpath("//li[@class='SellableItem-module__wrapper']//div[@class='SellableItem-module__details']"));
        resultsOfSearchList.stream()
                .filter(f -> f.getText().contains(bookForSearch))
                .findFirst()
                .get()
                .click();
        
        //Добавить в избранное
        driver.findElement(By.xpath("//button[@data-action='addfavorite']")).click();

        driver.findElement(By.xpath("//a[@href='/pages/my_books_all/']")).click();
        driver.findElement(By.id("my-books-list__selectedbooks")).click();
        driver.findElement(By.xpath("//div[contains(@data-obj, '" + bookForSearch + "')]/following-sibling::*//a[@class='dropdown-opener dropdown-dots']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/..//button[@data-action='dropfavorite']")));
        driver.findElement(By.xpath("//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/..//button[@data-action='dropfavorite']")).click();
        Thread.sleep(50000);
        driver.quit();
    }

}