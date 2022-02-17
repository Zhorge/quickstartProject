package org.WebUiTests.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final static String LOGIN_INPUT_LOCATOR_BY_ID = "login";
    private final static String LOGIN_BUTTON_LOCATOR_BY_XPATH = "//span[.='Войти']";

    @FindBy(xpath = "//iframe[contains(@src, 'login')]")
    private WebElement iframe;

    @FindBy(id = LOGIN_INPUT_LOCATOR_BY_ID)
    private WebElement loginInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(xpath = LOGIN_BUTTON_LOCATOR_BY_XPATH)
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Переключитсья в фрейм логина")
    public LoginPage switchToIframe() {
        driver.switchTo().frame(iframe);
        return this;
    }

    @Step("Заполнить поле логина")
    public LoginPage fillLogin(String login) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_INPUT_LOCATOR_BY_ID)));
        loginInput.sendKeys(login);
        return this;
    }

    @Step("Заполнить поле пароля")
    public LoginPage fillPassword(String password) {
        passwordInput.sendKeys(password);
//        webDriverWait.until(d -> d.findElement(By.id(LOGIN_INPUT_LOCATOR_BY_ID)).getAttribute("value").contains("@rambler.ru"));
        return this;
    }

    @Step("Клик на кнопку логина")
    public ManePage clickLoginButton() {
//        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON_LOCATOR_BY_XPATH)));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginButton.click();

        return new ManePage(driver);
    }

}
