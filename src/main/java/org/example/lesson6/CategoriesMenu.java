package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CategoriesMenu extends AbstractPage{

    @FindBy (xpath = "(//a[@id='itemc'])[3]")
    private WebElement monitors;

    public CategoriesMenu(WebDriver driver) {
        super(driver);
    }

    public CategoriesMenu clickMonitors (){
        monitors.click();
        return this;
    }
}
