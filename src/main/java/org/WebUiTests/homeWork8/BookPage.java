package org.WebUiTests.homeWork8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class BookPage extends ManePage {
    private SelenideElement addFavoriteButton = $x("//button[@data-action='addfavorite']");

    @Step("Нажать на кнопку 'Отложить'")
    public BookPage addBookToFavorites() {
        addFavoriteButton.click();
        return this;
    }
}
