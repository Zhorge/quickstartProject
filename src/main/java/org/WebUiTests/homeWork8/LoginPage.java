package org.WebUiTests.homeWork8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends ManePage {
    private SelenideElement emailButton = $x("//div[.='Электронная почта']");

    private SelenideElement emailInput = $x("//input[@name='email']");

    private SelenideElement continueButton = $x("//div[.='Продолжить']");

    private SelenideElement passwordInput = $x("//input[@name='pwd']");

    private SelenideElement submitButton = $x("//button[@type='submit']//div[.='Войти']");

    @Step("Клик на кнопку 'Электронная почта'")
    public LoginPage clickEmailButton() {
        emailButton.click();
        return this;
    }

    @Step("Ввести в поле 'Электронная почта' {email}")
    public LoginPage fillEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @Step("Клик на кнопку 'Продолжить'")
    public LoginPage clickContinueButton() {
        continueButton.click();
        return this;
    }

    @Step("Ввести в поле 'Пароль' {password}")
    public LoginPage fillPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }

    @Step("Клик на кнопку 'Войти'")
    public ManePage clickSubmitButton() {
        submitButton.click();
        $x("//div[@class='Profile-module__name']").shouldBe(visible);
        $x("//div[@class='AuthorizationPopup-module__wrapper']").shouldBe(visible);
        $x("//a[@class='AuthorizationPopup-module__close-icon']").click();
        return page(ManePage.class);
    }
}
