package org.WebUiTests.homeWork8;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class NewBooksPage extends ManePage{
    private static final String URL = "https://www.litres.ru/novie/";

    @Step("Проверить, что URL соответствует" + URL)
    public NewBooksPage checkCurrentUlr() {
        webdriver().shouldHave(url("https://www.litres.ru/novie/"));
        return this;
    }
}
