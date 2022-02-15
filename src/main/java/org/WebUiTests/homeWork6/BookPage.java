package org.WebUiTests.homeWork6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage extends BasePage{
    public BookPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-action='addfavorite']")
    private WebElement addFavoriteButton;

    public BookPage addBookToFavorites() {
        addFavoriteButton.click();
        return this;
    }
}
