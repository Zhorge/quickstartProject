package org.WebUiTests.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MoviePage {
    private final static String ADDED_TO_FAV_XPATH_LOCATOR = "//div[.='Добавлено в избранное']";

    private SelenideElement buttonFavorite
            = $(By.xpath("//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']"));

    private SelenideElement addedToFavoritesElement = $(By.xpath("//div[.='Добавлено в избранное']"));

    @Step("Добавить фильм в избранное")
    public MoviePage likeFilm() {
        buttonFavorite.click();
        return this;
    }

    @Step("Проверить, что фильм добавился в избранное")
    public MoviePage checkFilmAddedToFavorites() {
        addedToFavoritesElement.shouldBe(Condition.visible);
        return this;
    }
}
