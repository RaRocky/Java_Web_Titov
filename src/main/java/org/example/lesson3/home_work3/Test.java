package org.example.lesson3.home_work3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test {
    WebDriver driver;
    Test() {
        WebDriverManager.chromedriver().setup(); // установка веб-драйвера для браузера Chrome.
        ChromeOptions options = new ChromeOptions(); // объявление настроек браузера.
        options.addArguments("--incognito"); // режим инкогнито, отключает кэш, чтобы автотесты не влияли на состояние сайта.
        //options.addArguments("--headless"); // запуск браузера в фоновом режиме.
        options.addArguments("start-maximized"); // запуск браузера в полноэкранном режиме.
        driver = new ChromeDriver(options); // запуск самого браузера.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php"); // стартовая страница, с которой запускается браузер.
    }

    public List<WebElement> findElements(Locator locator) {
        if (locator.getType().equals("id")) return driver.findElements(By.id(locator.getPath()));
        if (locator.getType().equals("css")) return driver.findElements(By.cssSelector(locator.getPath()));
        if (locator.getType().equals("xpath")) return driver.findElements(By.xpath(locator.getPath()));
        else return null;
    }

    public Integer countElements(Locator locator) {
        if (locator.getType().equals("id")) return driver.findElements(By.id(locator.getPath())).size();
        if (locator.getType().equals("css")) return driver.findElements(By.cssSelector(locator.getPath())).size();
        if (locator.getType().equals("xpath")) return driver.findElements(By.xpath(locator.getPath())).size();
        else return 0;
    }

    public boolean isLocatorCorrect(Integer countOfElements, Locator locator) {
        if (countOfElements == 0) {
            System.out.println("Элементов, соответствующих локатору '" + locator.getPath() + "' не найдено.");
            return false;
        }
        if (countOfElements > 1) {
            System.out.println(locator.getPath() + " - Не уникальный локатор. Задайте другой.");
            return false;
        } else {
            System.out.println("Элемент с локатором '" + locator.getPath() + "' успешно найден.");
            return true;
        }
    }

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