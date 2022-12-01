package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends AbstractPage {

    @FindBy (css = "td > a")
    private WebElement deleteButton;

    @FindBy (css = ".btn-success")
    private  WebElement placeOrderButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage clickDeleteButton(){
        deleteButton.click();
        return this;
    }

    public CartPage clickPlaceOrderButton() {
        placeOrderButton.click();
        return this;
    }
}
