package org.WebUiTests;

import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.WebUiTests.lesson8.ManePage;
import org.WebUiTests.lesson8.MoviePage;


public class AfishaStepDefenitions {
    private final static String AFISHA_URL = "https://afisha.ru";
    private final static String LOGIN = "spartalex1993";
    private final static String PASSWORD = "Test4test";

    @Given("^Пользовтель авторизовался на сайте$")
    public void userAuthorized() {
        Selenide.open(AFISHA_URL);

        new ManePage()
                .clickLoginButton()
                .switchToIframe()
                .fillLogin(LOGIN)
                .fillPassword(PASSWORD)
                .clickLoginButton();
    }

    @When("Пользователь кликает на фильм {string}")
    public void пользовательКликаетНаФильмFilmName(String filmName) {
        new ManePage().clcikFilmByName(filmName);
    }

    @When("^Пользователь лайкает фильм$")
    public void userLikesFilm() {
        new MoviePage().likeFilm();
    }

    @Then("^Пользователь видит плашку с сообщением об успешном добавлении фильма в избранное$")
    public void userCanSeeSuccessMessage() {
        new MoviePage().checkFilmAddedToFavorites();
    }

    @After(value = "@close_browser")
    public void after() {
        Selenide.closeWebDriver();
    }
}
