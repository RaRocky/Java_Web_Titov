package org.example.lesson3.home_work3;

import java.util.Iterator;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        testCase1();
    }

    public static void testCase1() {
        Test test1 = new Test();

        Locator locator1 = new Locator("css", ".login");
        Locator locator2 = new Locator("id", "email");
        Locator locator3 = new Locator("id", "passwd");
        Locator locator4 = new Locator("id", "SubmitLogin");

        LinkedList<Locator> locators = new LinkedList<>();
        locators.add(locator1);
        locators.add(locator2);
        locators.add(locator3);
        locators.add(locator4);

        for (Locator iterator : locators) {
            test1.isLocatorCorrect(test1.countElements(iterator), iterator);
            test1.doTest(test1.findElements(iterator));
        }
        System.out.println("--------------------------------");
        System.out.println("Тест успешно завершен.");
    }
}
