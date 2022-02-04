package org.WebUiTests.homeWork5_Litres;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class LitresTest {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    private final static String LITRES_URL = "https://www.litres.ru/";
    private String email = "yagad95961@nahetech.com";
    private String password = "409:177";
    private String[] login = email.split("@");
    private String bookForSearch = "Думай и богатей";

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

    @Test
    void AuthorizeTest() {
        driver.findElement(By.xpath("//a[@href='/pages/login/']")).click();
        driver.findElement(By.xpath("//div[.='Электронная почта']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//div[.='Продолжить']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pwd']")));
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']//div[.='Войти']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Profile-module__name']")));
        assertThat(driver.findElement(By.xpath("//div[@class='Profile-module__name']")), hasText(login[0]));
    }

    @Test
    void GoToNewBooks() throws InterruptedException {
        driver.findElement(By.xpath("//a[.='Новинки']")).click();
        assertTrue(driver.getCurrentUrl().equals("https://www.litres.ru/novie/"));
    }

    @Test
    void AddFavoriteTest() throws InterruptedException {

        //Авторизация
        Thread.sleep(1000);
        driver.findElement(By.xpath("//a[@href='/pages/login/']")).click();
        driver.findElement(By.xpath("//div[.='Электронная почта']")).click();
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
        driver.findElement(By.xpath("//div[.='Продолжить']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='pwd']")));
        driver.findElement(By.xpath("//input[@name='pwd']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']//div[.='Войти']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Profile-module__name']")));

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

        //Добавить в "ОТЛОЖЕНО"
        driver.findElement(By.xpath("//button[@data-action='addfavorite']")).click();

        //Переход в "Мои книги" -> "Отложенные"
        driver.findElement(By.xpath("//a[@href='/pages/my_books_all/']")).click();
        driver.findElement(By.id("my-books-list__selectedbooks")).click();

        //Кол-во книг в "Отложенных"
        WebElement favoriteTab = driver.findElement(By.xpath("//li[@id='my-books-list__selectedbooks']//span[@class='my-books-link__counter']"));
        Integer count = Integer.parseInt(favoriteTab.getText());
        count --;

        //Удалить из "Отложенных"
        driver.findElement(By.xpath("//div[contains(@data-obj, '" + bookForSearch + "')]/following-sibling::*//a[@class='dropdown-opener dropdown-dots']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/..//button[@data-action='dropfavorite']")));
        driver.findElement(By.xpath("//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/..//button[@data-action='dropfavorite']")).click();

        //Кол-во книг изменилось на -1 после удаления
        assertThat(driver.findElement(By.xpath("//li[@id='my-books-list__selectedbooks']//span[@class='my-books-link__counter']")), hasText(count.toString()));
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
