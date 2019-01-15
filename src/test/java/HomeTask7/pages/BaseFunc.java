package HomeTask7.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseFunc {
    public WebDriver driver;
    private WebDriverWait wait;

    public BaseFunc() {
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

    public void closeDriver() {
        driver.quit();
    }

    public String pageTitleGet() {
        return driver.getTitle();
    }

    public WebElement getElement (By locator) {
        Assertions.assertFalse(getElements(locator).isEmpty(), "No element <<<<" + locator + ">>>> detected!");
        return driver.findElement(locator);
    }

    public List<WebElement> getElements (By locator) {
        Assertions.assertFalse(driver.findElements(locator).isEmpty(), "Element list <<<<" + locator + ">>>> is empty!");
        return driver.findElements(locator);
    }

    public String textGet (By xPath) {
        return getElement(xPath).getText();
    }

    public void isElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

}
