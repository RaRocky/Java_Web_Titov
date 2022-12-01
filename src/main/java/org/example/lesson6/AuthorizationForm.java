package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizationForm extends AbstractPage{

    @FindBy(id = "loginusername")
    private WebElement userName;

    @FindBy(id = "loginpassword")
    private WebElement password;

    @FindBy (css = "#logInModal .btn-primary")
    private WebElement logInButton;

    public AuthorizationForm(WebDriver driver) {
        super(driver);
    }

    public AuthorizationForm enterUserName (String name){
        userName.click();
        userName.sendKeys(name);
        return this;
    }

    public AuthorizationForm enterPassword (String passwd){
        password.click();
        password.sendKeys(passwd);
        return this;
    }

    public AuthorizationForm logInUser (String name, String passwd) {
        enterUserName(name);
        enterPassword(passwd);
        logInButton.click();
        return this;
    }
}
