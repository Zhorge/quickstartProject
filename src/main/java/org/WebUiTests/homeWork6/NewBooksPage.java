package org.WebUiTests.homeWork6;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class NewBooksPage extends BasePage {
    private static final String URL = "https://www.litres.ru/novie/";

    public NewBooksPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить, что URL соответствует" + NewBooksPage.URL)
    public NewBooksPage checkCurrentUlr() {
        assertTrue(driver.getCurrentUrl().equals(URL));
        return this;
    }
}
