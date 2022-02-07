package org.WebUiTests.homeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        actions = new Actions(driver);
        PageFactory.initElements(driver, this); // для поиска актуального состояния элемента в тестах
    }

    @FindBy(xpath = "//a[@href='/pages/login/']")
    private WebElement loginButton;

    public LoginPage clickLoginButton() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginButton.click();
        return new LoginPage(driver);
    }

    public BasePage checkProfileName(String[] loginName) {
        assertThat(driver.findElement(By.xpath("//div[@class='Profile-module__name']")), hasText(loginName[0]));
        return this;
    }

    private static final String RESULTS_OF_SEARCH_LOCATOR_BY_XPATH
            = "//li[@class='SellableItem-module__wrapper']//div[@class='SellableItem-module__details']";
    @FindBy(xpath = "//input[@class='Search-module__input']")
    private WebElement searchInput;

    //поиск и переход на страницу книги
    public BookPage searchForBook(String book) {
        searchInput.sendKeys(book);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(RESULTS_OF_SEARCH_LOCATOR_BY_XPATH)));
        List<WebElement> resultsOfSearchList = driver.findElements(By.xpath(RESULTS_OF_SEARCH_LOCATOR_BY_XPATH));
        resultsOfSearchList.stream()
                .filter(f -> f.getText().contains(book))
                .findFirst()
                .get()
                .click();
        return new BookPage(driver);
    }

    @FindBy(xpath = "//a[@href='/pages/my_books_all/']")
    private WebElement myBooks;

    public MyBooksPage goToMyBooksPage() {
        myBooks.click();
        return new MyBooksPage(driver);
    }

    @FindBy(xpath = "//a[.='Новинки']")
    private WebElement newBooks;

    public NewBooksPage goToNewBooksPage() {
        newBooks.click();
        return new NewBooksPage(driver);
    }
}
