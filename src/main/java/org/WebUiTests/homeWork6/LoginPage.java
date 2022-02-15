package org.WebUiTests.homeWork6;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[.='Электронная почта']")
    private WebElement emailButton;

    public LoginPage clickEmailButton() {
        emailButton.click();
        return this;
    }

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailInput;

    public LoginPage fillEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    @FindBy(xpath = "//div[.='Продолжить']")
    private WebElement continueButton;

    public LoginPage clickContinueButton() {
        continueButton.click();
        return this;
    }

    private static final String PASSWORD_INPUT_LOCATOR_BY_XPATH = "//input[@name='pwd']";
    @FindBy(xpath = PASSWORD_INPUT_LOCATOR_BY_XPATH)
    private WebElement passwordInput;

    public LoginPage fillPassword(String password) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(PASSWORD_INPUT_LOCATOR_BY_XPATH)));
        passwordInput.sendKeys(password);
        return this;
    }

    @FindBy(xpath = "//button[@type='submit']//div[.='Войти']")
    private WebElement submitButton;

    public BasePage clickSubmitButton() {
        submitButton.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='Profile-module__name']")));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='AuthorizationPopup-module__wrapper']")));
        driver.findElement(By.xpath("//a[@class='AuthorizationPopup-module__close-icon']")).click();
        return new BasePage(driver);
    }
}
