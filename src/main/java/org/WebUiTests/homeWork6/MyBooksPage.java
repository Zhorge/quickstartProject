package org.WebUiTests.homeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.hasText;

public class MyBooksPage extends BasePage {
    private static Integer count;

    public MyBooksPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "my-books-list__selectedbooks")
    private WebElement favoriteBooksTab;

    public MyBooksPage openFavoriteBooks() {
        favoriteBooksTab.click();
        return this;
    }

    private static final String NUMBER_OF_FAV_BOOKS_LOCATOR_BY_XPATH
            = "//li[@id='my-books-list__selectedbooks']//span[@class='my-books-link__counter']";
    @FindBy(xpath = NUMBER_OF_FAV_BOOKS_LOCATOR_BY_XPATH)
    private WebElement numberOfFavBooks;

    public MyBooksPage countNumberOfFavBooks() {
        MyBooksPage.count = Integer.parseInt(numberOfFavBooks.getText());
        return this;
    }

    private static final String DROP_FAVORITE_LOCATOR_BY_XPATH
            = "//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/..//button[@data-action='dropfavorite']";
    @FindBy(xpath = DROP_FAVORITE_LOCATOR_BY_XPATH)
    private WebElement dropFavorite;

    public MyBooksPage deleteBookFromFavorite(String book) {
        driver.findElement(By.xpath("//div[contains(@data-obj, '" + book
                + "')]/following-sibling::*//a[@class='dropdown-opener dropdown-dots']")).click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DROP_FAVORITE_LOCATOR_BY_XPATH)));
        dropFavorite.click();
        return this;
    }

    public MyBooksPage checkNumberOfFavBooksAfterDelete() {
        MyBooksPage.count--;
        assertThat(numberOfFavBooks, hasText(count.toString()));
        return this;
    }
}
