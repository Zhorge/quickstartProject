package org.WebUiTests.homeWork6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BookPage extends BasePage{
    public BookPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//button[@data-action='addfavorite']")
    private WebElement addFavoriteButton;

    @Step("Нажать на кнопку 'Отложить'")
    public BookPage addBookToFavorites() {
        addFavoriteButton.click();
        return this;
    }
}
