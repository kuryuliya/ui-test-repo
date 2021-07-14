package steps;

import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

public class BaseSteps {

    public final static String BASE_URL = "https://kasta.ua/";
    public final static String USERNAME = "yuliya.zp1@gmail.com";
    public final static String PASSWORD = "natasha0000";

    protected ChromeDriver driver;
    protected WebDriverWait wait;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));

        driver.get(BASE_URL);
    }

}
