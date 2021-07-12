import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    private final By typeTshort = By.xpath("//div[@class='flex header__search-container']//form[@class='search']//input[@type='search']");
    private final By pressSerch = By.xpath("//button[@type='submit']");
    private final By pressBuy = By.xpath("//*[@style='order:0']//*[text()='Купить']");
    private final By selectsize = By.xpath("//*[@class='size_list popup_size-list']//button[1]");
    private final By closeWindow = By.xpath("//div[@class='msg']//*[@ts-action='remove']");
    private final By basket = By.xpath("//*[@href='/basket/']");

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
    public void checkUserBasketKasta() {

        var ChoiseLink =By.xpath("//*[@class='product__img']//a");
        var BasketLink = By.xpath("//*[@class='cart_pd-info']//a");


        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(typeTshort).sendKeys("220386025");
        driver.findElement(pressSerch).click();
        var GetAtributeChoiseLink =driver.findElement(ChoiseLink).getAttribute("href");
        driver.findElement(pressBuy).click();
        driver.findElement(selectsize).click();

        WebElement wait = driver.findElement(closeWindow);
new WebDriverWait(driver, Duration.ofSeconds(6)).until(ExpectedConditions.invisibilityOf(wait));

        driver.findElement(basket).click();

        var GetAtributeBasketLink = driver.findElement(BasketLink).getAttribute("href");
    assertEquals(GetAtributeBasketLink,GetAtributeChoiseLink, "Tshort is not same as at the basket");
    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}
