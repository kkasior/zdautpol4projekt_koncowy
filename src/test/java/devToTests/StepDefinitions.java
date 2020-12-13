package devToTests;

import devToPageObjects.*;
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
    String firstPodcastLink;
    String firstPodcastTitle;

    MainPage mainPage;
    WeekPage weekPage;
    SinglePostPage singlePostPage;
    PodcastsPage podcastsPage;
    SinglePodcastPage singlePodcastPage;


    @Before
    public void setup(){
        driver = BaseDriver.setDriver();
        wait = new WebDriverWait(driver,10);
    }

    @Given("DevTo main page is open")
    public void dev_to_main_page_is_open() {
        mainPage = new MainPage(driver);
    }

    @When("User click week button")
    public void user_click_week_button() {
        mainPage.clickWeekButton();
    }
    @When("User select the first post")
    public void user_select_the_first_post() {
        weekPage = new WeekPage(driver);
        wait.until(ExpectedConditions.urlToBe(weekPage.url));

        firstPostLink = weekPage.firstPost.findElement(By.tagName("a")).getAttribute("href");
        firstPostTitleTextValue = weekPage.firstPost.getText();
        weekPage.selectFirstPost();
    }
    @When("User click podcasts button")
    public void user_click_podcasts_button() {
       mainPage.clickPodcastButton();
    }

    @When("User select the first podcast")
    public void user_select_the_first_podcast() {
        podcastsPage = new PodcastsPage(driver);
        wait.until(ExpectedConditions.urlToBe(podcastsPage.url));
        firstPodcastLink = driver.findElement(By.xpath("//div[@class = 'content']/parent::a")).getAttribute("href");
        firstPodcastTitle = podcastsPage.firstPodcast.getText();
        podcastsPage.selectFirstPodcast();
    }

    @Then("User should be able to see post content")
    public void user_should_be_able_to_see_post_content() {
        singlePostPage = new SinglePostPage(driver);
        wait.until(ExpectedConditions.urlToBe(firstPostLink));

        String postTitleTextValue = singlePostPage.postTitle.getText();
        assertTrue(postTitleTextValue.equals(firstPostTitleTextValue));
    }
    @Then("User should be able to see podcast content")
    public void user_should_be_able_to_see_podcast_content() {
        singlePodcastPage = new SinglePodcastPage(driver);
        wait.until(ExpectedConditions.urlToBe(firstPodcastLink));
        String podcastTitle = singlePodcastPage.podcastTitle.getText();
        assertTrue(firstPodcastTitle.contains(podcastTitle));
    }

    @Then("User can play podcast")
    public void user_can_play_podcast() {
        singlePodcastPage.playPodcast();
        wait.until(ExpectedConditions.invisibilityOf(singlePodcastPage.initializingMessage));
        String recordWrapperClass = singlePodcastPage.recordWrapper.getAttribute("class");
        Boolean isPodcastPlaying = recordWrapperClass.contains("playing");
        assertTrue(isPodcastPlaying);
    }

    @Then("User can pause podcast")
    public void user_can_pause_podcast() {
        singlePodcastPage.pausePodcast();
        assertTrue(singlePodcastPage.GetPausedStatus());
    }
}
