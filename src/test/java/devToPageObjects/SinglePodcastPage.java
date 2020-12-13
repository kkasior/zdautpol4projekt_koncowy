package devToPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SinglePodcastPage {

    WebDriver driver;

    @FindBy(tagName = "h1")
    public WebElement podcastTitle;
    @FindBy(id = "record")
    WebElement playBtn;
    @FindBy(className = "record-wrapper")
    public WebElement recordWrapper;
    @FindBy(xpath = "//*[@class='butt pause-butt']")
    WebElement pauseButton;
    @FindBy(className = "status-message")
    public WebElement initializingMessage;

    public SinglePodcastPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void playPodcast() {
        playBtn.click();
    }

    public boolean GetPausedStatus() {
        return pauseButton.isEnabled();
    }

    public void pausePodcast() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(pauseButton));
        pauseButton.click();
    }
}
