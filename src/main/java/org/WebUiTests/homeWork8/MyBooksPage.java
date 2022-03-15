package org.WebUiTests.homeWork8;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MyBooksPage extends ManePage{
    private static Integer count;

    private SelenideElement favoriteBooksTab = $("#my-books-list__selectedbooks");

    private SelenideElement numberOfFavBooks =
            $x("//li[@id='my-books-list__selectedbooks']//span[@class='my-books-link__counter']");

    private SelenideElement dropFavorite =
            $x("//a[@class='dropdown-opener dropdown-dots dropdown-opener_open']/.." +
                    "//button[@data-action='dropfavorite']");

    @Step("Открыть вкладку 'Отложенные'")
    public MyBooksPage openFavoriteBooks() {
        favoriteBooksTab.click();
        return this;
    }

    @Step("Посчитать кол-во книг во кладке 'Отложенные'")
    public MyBooksPage countNumberOfFavBooks() {
        count = Integer.parseInt(numberOfFavBooks.getText());
        return this;
    }

    @Step("Удалить книгу из 'Отложенные'")
    public MyBooksPage deleteBookFromFavorite(String book) {
        $(By.xpath("//div[contains(@data-obj, '" + book
                + "')]/following-sibling::*//a[@class='dropdown-opener dropdown-dots']")).click();
        dropFavorite.click();
        return this;
    }

    @Step("Убедиться, что после удаления книги из 'Отложенные' общее кол-во книг во кладке уменьшилось на 1")
    public MyBooksPage checkNumberOfFavBooksAfterDelete() {
        MyBooksPage.count--;
        numberOfFavBooks.shouldHave(text(count.toString()));
        return this;
    }
}
