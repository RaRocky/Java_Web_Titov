package org.example.lesson3.home_work3;

import org.openqa.selenium.WebDriver;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        Browser browser = new Browser();

        while (true) {
            System.out.println();
            System.out.println("Выполнить тест-кейс №1 - Введите '1'");
            System.out.println("Выполнить тест-кейс №2 - Введите '2'");
            System.out.println("Выполнить тест-кейс №3 - Введите '3'(Сначала необходимо выполнить тест№2 или выполните " +
                    "предусловие тест-кейса №3 вручную.)");
            System.out.println("Выполнить тест-кейс №4 - Введите '4' (Сначала необходимо выполнить тест №1 или выполните" +
                    "предусловие тест-кейса №4 вручную)");
            System.out.println("Выход - Введите 'exit'");

            switch (input.next()) {
                case "1":
                    testCase1(browser.getDriver());
                    break;
                case "2":
                    testCase2(browser.getDriver());
                    break;
                case "3":
                    testCase3(browser.getDriver());
                    break;
                case "4":
                    testCase4(browser.getDriver());
                    break;
                case "exit":
                    input.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Введите правильный запрос.");
                    break;
            }
        }
    }


    // Метод запускает автотест по тест-кейсу №1 - Авторизация пользователя на сайте.
    public static void testCase1(WebDriver driver) {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №1.
        Test test = new Test(driver);

        // Переход на главную страницу сайта.
        driver.get("http://automationpractice.com/index.php");

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №1.

        Locator locator1 = new Locator("Кнопка 'Sign in'", "css", ".login");
        Locator locator2 = new Locator("Поле ввода email", "id", "email");
        Locator locator3 = new Locator("Поле ввода пароля", "id", "passwd");
        Locator locator4 = new Locator("Кнопка подтверждения авторизации", "id", "SubmitLogin");

        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №1.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);

        // Реализация операций, описанных в тест-кейсе №1.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }


    // Метод запускает автотест по тест-кейсу №2 - Добавление товара в корзину
    public static void testCase2(WebDriver driver) {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №2.
        Test test = new Test(driver);

        // Переход на главную страницу сайта.
        driver.get("http://automationpractice.com/index.php");

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №2.

        Locator locator1 = new Locator("Кнопка 'T-SHIRTS' главного меню", "xpath",
                ".//div[@id='block_top_menu']/ul/li[3]/a");

        Locator locator2 = new Locator("Изображение товара", "css",
                ".product_img_link > .replace-2x");

        Locator locator3 = new Locator("Кнопка 'Add to cart'", "id", "add_to_cart");

        Locator locator4 = new Locator("Кнопка 'Proceed to checkout'", "xpath",
                ".//a[@title = 'Proceed to checkout']/span");

        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №2.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);

        // Реализация операций, описанных в тест-кейсе №2.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }


    // Метод запускает автотест по тест-кейсу №3 - Удаление товара из корзины
    public static void testCase3(WebDriver driver) {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №3.
        Test test = new Test(driver);

        // Переход на главную страницу сайта.
        driver.get("http://automationpractice.com/index.php");

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №3.

        Locator locator1 = new Locator("Кнопка 'Cart'", "xpath",
                ".//a[@title='View my shopping cart']/b");

        Locator locator2 = new Locator("Кнопка удаления товара", "css",
                ".icon-trash");

        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №3.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);

        // Реализация операций, описанных в тест-кейсе №3.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }


    // Метод запускает автотест по тест-кейсу №4 - Добавление товара в список желаемого
    public static void testCase4(WebDriver driver) {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №4.
        Test test = new Test(driver);

        // Переход на главную страницу сайта.
        driver.get("http://automationpractice.com/index.php");

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №4.

        Locator locator1 = new Locator("Кнопка 'T-SHIRTS' главного меню", "xpath",
                ".//div[@id='block_top_menu']/ul/li[3]/a");

        Locator locator2 = new Locator("Изображение товара", "css",
                ".product_img_link > .replace-2x");

        Locator locator3 = new Locator("Кнопка 'Add to wishlist'", "id", "wishlist_button");

       Locator locator4 = new Locator("Кнопка закрытия всплывающего уведомления", "css",
                ".fancybox-close");

        Locator locator5 = new Locator("Кнопка входа в личный кабинет пользователя", "css",
                ".account > span");

        Locator locator6 = new Locator("Кнопка 'MY WISHLISTS'", "xpath",
                ".//*[@title = 'My wishlists']/span");

        Locator locator7 = new Locator("Кнопка 'My wishlist'", "xpath",
                ".//a[contains(text(),'My wishlist')]");


        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №4.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);
        locators.add(locator5);
        locators.add(locator6);
        locators.add(locator7);

        // Реализация операций, описанных в тест-кейсе №4.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }
}

