package org.example.lesson6;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.Duration;
import java.util.StringTokenizer;

public class AbstractTest {

    static WebDriver webDriver;

    @BeforeAll
    static void setDriver(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        options.setPageLoadTimeout(Duration.ofSeconds(10));


        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @BeforeEach
    void initMainPage(){
        Assertions.assertDoesNotThrow( ()-> getWebDriver().navigate().to("https://www.demoblaze.com/index.html"),
                "Страница не доступна");
        Assertions.assertTrue(true);

    }

    @AfterAll
    public static void exit(){
        if(webDriver !=null) webDriver.quit();
    }

    public WebDriver getWebDriver(){
        return AbstractTest.webDriver;
    }

    public static void addCookiesFromFile(String fileName) {

        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            // Чтение файла построчно при помощи BufferedReader.
            while ((line = bufferedReader.readLine()) != null) {

                // Чтение параметров Cookie, разделенных ";".
                StringTokenizer token = new StringTokenizer(line, ";");

                while (token.hasMoreTokens()) {
                    String name = token.nextToken();
                    String value = token.nextToken();
                    String domain = token.nextToken();
                    String path = token.nextToken();
                    //String date = token.nextToken();
                    String isSecureStr = token.nextToken();

                   /* // Разделение составляющих даты.
                    String[] partsOfDate = date.split("\\s");
                    int year = Integer.parseInt(partsOfDate[5]);
                    String monthStr = partsOfDate[1];
                    int dayOfMonth = Integer.parseInt(partsOfDate[2]);
                    String time = partsOfDate[3];

                    // Замена сокращенного названия месяца соответствующим числовым значением.
                    int month = 0;
                    if (monthStr.equals("Jan")) month = 1;
                    if (monthStr.equals("Feb")) month = 2;
                    if (monthStr.equals("Mar")) month = 3;
                    if (monthStr.equals("Apr")) month = 4;
                    if (monthStr.equals("May")) month = 5;
                    if (monthStr.equals("June")) month = 6;
                    if (monthStr.equals("July")) month = 7;
                    if (monthStr.equals("Aug")) month = 8;
                    if (monthStr.equals("Sep")) month = 9;
                    if (monthStr.equals("Oct")) month = 10;
                    if (monthStr.equals("Nov")) month = 11;
                    if (monthStr.equals("Dec")) month = 12;


                    // Разделение времени на часы, минуты и секунды.
                    String[] partsOfTime = time.split(":");
                    int hours = Integer.parseInt(partsOfTime[0]);
                    int minutes = Integer.parseInt(partsOfTime[1]);
                    int seconds = Integer.parseInt(partsOfTime[2]);*/

                    boolean isSecure;
                    isSecure = isSecureStr.equals("true");

                    Cookie cookie = new Cookie.Builder(name, value)
                            .domain(domain)
                            .path(path)
                            //.expiresOn(new Date(year, month, dayOfMonth, hours, minutes, seconds))
                            .isSecure(isSecure)
                            .build();

                    webDriver.manage().addCookie(cookie);
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void addAuthorizationCookie () {
        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Обновление страницы.
        webDriver.navigate().refresh();

        // Явное ожидание.
        new WebDriverWait(webDriver, Duration.ofSeconds(5)).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", webDriver.findElement(By.id("nameofuser")).getText());
    }
}
