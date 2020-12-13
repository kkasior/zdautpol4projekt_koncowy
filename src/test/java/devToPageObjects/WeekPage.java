package devToPageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WeekPage {
    WebDriver driver;
    public String url = "https://dev.to/top/week";

    @FindBy(className = "crayons-story__title")
    public WebElement firstPost;

    public WeekPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectFirstPost(){
        firstPost.click();
    }
}
