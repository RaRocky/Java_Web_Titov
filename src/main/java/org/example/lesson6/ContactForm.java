package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactForm extends AbstractPage {

    @FindBy(id = "recipient-email")
    private WebElement contactEmail;

    @FindBy(id = "recipient-name")
    private WebElement contactName;

    @FindBy(id = "message-text")
    private WebElement contactMessage;

    @FindBy(css = "#exampleModal .btn-primary")
    private WebElement sendMessageButton;

    public ContactForm(WebDriver driver) {
        super(driver);
    }

    public ContactForm enterContactEmail(String email) {
        contactEmail.click();
        contactEmail.sendKeys(email);
        return this;
    }

    public ContactForm enterContactName(String name) {
        contactName.click();
        contactName.sendKeys(name);
        return this;
    }

    public ContactForm enterMessage(String text) {
        contactMessage.click();
        contactName.sendKeys(text);
        return this;
    }

    public ContactForm clickSendMessageButton() {
        sendMessageButton.click();
        return this;
    }

    public ContactForm sendContactMessage(String email, String name, String text) {
        enterContactEmail(email).enterContactName(name).enterMessage(text).clickSendMessageButton();
        return this;
    }
}
