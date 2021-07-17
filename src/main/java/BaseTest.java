import java.time.Duration;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import steps.LoginSteps;
import steps.MainSteps;

public class BaseTest {

    public final static String BASE_URL = "https://kasta.ua/";
    public final static String USERNAME = "yuliya.zp1@gmail.com";
    public final static String PASSWORD = "natasha0000";
    public ChromeDriver driver;
    public WebDriverWait wait;
    protected LoginSteps login;
    protected MainSteps main;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();

        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        login = new LoginSteps(driver, wait);
        main = new MainSteps(driver, wait);

        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));

        driver.get(BASE_URL);
    }


    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}
