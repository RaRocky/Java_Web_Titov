package org.example.lesson3.home_work3;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Browser {

    WebDriver driver;

    Browser () {
        WebDriverManager.chromedriver().setup(); // установка веб-драйвера для браузера Chrome.
        ChromeOptions options = new ChromeOptions(); // объявление настроек браузера.
        //options.addArguments("--incognito"); // режим инкогнито, отключает кэш, чтобы автотесты не влияли на состояние сайта.
        //options.addArguments("--headless"); // запуск браузера в фоновом режиме.
        options.addArguments("start-maximized"); // запуск браузера в полноэкранном режиме.
        driver = new ChromeDriver(options); // запуск самого браузера.
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

    }

    public WebDriver getDriver() {
        return driver;
    }
}
