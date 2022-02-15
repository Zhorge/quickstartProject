package org.WebUiTests.homeWork6;

import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class NewBooksPage extends BasePage {
    public NewBooksPage(WebDriver driver) {
        super(driver);
    }

    private static final String URL = "https://www.litres.ru/novie/";

    public NewBooksPage checkCurrentUlr() {
        assertTrue(driver.getCurrentUrl().equals(URL));
        return this;
    }
}
