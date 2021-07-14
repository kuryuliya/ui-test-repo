import static org.testng.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
    private final By searchField = By.xpath("//input[@class='search_input']");
    private final By searchButton = By.xpath("//button[@class='search__btn']");
    private final By buyButton = By.xpath("//*[@style='order:0']//*[text()='Купить']");
    private final By selectSizeButton = By.xpath("//*[@class='size_list popup_size-list']//button[1]");
    private final By closeWindow = By.xpath("//div[@class='msg']//*[@ts-action='remove']");
    private final By basket = By.xpath("//*[@href='/basket/']");

    // Tany
    private final By search = By.xpath("//*[@class='search_input']");
    private final By searchIcon = By.xpath("//*[@class='search__btn']");
    private final By clickBy = By.xpath("//*[@class='w-full box-border t-center catalog__add-to-cart']");
    private final By alertSize = By.xpath("//button[@value='2423014060']");
    private final By addToCart = By.xpath("//*[@class='header_basket']");
    private final By productAddCart = By.xpath("//a[@href='/product/11649387:675/']");
    private final By productSearch = By.xpath("//a[@href='/product/11649387:675/']");
    private final By alertClose = By.xpath("//div[@class='msg']//*[@ts-action='remove']"); // всплывающее окно, после нажатия кнопки купить
    private WebDriverWait wait;

    @BeforeSuite
    public void setUpDriver() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
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
        assertEquals(driver.findElement(verificationLocatorEmail).getText(), USERNAME, "Email");

    }

    // Tany
    @Test
    public void addToCartTest() {

        var searchWord = "218631605";
        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(search).sendKeys(searchWord);
        driver.findElement(searchIcon).click();
        var productSearchText = driver.findElement(productSearch).getAttribute("href"); // получаем ссылку на товар
        driver.findElement(clickBy).click();
        driver.findElement(alertSize).click();
        wait.until(invisibilityOf(driver.findElement(alertClose)));
        driver.findElement(addToCart).click();
        var productAddCartText = driver.findElement(productAddCart).getAttribute("href"); // получаем ссылку на товар в корзине
        assertEquals(productAddCartText, productSearchText, "Items in the cart does not coincide with the selected"); // сравниваем  ссылки на товар

    }

    @Test
    public void checkUserBasketKasta() {

        var choisedItem = By.xpath("//*[@class='product__img']//a");
        var basketLinkItem = By.xpath("//*[@class='cart_pd-info']//a");


        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(searchField).sendKeys("220386025");
        driver.findElement(searchButton).click();
        var getAtributeChoiseLink = driver.findElement(choisedItem).getAttribute("href");
        driver.findElement(buyButton).click();
        driver.findElement(selectSizeButton).click();


        var wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(closeWindow)));


        driver.findElement(basket).click();


        var getAtributeBasketLink = driver.findElement(basketLinkItem).getAttribute("href");
        assertEquals(getAtributeBasketLink, getAtributeChoiseLink, "Tshort is not same as at the basket");
    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }
}
