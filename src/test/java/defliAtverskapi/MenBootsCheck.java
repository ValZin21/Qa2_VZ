package defliAtverskapi;

import defliAtverskapi.pages.BaseFunctions;
import defliAtverskapi.pages.HomePage;
import org.apache.http.conn.util.PublicSuffixList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MenBootsCheck {

    private BaseFunctions baseFunctions = new BaseFunctions();
    private static final String HOME_PAGE = "http://atverskapi.delfi.lv/lv/style";
//    public static final By DROP_DOWN_MENU = By.xpath(".//li[@class='dropdown']");
//    public static final By DROP_DOWN_MENU_SUMMARY = By.xpath(".//li[@class='dropdown']/a");
//    public static final By DROP_DOWN_MENU_OPEN = By.xpath(".//li[@class='dropdown open']");
//    public static final By DROP_DOWN_MUENU_ELEMENTS = By.xpath(".//ul[@class='dropdown-menu']/li/a");



    @Test
    public void new5BlackBootsCheck() {
        baseFunctions.goToPage(HOME_PAGE);

        HomePage homePage = new HomePage(baseFunctions);
        homePage.findAndOpenVirieshiDropDownMenu();
        homePage.findAndClickOnApaviProduct();




















    }

    @AfterEach
    private void drvierQuit(){
        baseFunctions.driver.quit();
    }
}


  /*CU
  ** В проекте Delfi Atverskapi выбрать мужские чёрные туфли новые,проверить,что первые 5позиций соответствуют данному критерию

  ** PLAN
  * Заходимa http://atverskapi.delfi.lv/ru/style
  *
  * На home page Ищем drop-down menu "Ддя мужчин"
  * Ситываем оттуда все значения в лист
  * Проверяем не пустой ли лист
  * Проверяем, есть ли там раздел обувь; если есть - кликаем на него, и переходим на страницу товаров, если нет - эррор
  *
  * На странице товаров ищем подкатегорию Обувь
  * считываем в лист оттуда все значения
  * проверяем, не пустой ли лист
  * ищем в листе туфли, если есть - выбираем, если нет - эррор
  * проверяем, применился ли фильтр
  *
  * дальше - На странице товаров ищем раздел "Цвет"
  * считываем в лист оттуда все значения
  * проверяем, не пустой ли лист
  * ищем в листе чёрный цвет, если есть - выбираем, если нет - эррор
  * проверяем, применился ли фильтр
  *
  * дальше - На странице товаров ищем раздел "Состояние"
  * считываем в лист оттуда все значения
  * проверяем, не пустой ли лист
  * ищем в листе "новый", если есть - выбираем, если нет - эррор
  * проверяем, применился ли фильтр
  *
  * дальше - на странице продуктов считываем в лист первые пять отфильтрованных продуктов
  * Проверяем, не пустой ли лист
  * через цикл по очереди открываем каждый продукт
  *
  * И на странице описания продукта проверяем правильность критериев выбора
  * туфли - из списка фильтров в врехнем левом углу
  * цвет - из списка описания продукта
  * состояние - из списка описания продукта
  * возвращаемся назад на отфильтрованную старницу товаров.
  */
