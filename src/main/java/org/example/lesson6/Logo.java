package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Logo extends AbstractPage {

    @FindBy(id = "nava")
    private WebElement logo;

    public Logo(WebDriver driver) {
        super(driver);
    }

    public Logo clickLogo(){
        logo.click();
        return this;
    }
}
