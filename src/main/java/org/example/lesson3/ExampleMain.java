package org.example.lesson3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExampleMain {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup(); // установка веб-драйвера для браузера Chrome.
        ChromeOptions options = new ChromeOptions(); // объявление настроек браузера.
        options.addArguments("--incognito"); // режим инкогнито, отключает кэш, чтобы автотесты не влияли на состояние сайта.
        //options.addArguments("--headless"); // запуск браузера в фоновом режиме.
        options.addArguments("start-maximized"); // запуск браузера в полноэкранном режиме.


        WebDriver driver = new ChromeDriver(options); // запуск самого браузера.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("http://automationpractice.com/index.php"); // стартовая страница, с которой запускается браузер.

        // WebElement webElement2 = driver.findElement(By.cssSelector("input.gLFyf.gsfi"));

        String locator = ".login";
        List<WebElement> webElements = driver.findElements(By.cssSelector(locator));
        if (webElements.size() == 0)
            System.out.println("Элементов, соответствующих локатору '" + locator + "' не найдено.");
        if (webElements.size() > 1) System.out.println("Не уникальный локатор. Задайте другой.");
        else {
            System.out.println("Элемент с локатором " + locator + " успешно найден.");
            webElements.get(0).click();
        }

        locator = "email";
        webElements = driver.findElements(By.id(locator));
        if (webElements.size() == 0)
            System.out.println("Элементов, соответствующих локатору '" + locator + "' не найдено.");
        if (webElements.size() > 1) System.out.println("Не уникальный локатор. Задайте другой.");
        else {
            System.out.println("Элемент с локатором " + locator + " успешно найден.");
            webElements.get(0).click();
            webElements.get(0).sendKeys("rjc45017@xcoxc.com");
        }

        locator = "passwd";
        webElements = driver.findElements(By.id(locator));
        if (webElements.size() == 0)
            System.out.println("Элементов, соответствующих локатору '" + locator + "' не найдено.");
        if (webElements.size() > 1) System.out.println("Не уникальный локатор. Задайте другой.");
        else {
            System.out.println("Элемент с локатором " + locator + " успешно найден.");
            System.out.println(webElements.get(0).getTagName() + webElements.get(0).getAttribute("id"));
            webElements.get(0).click();
            webElements.get(0).sendKeys("Enter000");
        }

        locator = "SubmitLogin";
        webElements = driver.findElements(By.id(locator));
        if (webElements.size() == 0)
            System.out.println("Элементов, соответствующих локатору '" + locator + "' не найдено.");
        if (webElements.size() > 1) System.out.println("Не уникальный локатор. Задайте другой.");
        else {
            System.out.println("Элемент с локатором " + locator + " успешно найден.");
            webElements.get(0).click();
                        System.out.println("--------------------------------");
            System.out.println("Пользователь успешно авторизован");

        }
    }
}
