package org.example.lesson5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.*;
import java.time.Duration;
import java.util.StringTokenizer;

public class AbstractTest {

    private static WebDriver driver;

    @BeforeAll
    static void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    @BeforeEach
    void goTo() {
        Assertions.assertDoesNotThrow(() -> driver.navigate().to("https://www.demoblaze.com/index.html"),
                "Страница не доступна");

        /*Assertions.assertDoesNotThrow(() -> driver.navigate().to("https://www.saucedemo.com/"),
                "Страница не доступна");*/
    }

    @AfterAll
    static void close() {
        driver.quit();
    }

    @AfterEach
    void deleteCookies() {
        driver.manage().deleteAllCookies();
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Locator isLocatorCorrect(Locator locator) throws WrongPathOfLocatorException,
            WrongTypeOfLocatorException {

        int countOfLocatorPaths;

        switch (locator.getType()) {
            case "id":
                countOfLocatorPaths = driver.findElements(By.id(locator.getPath())).size();
                break;

            case "css":
                countOfLocatorPaths = driver.findElements(By.cssSelector(locator.getPath())).size();
                break;

            case "xpath":
                countOfLocatorPaths = driver.findElements(By.xpath(locator.getPath())).size();
                break;
            default:
                throw new WrongTypeOfLocatorException("Несуществующий тип локатора." +
                        " Возможные варианты: 'id', 'css', 'xpath'");
        }

        if (countOfLocatorPaths == 0) throw new WrongPathOfLocatorException("Элементов, соответствующих '"
                + locator.getName() + "' c локатором '" + locator.getType() +
                " = '" + locator.getPath() + "' не найдено.");
        if (countOfLocatorPaths > 1) throw new WrongPathOfLocatorException("Элемент '" + locator.getName() +
                "' с локатором " + locator.getType() + " = '" + locator.getPath() +
                "' - не уникальный. Задайте другой.");
        System.out.println("Элемент '" + locator.getName() + "' с локатором '" + locator.getType() + " = " +
                locator.getPath() + "' успешно найден.");
        return locator;
    }

    public static WebElement findElement(Locator locator) {

        switch (locator.getType()) {
            case "id":
                return driver.findElement(By.id(locator.getPath()));

            case "css":
                return driver.findElement(By.cssSelector(locator.getPath()));

            case "xpath":
                return driver.findElement(By.xpath(locator.getPath()));

            default:
                return null;
        }
    }

    public static void writeCookieToFile(String fileName) {

        // Создание файла для записи значений Cookie.
        File file = new File(fileName);

        try {
           /* // Удаление файла Cookie, если он существует.
            file.delete();

            // Создание нового файла Cookie.
            file.createNewFile();*/


            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Запись значений Cookie в файл.
            for (Cookie ck : driver.manage().getCookies()) {
                bufferedWriter.write((ck.getName() + ";" + ck.getValue() + ";" + ck.getDomain() + ";" + ck.getPath() +
                        ";" + ck.getExpiry() + ";" + ck.isSecure()));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            fileWriter.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
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

                    getDriver().manage().addCookie(cookie);
                }
            }
            fileReader.close();
            bufferedReader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

