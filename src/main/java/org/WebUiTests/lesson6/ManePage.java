package org.WebUiTests.lesson6;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ManePage extends BasePage {
    private static final String LOGIN_BUTTON_LOCATOR_BY_XPATH = "//button[.='Войти']";

    public ManePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = LOGIN_BUTTON_LOCATOR_BY_XPATH)
    private WebElement loginButton;

    @FindBy(xpath = "//a[contains(@href, 'movie')]/ancestor::div[@data-test='ITEM']")
    private List<WebElement> filmsList;

    @Step("Клик на кнопку логина главной страницы")
    public LoginPage clickLoginButton() {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOGIN_BUTTON_LOCATOR_BY_XPATH)));
        loginButton.click();
        return new LoginPage(driver);
    }

    @Step("Клик на фильм по имени")
    public MoviePage clcikFilmByName(String filmName) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        filmsList.stream().filter(f -> f.getText().contains(filmName)).findFirst().get().click();
        return new MoviePage(driver);
    }
}
