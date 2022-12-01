package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MonitorsPage extends AbstractPage {

    @FindBy(xpath = ".//a[contains(text(),'ASUS Full HD')]")
    private WebElement asusFullHd;

    public MonitorsPage(WebDriver driver) {
        super(driver);
    }

    public MonitorsPage clickAsusFullHd() {
        asusFullHd.click();
        return this;
    }
}
