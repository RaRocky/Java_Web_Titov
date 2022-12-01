package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends AbstractPage{

    @FindBy (css = ".btn-success")
    private WebElement addToCart;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage clickAddToCart (){
        addToCart.click();
        return this;
    }
}
