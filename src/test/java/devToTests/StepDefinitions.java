package devToTests;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testingUtils.BaseDriver;

import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    WebDriver driver;
    WebDriverWait wait;
    String firstPostLink;
    String firstPostTitleTextValue;

    @Before
    public void setup(){
        driver = BaseDriver.setDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Given("DevTo main page is open")
    public void dev_to_main_page_is_open() {
        driver.get("https://dev.to");
    }

    @When("User click week button")
    public void user_click_week_button() {
        WebElement weekBtn = driver.findElement(By.xpath("//a[@href='/top/week']"));
        weekBtn.click();
    }
    @When("User select the first post")
    public void user_select_the_first_post() {
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
        WebElement firstPost = driver.findElement(By.cssSelector(".crayons-story__title > a:first-child"));
        firstPostLink = firstPost.getAttribute("href");
        WebElement firstPostTitle = driver.findElement(By.className("crayons-story__title"));
        firstPostTitleTextValue = firstPostTitle.getText();
        firstPostTitle.click();
    }
    @When("User click podcasts button")
    public void user_click_podcasts_button() {
       WebElement podcastsBtn = driver.findElement(By.partialLinkText("Podcasts"));
       podcastsBtn.click();
    }

    @When("User select the first podcast")
    public void user_select_the_first_podcast() {
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement firstPodcast = driver.findElement(By.className("content"));
        firstPodcast.click();
    }

    @Then("User should be able to see post content")
    public void user_should_be_able_to_see_post_content() {
        wait.until(ExpectedConditions.urlToBe(firstPostLink));
        WebElement postTitle = driver.findElement(By.tagName("h1"));
        String postTitleTextValue = postTitle.getText();
        assertTrue(postTitleTextValue.equals(firstPostTitleTextValue));
    }
}
