package org.example.lesson6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lesson6Test extends AbstractTest {

    @Test
    @DisplayName("Переход на главную страницу сайта нажатием на логотип 'PRODUCT STORE'")
    public void pressLogoTest() {

        // Шаг№1. Переход на страницу сайта, отличную от главной
        Assertions.assertDoesNotThrow(() -> getWebDriver().navigate().to(
                        "https://www.demoblaze.com/prod.html?idp_=5"),
                "Страница не доступна");

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/prod.html?idp_=5"));

        // Шаг№2. Нажатие на надпись 'PRODUCT STORE' на логотипе сайта.
        Logo logo = new Logo(getWebDriver());
        logo.clickLogo();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/index.html"));

        // Проверка перехода на главную страницу.
        Assertions.assertEquals("https://www.demoblaze.com/index.html", getWebDriver().getCurrentUrl(),
                "Переход на главную страницу не осуществлен");

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Авторизация пользователя.")
    public void authorizationTest() {

        // Шаг№1. Нажатие на кнопку 'Log in'.
        MainMenu mainMenu = new MainMenu(getWebDriver());
        mainMenu.clickLogIn();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("logInModal")));

        // Проверка открытия формы авторизации.
        Assertions.assertTrue(getWebDriver().findElement(By.id("logInModal")).isDisplayed(),
                "Форма авторизации не открылась.");

        // Шаг№2. Авторизация пользователя.
        AuthorizationForm authorizationForm = new AuthorizationForm(getWebDriver());
        authorizationForm.logInUser("Bob Smith", "Enter000");

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("nameofuser")));

        // Проверка наличия надписи 'Welcome Bob Smith'.
        Assertions.assertEquals("Welcome Bob Smith", getWebDriver().findElement(By.id("nameofuser")).getText());

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Добавление товара в корзину.")
    public void addProductTest() {
        // Добавление Cookie с авторизованным пользователем.
        addAuthorizationCookie();

        // Шаг№1. Переход на страницу категории 'Monitors'.
        CategoriesMenu categoriesMenu = new CategoriesMenu(getWebDriver());
        categoriesMenu.clickMonitors();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/index.html#"));

        // Проверка перехода на страницу категории 'Monitors'.
        Assertions.assertEquals("https://www.demoblaze.com/index.html#", getWebDriver().getCurrentUrl(),
                "Переход на страницу категории 'Monitors' не осуществлен");

        // Шаг№2. Переход на страницу товара.
        MonitorsPage monitorsPage = new MonitorsPage(getWebDriver());
        monitorsPage.clickAsusFullHd();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/prod.html?idp_=14"));

        // Проверка перехода на страницу товара.
        Assertions.assertEquals("https://www.demoblaze.com/prod.html?idp_=14", getWebDriver().getCurrentUrl(),
                "Переход на страницу товара не осуществлен");

        // Шаг№3. Нажатие на кнопку 'Add to cart'
        ProductPage productPage = new ProductPage(getWebDriver());
        productPage.clickAddToCart();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.alertIsPresent());

        // Проверка текста уведомления о добавлении товара.
        Assertions.assertEquals("Product added.", getWebDriver().switchTo().alert().getText());

        // Шаг№4. Подтверждение действия на странице.
        getWebDriver().switchTo().alert().accept();

        // Шаг№5. Переход в раздел 'Cart' главного меню сайта.
        MainMenu mainMenu = new MainMenu(getWebDriver());
        mainMenu.clickCart();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/cart.html"));

        // Проверка перехода на страницу корзины товаров.
        Assertions.assertEquals("https://www.demoblaze.com/cart.html", getWebDriver().getCurrentUrl(),
                "Переход на страницу корзины товаров не осуществлен");

        // Удаление товара из корзины.
        // Нажатие на кнопку Delete.
        CartPage cartPage = new CartPage(getWebDriver());
        cartPage.clickDeleteButton();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Отправление сообщения в разделе Contact.")
    public void sendMessageTest() {
        // Добавление Cookie с авторизованным пользователем.
        addAuthorizationCookie();

        // Шаг№1. Нажатие на кнопку 'Contact'.
        MainMenu mainMenu = new MainMenu(getWebDriver());
        mainMenu.clickContact();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("exampleModalLabel")));

        // Проверка открытия формы отправки сообщения.
        Assertions.assertTrue(getWebDriver().findElement(By.id("exampleModalLabel")).isDisplayed(),
                "Форма авторизации не открылась.");

        // Шаг№2. Отправка сообщения
        ContactForm contactForm = new ContactForm(getWebDriver());
        contactForm.sendContactMessage("bobsmith@gmail.com", "Bob Smith", "My Message");

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.alertIsPresent());

        // Проверка текста уведомления об отправке сообщения.
        Assertions.assertEquals("Thanks for the message!!", getWebDriver().switchTo().alert().getText());

        // Подтверждение действия на странице.
        getWebDriver().switchTo().alert().accept();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Оформление заказа.")
    public void placeOrderTest() {
        // Добавление Cookie с авторизованным пользователем.
        addCookiesFromFile("Authorization_lesson5_Cookie.data");

        // Шаг№1. Переход в раздел 'Cart' главного меню сайта.
        MainMenu mainMenu = new MainMenu(getWebDriver());
        mainMenu.clickCart();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions.urlContains(
                "https://www.demoblaze.com/cart.html"));

        // Проверка перехода на страницу корзины товаров.
        Assertions.assertEquals("https://www.demoblaze.com/cart.html", getWebDriver().getCurrentUrl(),
                "Переход на страницу корзины товаров не осуществлен");

        // Шаг№2. Нажатие на кнопку 'Place Order'.
        CartPage cartPage = new CartPage(getWebDriver());
        cartPage.clickPlaceOrderButton();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("orderModalLabel")));

        // Проверка открытия формы оформления заказа.
        Assertions.assertTrue(getWebDriver().findElement(By.id("orderModalLabel")).isDisplayed(),
                "Форма оформления заказа не открылась.");

        // Заполнение формы и отправка заказа.
        PlaceOrderForm placeOrderForm = new PlaceOrderForm(getWebDriver());
        placeOrderForm.sendOrder("Bob Smith", "USA", "New York", "0000 0000 0000 0000",
                "November", "2022");

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("orderModalLabel")));

        // Проверка появления сообщения об оформлении заказа.
        Assertions.assertTrue(getWebDriver().getPageSource().contains("Thank you for your purchase!"));

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }

    @Test
    @DisplayName("Проверка возможности воспроизведения видео 'About us'")
    public void aboutUsVideoTest() throws InterruptedException {
        // Нажатие на кнопку 'About us' главного меню сайта.
        MainMenu mainMenu = new MainMenu(getWebDriver());
        mainMenu.clickAboutUs();

        // Явное ожидание.
        new WebDriverWait(getWebDriver(), 5).until(ExpectedConditions
                .visibilityOfElementLocated(By.id("videoModalLabel")));

        // Проверка наличия кнопки воспроизведения видео.
        Assertions.assertTrue(getWebDriver()
                .findElement(By.cssSelector("#example-video > button > span.vjs-icon-placeholder")).isEnabled());

        // Нажатие на кнопку воспроизведения видео.
        AboutUsVideoWindow aboutUsVideoWindow = new AboutUsVideoWindow(getWebDriver());
        aboutUsVideoWindow.clickPlayVideoButton();

        // Нажатие на кнопку 'Pause'.
        aboutUsVideoWindow.clickPauseVideoButton();

        // Нажатие на кнопку 'Close'.
        aboutUsVideoWindow.clickCloseButton();

        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Тест успешно завершен");
    }
}


