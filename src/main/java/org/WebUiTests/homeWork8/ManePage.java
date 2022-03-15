package org.WebUiTests.homeWork8;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ManePage {
    private SelenideElement loginButton = $x("//a[@href='/pages/login/']");

    private SelenideElement searchInput = $x("//input[@class='Search-module__input']");

    private SelenideElement myBooks = $x("//a[@href='/pages/my_books_all/']");

    private SelenideElement newBooks = $x("//a[.='Новинки']");

    private SelenideElement profileName = $x("//div[@class='Profile-module__name']");

    private ElementsCollection resultsOfSearch =
            $$x("//li[@class='SellableItem-module__wrapper']//div[@class='SellableItem-module__details']");

    @Step("Клик на кнопку 'Войти'")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    @Step("Проверить, что имя профиля соответствует логину {loginName}")
    public ManePage checkProfileName(String[] loginName) {
        profileName.shouldHave(text((loginName[0])));
        return this;
    }

    @Step("Найти книгу {book} в поиске и перейти на её страницу")
    public BookPage searchForBook(String book) {
        searchInput.sendKeys(book);
        resultsOfSearch.first().click();

        return page(BookPage.class);
    }

    @Step("Перейти на страницу 'Мои книги'")
    public MyBooksPage goToMyBooksPage() {
        myBooks.click();
        return page(MyBooksPage.class);
    }

    @Step("Перейти на страницу 'Новинки книг'")
    public NewBooksPage goToNewBooksPage() {
        newBooks.click();
        return page(NewBooksPage.class);
    }
}
