package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PlaceOrderForm extends AbstractPage {

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement creditCardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(css = "#orderModal .btn-primary")
    private WebElement purchaseButton;

    public PlaceOrderForm(WebDriver driver) {
        super(driver);
    }

    public PlaceOrderForm enterName(String name) {
        nameField.click();
        nameField.sendKeys(name);
        return this;
    }

    public PlaceOrderForm enterCountry(String country) {
        countryField.click();
        countryField.sendKeys(country);
        return this;
    }

    public PlaceOrderForm enterCity(String city) {
        cityField.click();
        cityField.sendKeys(city);
        return this;
    }

    public PlaceOrderForm enterCreditCard(String creditCard) {
        creditCardField.click();
        creditCardField.sendKeys(creditCard);
        return this;
    }

    public PlaceOrderForm enterMonth(String month) {
        monthField.click();
        monthField.sendKeys(month);
        return this;
    }

    public PlaceOrderForm enterYear(String year) {
        monthField.click();
        monthField.sendKeys(year);
        return this;
    }

    public PlaceOrderForm clickPurchaseButton() {
        purchaseButton.click();
        return this;
    }

    public PlaceOrderForm sendOrder(String name, String country, String city,
                                    String creditCard, String month, String year) {
        enterName(name).
        enterCountry(country).
        enterCity(city).
        enterCreditCard(creditCard).
        enterMonth(month).
        enterYear(year).
        clickPurchaseButton();
        return this;
    }
}
