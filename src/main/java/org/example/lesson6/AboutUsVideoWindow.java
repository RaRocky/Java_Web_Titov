package org.example.lesson6;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AboutUsVideoWindow extends AbstractPage{
    private final long PAUSE = 1000;
    @FindBy (css = "#example-video > button > span.vjs-icon-placeholder")
    private WebElement playVideoButton;

    @FindBy (css = ".vjs-play-control > .vjs-icon-placeholder")
    private WebElement pauseVideoButton;

    @FindBy(css = "#videoModal .btn")
    private WebElement closeButton;

    public AboutUsVideoWindow(WebDriver driver) {
        super(driver);
    }

    public AboutUsVideoWindow clickPlayVideoButton(){
        Actions action = new Actions(getDriver());
        action.pause(PAUSE)
                .click(playVideoButton)
                .pause(PAUSE)
                .build()
                .perform();
        return this;
    }

    public AboutUsVideoWindow clickPauseVideoButton(){
        Actions action = new Actions(getDriver());
        action.pause(PAUSE)
                .click(pauseVideoButton)
                .pause(PAUSE)
                .build()
                .perform();
        return this;
    }

    public AboutUsVideoWindow clickCloseButton(){
        closeButton.click();
        return this;
    }
}
