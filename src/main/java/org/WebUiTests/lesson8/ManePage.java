package org.WebUiTests.lesson8;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class ManePage {
    private SelenideElement loginButton = $(By.xpath("//button[.='Войти']"));

    private ElementsCollection filmList = $$(By.xpath("//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']"));

    @Step("Клик на кнопку логина главной страницы")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Клик на фильм по имени")
    public MoviePage clcikFilmByName(String filmName) {
        //filmsList.stream().filter(f -> f.getText().contains(filmName)).findFirst().get().click();
        filmList.findBy(Condition.text(filmName)).click();
        return page(MoviePage.class);
    }
}
