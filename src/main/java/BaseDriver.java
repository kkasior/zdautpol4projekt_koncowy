import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDriver {
    public static WebDriver setDriver(){
        System.setProperty("webdriver.chrome.driver","chromedriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }
}
