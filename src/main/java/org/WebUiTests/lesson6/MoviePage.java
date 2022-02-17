package org.WebUiTests.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.hamcrest.MatcherAssert.assertThat;
import static ru.yandex.qatools.htmlelements.matchers.WebElementMatchers.isDisplayed;

public class MoviePage extends BasePage {

    private final static String BUTTON_FAVORITE_LOCATOR_BY_XPATH
            = "//section[@data-test='PAGE-SECTION TITLE-SECTION']//button[@data-test='BUTTON FAVORITE']";

    private final static String ADDED_TO_FAV_XPATH_LOCATOR = "//div[.='Добавлено в избранное']";

    @FindBy(xpath = BUTTON_FAVORITE_LOCATOR_BY_XPATH)
    private WebElement buttonFavorite;

    @FindBy(xpath = ADDED_TO_FAV_XPATH_LOCATOR)
    private WebElement addedToFavoritesElement;

    public MoviePage(WebDriver driver) {
        super(driver);
    }

    @Step("Добавить фильм в избранное")
    public MoviePage likeFilm() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BUTTON_FAVORITE_LOCATOR_BY_XPATH)));
        buttonFavorite.click();
        return this;
    }

    @Step("Проверить, что фильм добавился в избранное")
    public MoviePage checkFilmAddedToFavorites() {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ADDED_TO_FAV_XPATH_LOCATOR)));
        assertThat(addedToFavoritesElement, isDisplayed());
        return this;
    }

}
