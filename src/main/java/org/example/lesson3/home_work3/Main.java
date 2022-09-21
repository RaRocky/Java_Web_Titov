package org.example.lesson3.home_work3;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {

        testCase2();
    }

    // Метод запускает автотест по тест-кейсу №1 - Авторизация пользователя на сайте.
    public static void testCase1() {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №1.
        Test test = new Test();

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №1.

        Locator locator1 = new Locator("Кнопка 'Sign in'","css", ".login");
        Locator locator2 = new Locator("Поле ввода email","id", "email");
        Locator locator3 = new Locator("Поле ввода пароля","id", "passwd");
        Locator locator4 = new Locator("Кнопка подтверждения регистрации","id", "SubmitLogin");

        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №1.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);

        // Реализация операция, описанных в тест-кейсе №1.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }

    // Метод запускает автотест по тест-кейсу №2 - Добавление товара в корзину

    public static void testCase2() {

        // Создание объекта класса Test с функционалом для запуска тест-кейса №2.
        Test test = new Test();

        // Создание объектов класса Locator, определяющих элементы сайта, задействованных в тест-кейсе №2.

        Locator locator1 = new Locator("Кнопка главного меню 'T-SHIRTS'","xpath",
                ".//div[@id='block_top_menu']/ul/li[3]/a");

        Locator locator2 = new Locator("Изображение товара","xpath",
                ".//img[@alt='Faded Short Sleeve T-shirts']");

        Locator locator3 = new Locator("Кнопка 'Add to cart'","id", "add_to_cart");

        Locator locator4 = new Locator("Кнопка Proceed to checkout"  ,"xpath",
                ".//a[@title='Proceed to checkout']/span");

        // Создание связанного списка элементов сайта, задействованных в тест-кейсе №2.

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);

        // Реализация операция, описанных в тест-кейсе №2.

        for (Locator iterator : locators) {
            test.isLocatorCorrect(test.countElements(iterator), iterator);
            test.doTest(test.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }
}

