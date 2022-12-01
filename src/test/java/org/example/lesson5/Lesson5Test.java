package org.example.lesson5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lesson5Test extends AbstractTest {
    private final long PAUSE = 1000;

    @Test
    @DisplayName("Переход на главную страницу сайта нажатием на логотип 'PRODUCT STORE'")
    public void pressLogoTest() throws WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Надпись 'PRODUCT STORE' на логотипе сайта",
                "id", "nava");

        // Шаг№1. Переход на страницу сайта, отличную от главной
        Assertions.assertDoesNotThrow(() -> getDriver().navigate().to("https://www.demoblaze.com/prod.html?idp_=5"),
                "Страница не доступна");

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/prod.html?idp_=5"));

        // Шаг№2. Нажатие на надпись 'PRODUCT STORE' на логотипе сайта.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/index.html"));

        // Проверка перехода на главную страницу.
        Assertions.assertEquals("https://www.demoblaze.com/index.html", getDriver().getCurrentUrl(),
                "Переход на главную страницу не осуществлен");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Авторизация пользователя.")
    public void authorizationTest() throws WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка 'Log in' главного меню сайта", "id", "login2");
        Locator locator2 = new Locator("Поле ввода имени пользователя", "id", "loginusername");
        Locator locator3 = new Locator("Поле ввода пароля", "id", "loginpassword");
        Locator locator4 = new Locator("Кнопка 'Log in'", "css", "#logInModal .btn-primary");

        // Шаг№1. Нажатие на кнопку 'Log in'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Проверка открытия формы авторизации.
        Assertions.assertTrue(getDriver().findElement(By.id("logInModal")).isDisplayed(),
                "Форма авторизации не открылась.");

        // Шаги№2, 3, 4. Заполнение полей 'email' и 'password', нажатие на кнопку "Login".
        action.pause(PAUSE).
                click(findElement(isLocatorCorrect(locator2)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator2)), "Bob Smith")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator3)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator3)), "Enter000")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator4)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", getDriver().findElement(By.id("nameofuser")).getText());

        // Запись Cookie в файл.
        writeCookieToFile("Authorization_lesson5_Cookie.data");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Добавление товара в корзину.")
    public void addProductTest() throws WrongTypeOfLocatorException,
            WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка 'Monitors' в блоке 'CATEGORIES'",
                "xpath", "(//a[@id='itemc'])[3]");
        Locator locator2 = new Locator("Ссылка на страницу товара 'ASUS Full HD'",
                "xpath", ".//a[contains(text(),'ASUS Full HD')]");
        Locator locator3 = new Locator("Кнопка 'Add to cart'", "css", ".btn-success");
        Locator locator4 = new Locator("Кнопка 'Cart' главного меню сайта", "id", "cartur");
        Locator locator5 = new Locator("Кнопка 'Delete'", "css", "td > a");

        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Обновление страницы.
        getDriver().navigate().refresh();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", getDriver().findElement(By.id("nameofuser")).getText());

        // Переход на страницу категории 'Monitors'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/index.html#"));

        // Проверка перехода на страницу категории 'Monitors'.
        Assertions.assertEquals("https://www.demoblaze.com/index.html#", getDriver().getCurrentUrl(),
                "Переход на страницу категории 'Monitors' не осуществлен");

        // Переход на страницу товара.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator2)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/prod.html?idp_=14"));

        // Проверка перехода на страницу товара.
        Assertions.assertEquals("https://www.demoblaze.com/prod.html?idp_=14", getDriver().getCurrentUrl(),
                "Переход на страницу товара не осуществлен");

        // Нажатие на кнопку 'Add to cart'
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator3)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.alertIsPresent());

        // Проверка текста уведомления о добавлении товара.
        Assertions.assertEquals("Product added.", getDriver().switchTo().alert().getText());

        // Подтверждение действия на странице.
        getDriver().switchTo().alert().accept();

        // Переход в раздел 'Cart' главного меню сайта.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator4)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/cart.html"));

        // Проверка перехода на страницу корзины товаров.
        Assertions.assertEquals("https://www.demoblaze.com/cart.html", getDriver().getCurrentUrl(),
                "Переход на страницу корзины товаров не осуществлен");

        // Проверка наличия в корзине товара 'ASUS Full HD'.
        Assertions.assertEquals("ASUS Full HD",
                getDriver().findElement(By.cssSelector("#tbodyid > tr > td:nth-child(2)")).getText());


        // Удаление товара из корзины.
        // Нажатие на кнопку Delete.
        action.pause(2000)
                .click(findElement(isLocatorCorrect(locator5)))
                .pause(2000)
                .build()
                .perform();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Отправление сообщения в разделе Contact.")
    public void sendMessageTest() throws WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка Contact главного меню сайта", "css",
                ".nav-item:nth-child(2) > .nav-link");
        Locator locator2 = new Locator("Поле ввода 'Contact Email'", "id", "recipient-email");
        Locator locator3 = new Locator("Поле ввода 'Contact Name'", "id", "recipient-name");
        Locator locator4 = new Locator("Поле ввода 'Message'", "id", "message-text");
        Locator locator5 = new Locator("Кнопка 'Send message'", "css", "#exampleModal .btn-primary");

        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Обновление страницы.
        getDriver().navigate().refresh();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", getDriver().findElement(By.id("nameofuser")).getText());

        // Шаг№1. Нажатие на кнопку 'Contact'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Проверка открытия формы отправки сообщения.
        Assertions.assertTrue(getDriver().findElement(By.id("exampleModalLabel")).isDisplayed(),
                "Форма авторизации не открылась.");

        // Шаг№2. Заполнение полей 'Contact Email', 'Contact Name' и 'Message', нажатие на кнопку "Send message".
        action.pause(PAUSE).
                click(findElement(isLocatorCorrect(locator2)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator2)), "bobsmith@gmail.com")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator3)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator3)), "Bob Smith")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator4)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator4)), "My Message")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator5)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
       new WebDriverWait(getDriver(), 5).until(ExpectedConditions.alertIsPresent());

        // Проверка текста уведомления об отправке сообщения.
        Assertions.assertEquals("Thanks for the message!!", getDriver().switchTo().alert().getText());

        // Подтверждение действия на странице.
        getDriver().switchTo().alert().accept();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Оформление заказа.")
    public void placeOrderTest() throws WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка 'Cart' главного меню сайта", "id", "cartur");
        Locator locator2 = new Locator("Кнопка 'Place Order'", "css", ".btn-success");
        Locator locator3 = new Locator("Поле ввода 'Name'", "id", "name");
        Locator locator4 = new Locator("Поле ввода 'Country'", "id", "country");
        Locator locator5 = new Locator("Поле ввода 'City'", "id", "city");
        Locator locator6 = new Locator("Поле ввода 'Credit card'", "id", "card");
        Locator locator7 = new Locator("Поле ввода 'Month'", "id", "month");
        Locator locator8 = new Locator("Поле ввода 'Year'", "id", "year");
        Locator locator9 = new Locator("Кнопка 'Purchase'", "css", "#orderModal .btn-primary");

        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Обновление страницы.
        getDriver().navigate().refresh();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", getDriver().findElement(By.id("nameofuser")).getText());

        // Переход в раздел 'Cart' главного меню сайта.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/cart.html"));

        // Проверка перехода на страницу корзины товаров.
        Assertions.assertEquals("https://www.demoblaze.com/cart.html", getDriver().getCurrentUrl(),
                "Переход на страницу корзины товаров не осуществлен");

        // Нажатие на кнопку 'Place Order'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator2)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("orderModalLabel")));

        // Проверка открытия формы оформления заказа.
        Assertions.assertTrue(getDriver().findElement(By.id("orderModalLabel")).isDisplayed(),
                "Форма оформления заказа не открылась.");

        // Заполнение полей 'Name', 'Country', 'City', 'Credit card', 'Month', 'Year' и нажатие на кнопку "Purchase".
        action.pause(PAUSE).
                click(findElement(isLocatorCorrect(locator3)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator3)), "Bob Smith")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator4)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator4)), "USA")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator5)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator5)), "New York")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator6)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator6)), "0000 0000 0000 0000")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator7)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator7)), "November")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator8)))
                .pause(PAUSE)
                .sendKeys(findElement(isLocatorCorrect(locator8)), "2022")
                .pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator9)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("orderModalLabel")));

        // Проверка появления сообщения об оформлении заказа.
        Assertions.assertTrue(getDriver().getPageSource().contains("Thank you for your purchase!"));

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Проверка возможности воспроизведения видео 'About us'")
    public void aboutUsVideoTest() throws WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка 'About us' главного меню сайта", "css",
                ".nav-item:nth-child(3) > .nav-link");
        Locator locator2 = new Locator("Кнопка воспроизведения видео", "css",
                ".vjs-big-play-button > .vjs-icon-placeholder");
        Locator locator3 = new Locator("Кнопка 'Pause'", "css",
                ".vjs-play-control > .vjs-icon-placeholder");
        Locator locator4 = new Locator("Кнопка 'Close'", "css",
                "#videoModal .btn");

        // Нажатие на кнопку 'About us' главного меню сайта.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(PAUSE)
                .build()
                .perform();

        // Явное ожидание.
        new WebDriverWait(getDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("videoModalLabel")));

        // Проверка наличия кнопки воспроизведения видео.
        Assertions.assertTrue(getDriver()
                .findElement(By.cssSelector("#example-video > button > span.vjs-icon-placeholder")).isEnabled());

        // Нажатие на кнопку воспроизведения видео.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator2)))
                .pause(PAUSE)
                .build()
                .perform();

        // Нажатие на кнопку 'Pause'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator3)))
                .pause(PAUSE)
                .build()
                .perform();

        // Нажатие на кнопку 'Close'.
        action.pause(PAUSE)
                .click(findElement(isLocatorCorrect(locator4)))
                .pause(PAUSE)
                .build()
                .perform();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }


    /*@Test
    @DisplayName("Авторизация пользователя типа standard")
    public void authorizationTest() throws InterruptedException,
            WrongTypeOfLocatorException, WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Поле ввода имени пользователя", "id", "user-name");
        Locator locator2 = new Locator("Поле ввода пароля", "id", "password");
        Locator locator3 = new Locator("Кнопка 'Login'", "id", "login-button");

        // Заполнение полей email и password, нажатие на кнопку "Login".
        action.sendKeys(findElement(isLocatorCorrect(locator1)), "standard_user")
                .pause(2000l)
                .sendKeys(findElement(isLocatorCorrect(locator2)), "secret_sauce")
                .pause(2000l)
                .click(findElement(isLocatorCorrect(locator3)))
                .pause(2000l)
                .build()
                .perform();

        Assertions.assertTrue(getDriver().getPageSource().contains("Products"), "Тест завершен с ошибкой");

        writeCookieToFile("User_standard_Cookie.data");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");

        Thread.sleep(3000);
    }*/

    /* @Test
    @DisplayName("Выход из аккаунта пользователя")
    public void logOutTest () throws WrongTypeOfLocatorException, WrongPathOfLocatorException, InterruptedException {
        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Кнопка 'Log Out' главного меню сайта",
                "id", "logout2");

        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Обновление страницы.
        getDriver().navigate().refresh();

        Actions action = new Actions(getDriver());

        // Нажатие на кнопку 'Log Out' главного меню сайта.
        action.pause(2000)
                .click(findElement(isLocatorCorrect(locator1)))
                .pause(2000)
                .build()
                .perform();

        Thread.sleep(3000);

        getDriver().navigate().refresh();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");

        Thread.sleep(3000);

    }*/

    /*@Test
    @DisplayName("Добавление товара в корзину")
    public void addProductTest() throws InterruptedException, WrongTypeOfLocatorException,
            WrongPathOfLocatorException {

        Actions action = new Actions(getDriver());

        JavascriptExecutor jsExecutor = (JavascriptExecutor) getDriver();

        // Локаторы, используемые в тесте:
        Locator locator1 = new Locator("Ссылка на страницу товара 'Sauce Labs Bolt T-Shirt'",
                "css", "#item_1_title_link > .inventory_item_name");
        Locator locator2 = new Locator("Кнопка 'ADD TO CART'", "id",
                "add-to-cart-sauce-labs-bolt-t-shirt");
        Locator locator3 = new Locator("Иконка корзины товаров", "css",
                ".shopping_cart_badge");

        // Добавление Cookie с авторизованным пользователем типа standard.
        addCookiesFromFile("User_standard_Cookie.data");

        // Переход на страницу со списком товаров.
        Assertions.assertDoesNotThrow(() -> getDriver().navigate().to("https://www.saucedemo.com/inventory.html"),
                "Страница не доступна");

        // Прокручивание страницы.
        jsExecutor.executeScript("window.scrollBy(0,100)");
        Thread.sleep(2000);

        // Переход по ссылке на страницу товара 'Sauce Labs Bolt T-Shirt'
        action.click(findElement(isLocatorCorrect(locator1)))
                .pause(2000l)
                .build()
                .perform();

        Assertions.assertTrue(getDriver().getCurrentUrl().contains("https://www.saucedemo.com/inventory-item.html?id=1"),
                "Тест завершен с ошибкой.");

        // Нажатие на кнопку 'ADD TO CART'
        action.click(findElement(isLocatorCorrect(locator2)))
                .pause(2000l)
                .build()
                .perform();

        Assertions.assertTrue(getDriver().getPageSource().contains("remove-sauce-labs-bolt-t-shirt"));

        // Нажатие на иконку корзины товаров.
        action.click(findElement(isLocatorCorrect(locator3)))
                .pause(2000l)
                .build()
                .perform();

        writeCookieToFile("Cart_Cookie.data");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");

        Thread.sleep(3000);
    }*/

    /*@Test
    @DisplayName("Удаление товара из корзины")
    public void deleteProductTest() throws InterruptedException {

        getDriver().manage().deleteAllCookies();

        getDriver().manage().addCookie(new Cookie(
                "PrestaShop-a30a9934ef476d11b6cc3c983616e364",
                "6jnbJ5Mv%2FpoGc2NKL8pNJ4YFXCOUOstwz0u61yL66Uc4J%2B6JotnvT5gV6cZH8fhtxASJY9b46wEKeWwPoWo0tH5HPnGbZ0I5HiLDIKr66jB7g4WHRR%2BFJ1HU7TpFBCg8fAe%2BpgT5RrRb6l1tttGz4tUeMRe4rIX4eO0fP22IAJlxZLY%2FkuWqla7wS3pekPNH4kmhA64nbwDaF51sFLOU3K%2BvX27KdfJYjcviT7Kmj5U%3D000161"));

        getDriver().navigate().refresh();

        org.example.lesson3.home_work3.Main.testCase3(getDriver());

        // getDriver().manage().deleteAllCookies();

        System.out.println("Тест успешно завершен");

        Thread.sleep(3000);
    }*/

   /* @Test
    @DisplayName("Test")
    public void test() throws InterruptedException {

        addCookiesFromFile("Authorization_lesson5_Cookie.data");
        //getDriver().navigate().to("https://www.saucedemo.com/cart.html");
        getDriver().navigate().refresh();

        *//*Locator locator4 = new Locator("Кнопка 'Cart' главного меню сайта", "id", "cartur");

        Actions action = new Actions(getDriver());

        action.pause(2000)
                .click(findElement(isLocatorCorrect(locator4)))
                .build()
                .perform();*//*


        Thread.sleep(3000);

    }*/
}
