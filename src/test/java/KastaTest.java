import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class KastaTest {

    // Host and credentials
    private final static String BASE_URL = "https://kasta.ua/";
    private final static String USERNAME = "yuliya.zp1@gmail.com";
    private final static String PASSWORD = "natasha0000";
    //    Locators
    private final By selectRuLanguage = By.xpath("//a[@href='/lang/ru']");
    private final By signInTab = By.xpath("//*[@class='header_profile top_menu_item']");
    private final By loginField = By.name("email");
    private final By passwordField = By.name("password");
    private final By nextBtn = By.xpath("//button[contains(text(),'Войти')]");
    private ChromeDriver driver;

    //Jeny
    private final By Type_tshort = By.xpath("//div[@class='flex header__search-container']//form[@class='search']//input[@type='search']");
    private final By Press_serch = By.xpath("//button[@type='submit']");
    private final By Press_Buy = By.xpath("//*[@style='order:0']//*[text()='Купить']");
    private final By Select_size = By.xpath("//*[@class='size_list popup_size-list']//button[1]");

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(30000));
    }

    @Test
    public void authorizeByValidUserToKasta() {
        var verificationLocator = By.xpath("//*[contains(text(),'Курницкя Юлия')]");

        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(signInTab).click();
        driver.findElement(loginField).sendKeys(USERNAME);
        driver.findElement(nextBtn).click();
        driver.findElement(passwordField).sendKeys(PASSWORD);
        driver.findElement(nextBtn).click();

        driver.get(BASE_URL + "me");

        assertTrue(driver.findElement(verificationLocator).isDisplayed(), "An user is unauthorized");
    }

    @Test
    public void authorizeByInvalidUserToKasta() {
        var verificationLocatorEmail = By.xpath("//div[@class='auth_title']//*[@class='email']");
        var verificationErrorMessage = By.xpath("//div[@class='auth_title']");


        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(signInTab).click();
        driver.findElement(loginField).sendKeys(USERNAME);
        driver.findElement(nextBtn).click();
        driver.findElement(passwordField).sendKeys("csedcsf");
        driver.findElement(nextBtn).click();

        //assertEquals(driver.findElement(verificationErrorMessage).getText(),"Неверно указан пароль для", "Error");
        assertEquals(driver.findElement(verificationLocatorEmail).getText(),USERNAME, "Email");

    }

    @Test
    public void CheckUserBasketKasta() {

     var verificationLocatorByTshort = By.xpath("//*[@style='order:0']//div[@class='product__img']//a[@href='/product/11716758:584/']");

        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(Type_tshort).sendKeys("Футболка");
        driver.findElement(Press_serch).click();

      //var Check= driver.findElement(verificationLocatorByTshort).toString(); //падает тут т.к. меняется xpach

        driver.findElement(Press_Buy).click();
        driver.findElement(Select_size).click();


//        WebElement element = (new WebDriverWait(driver, Duration.ofSeconds(100)))
//            .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@href='/basket/']//*[text()='Корзина']")));


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//*[@href='/basket/']//*[text()='Корзина']")).click();


//      var verificationLocatorByTshort2 = By.xpath("//*[@class='cart_item_descr']//a");//xpath того что в карзине
//      var Check2 = driver.findElement(verificationLocatorByTshort2).toString();


//      assertTrue(Check2.contains("gdgd") && Check.contains("gdgd") ,"Tshirt is not match");

    }




    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}
