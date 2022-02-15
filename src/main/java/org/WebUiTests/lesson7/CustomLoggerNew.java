package org.WebUiTests.lesson7;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.ByteArrayInputStream;

public class CustomLoggerNew implements WebDriverListener {
    public void beforeClick(WebElement element) {
        Allure.step("Собираемся кликнуть на " + element.getText());
    }

    public void beforeQuit(WebDriver driver) {
        Allure.addAttachment("Скриншот страницы",
                new ByteArrayInputStream(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES)));
    }
}
