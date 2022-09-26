package org.example.lesson3.home_work3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;

public class Test {
    WebDriver driver;

    // Конструктор.
    Test(WebDriver driver) {
        this.driver = driver;
    }

    // Метод находит элементы сайта по заданному типу локатора.
    public List<WebElement> findElements(Locator locator) {
        if (locator.getType().equals("id")) return driver.findElements(By.id(locator.getPath()));
        if (locator.getType().equals("css")) return driver.findElements(By.cssSelector(locator.getPath()));
        if (locator.getType().equals("xpath")) return driver.findElements(By.xpath(locator.getPath()));
        else return null;
    }

    // Метод подсчитывает количество элементов, найденных по заданному локатору.
    public Integer countElements(Locator locator) {
        if (locator.getType().equals("id")) return driver.findElements(By.id(locator.getPath())).size();
        if (locator.getType().equals("css")) return driver.findElements(By.cssSelector(locator.getPath())).size();
        if (locator.getType().equals("xpath")) return driver.findElements(By.xpath(locator.getPath())).size();
        else return 0;
    }

    // Метод определяет существование и уникальность элемента сайта по заданному локатору.
    public void isLocatorCorrect(Integer countOfElements, Locator locator) {

        if (countOfElements == 0) {
            System.out.println("Элементов, соответствующих '" + locator.getName() + "' c локатором " +
                    locator.getType() + " = '" + locator.getPath() + "' не найдено.");
            return;
        }
        if (countOfElements > 1) {
            System.out.println("'" + locator.getName() + "' с локатором " + locator.getType() + " = '"
                    + locator.getPath() + "' - не уникальный. Задайте другой.");
        } else {
            System.out.println("Элемент '" + locator.getName() + "' с локатором " + locator.getType() + " = '" +
                    locator.getPath() + "' успешно найден.");
        }
    }

    // Метод совершения операций автотестировщика над элементами на сайте.
    public void doTest(List<WebElement> webElements) {

        if (webElements.get(0).getTagName().equals("input") &&
                webElements.get(0).getAttribute("id").equals("email")) {
            webElements.get(0).sendKeys("rjc45017@xcoxc.com");
        }

        if (webElements.get(0).getTagName().equals("input") &&
                webElements.get(0).getAttribute("id").equals("passwd")) {
            webElements.get(0).sendKeys("Enter000");
        }

        webElements.get(0).click();
    }
}
