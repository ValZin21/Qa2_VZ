package TVNetTestInPageObjects.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunctions {

    public WebDriver driver;
    WebDriverWait wait;

    public BaseFunctions() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        driver.manage().window().maximize();
    }

    public void goToPage(String url) {
        if (!url.contains("http://") && !url.contains("https://")) {
            url = "http://" + url;
        }
        driver.get(url);
    }

    public List<WebElement> getElements (By xPath) {
        return driver.findElements(xPath);
    }

    public WebElement getElement (By xPath) {
        Assertions.assertFalse(getElements(xPath).isEmpty(), "No element <<<<" + xPath + ">>>> detected!");
        return driver.findElement(xPath);
    }

    public Integer getCommentsNumber (String text) {
        return Integer.valueOf(text);
    }

    public String getText (By xPath) {
        return getElement(xPath).getText();
    }

    public void isElementPresent(By xPath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xPath));
    }

    public void isElementClickable(By xPath) {
        wait.until(ExpectedConditions.elementToBeClickable(xPath));
    }

    public void isElementInvisible(By xPath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xPath));
    }

}
