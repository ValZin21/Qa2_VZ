import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Lists {
    
    private final String webIndex = "http://rus.delfi.lv";
    private final By WEB_ARTICLE_SCOUT = By.xpath("(.//h3/a[@class='top2012-title'])");
    List<WebElement> webArticles = new ArrayList<WebElement>();
    List<String> myArticles = new ArrayList<String>();
    public WebDriver driver;

    @Test
    public void listExample(){
       List<String> names = new ArrayList<String>();
       //Assertions.assertFalse(names.isEmpty(), "No elements!");
       names.add("Ksenija");
       names.add("Viktorija");
       names.add("Ieva");
       System.out.println(names.size());
       System.out.println(names.get(1));

//       for (int i = 0; i < names.size(); i++){
//           System.out.println("Love " + names.get(i));
//       }

       for (String name : names) {  // to got thru all array
           System.out.println("Love " + name);
       }
    }

    @Test
    public void delfiListCheckerManual (){
        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(webIndex);
        driver.manage().window().maximize();
        webArticles = driver.findElements(WEB_ARTICLE_SCOUT);

        myArticles.add("Из первых уст: 8 наблюдений о разнице жизни в Риге и Санкт-Петербурге");
        myArticles.add("Троицкий: Латвия и Эстония ошиблись, обращаясь с русскоязычными жителями высокомерно");
        myArticles.add("В Даугавпилсской думе фактически развалилась коалиция; \"Согласие\" возвращает влияние");
        myArticles.add("В Латвии возникла нехватка вакцин для взрослых против дифтерии");
        myArticles.add("Трамп заявил о выходе из договора с Россией о ракетах средней и малой дальности");

       // System.out.println(myArticles.get(2));
       // System.out.println(webArticles.get(2).getText());

        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(webArticles.get(i).getText(), myArticles.get(i), "AAA! Failure!");
        }
      //  driver.close();
    }

    @AfterEach
    public void closeBroser(){
        driver.quit();
    }
}
