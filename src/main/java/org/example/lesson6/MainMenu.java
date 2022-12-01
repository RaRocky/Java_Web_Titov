package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainMenu extends AbstractPage{

    @FindBy(id = "login2")
    private WebElement logIn;

    @FindBy (id = "cartur")
    private WebElement cart;

    @FindBy (css = ".nav-item:nth-child(2) > .nav-link")
    private WebElement contact;

    @FindBy (css = ".nav-item:nth-child(3) > .nav-link")
    private WebElement aboutUs;

    public MainMenu(WebDriver driver) {
        super(driver);
    }

    public MainMenu clickLogIn(){
        logIn.click();
        return this;
    }

    public MainMenu clickCart(){
        cart.click();
        return this;
    }

    public MainMenu clickContact(){
        contact.click();
        return this;
    }

    public MainMenu clickAboutUs(){
        aboutUs.click();
        return this;
    }
}
