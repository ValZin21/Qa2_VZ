package Lection7.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class BaseFunc {
    WebDriver browser;

    public BaseFunc() {
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver-v0.23.0-win64/geckodriver.exe");
        browser = new FirefoxDriver();
        browser.manage().window().maximize();
    }

    public void goToPage(String url) {
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "http://" + url;
        }
        browser.get(url);
    }

    public List<WebElement> getElements(By locator) {
        return browser.findElements(locator);
    }

    public WebElement getElement(By locator) {
        Assertions.assertFalse(getElements(locator).isEmpty(), "No elements");
        return browser.findElement(locator);
    }
}
