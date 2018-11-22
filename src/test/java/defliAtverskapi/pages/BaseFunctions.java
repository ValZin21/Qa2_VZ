package defliAtverskapi.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BaseFunctions {

    public WebDriver driver;
    WebDriverWait wait;
    public List<WebElement> elementList;
    public List<String> productCheckList = new ArrayList<String>();
    public String checkShoes = "Kurpes";
    public String checkColor = "Melna";
    public String checkState = "Jauns";

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
        Assertions.assertFalse(driver.findElements(xPath).isEmpty(), "Element list <<<<" + xPath + ">>>> is empty!");
        return driver.findElements(xPath);
    }

    public WebElement getElement (By xPath) {
        Assertions.assertFalse(getElements(xPath).isEmpty(), "No element <<<<" + xPath + ">>>> detected!");
        return driver.findElement(xPath);
    }

    public WebElement getElementFromList(List<WebElement> list, int number) {
        return list.get(number);
    }

    public String getTextFromList(List<WebElement> list, int number) {
        return getElementFromList(list, number).getText();
    }

    public void isElementPresent(By xPath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xPath));
    }

    public String textGet (By xPath) {
        Assertions.assertFalse(getElements(xPath).isEmpty());
        return driver.findElement(xPath).getText();
    }

    public String attributeGet (By xPath, String attribute) {
        Assertions.assertFalse(getElements(xPath).isEmpty());
        return driver.findElement(xPath).getAttribute(attribute);
    }

    public String titleGet() {
        return driver.getTitle();
    }
}
