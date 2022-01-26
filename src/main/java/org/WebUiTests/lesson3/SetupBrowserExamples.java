package org.WebUiTests.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SetupBrowserExamples {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions(); // настройки с которыми запускается наш браузер
        chromeOptions.addArguments("--disable-notifications"); // откл. уведомлений
        chromeOptions.addArguments("--headless"); // без интерфейса
        chromeOptions.addArguments("user-agent=Googlebot/2.1 (+http://www.google.com/bot.html)"); //версия страницы, которая будет добавляться поисковому роботу

        WebDriver driver = new ChromeDriver(chromeOptions);
        driver.get("https://google.com");
        Thread.sleep(5000);
        driver.quit();
    }
}
