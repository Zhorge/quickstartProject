package org.WebUiTests.lesson8;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;


public class LoginPage {
    private SelenideElement iframe = $(By.xpath("//iframe[contains(@src, 'login')]"));

    private SelenideElement loginInput = $(By.id("login"));

    private SelenideElement passwordInput = $(By.id("password"));

    private SelenideElement loginButton = $(By.xpath("//span[.='Войти']"));

    @Step("Переключитсья в фрейм логина")
    public LoginPage switchToIframe() {
        Selenide.switchTo().frame(iframe);
        return this;
    }

    @Step("Заполнить поле логина")
    public LoginPage fillLogin(String login) {
        loginInput.sendKeys(login);
        return this;
    }

    @Step("Заполнить поле пароля")
    public LoginPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку логина")
    public ManePage clickLoginButton() {
        loginButton.click();
        return page(ManePage.class);
    }
}
