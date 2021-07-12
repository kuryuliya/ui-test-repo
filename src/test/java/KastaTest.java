import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

    // Tany
    private final By search = By.xpath("//*[@class='search_input']");
    String searchWord = "218631605";
    private final By searchIcon = By.xpath("//*[@class='search__btn']");
    private final By clickBy = By.xpath("//*[@class='w-full box-border t-center catalog__add-to-cart']");
    private final By alertSize = By.xpath("//button[@value='2423014060']");
    private final By addToCart = By.xpath("//*[@class='header_basket']");
    private final By productAddCart = By.xpath("//div[@class='cart_pd-info']//a[@href='/product/11649387:675/']");
    private final By productSearch = By.xpath("//div[@class='product__info_container']//a[@href='/product/11649387:675/']");
    private final By alertClose = By.xpath("//div[@class='msg']//*[@ts-action='remove']"); // всплывающее окно, после нажатия кнопки купить





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

    // Tany
    @Test
    public void addToCartTest() {

        driver.get(BASE_URL);
        driver.findElement(selectRuLanguage).click();
        driver.findElement(search).sendKeys(searchWord);
        driver.findElement(searchIcon).click();
        var productSearchText = driver.findElement(productSearch).getAttribute("href"); // получаем ссылку на товар
        System.out.println(productSearchText);
        driver.findElement(clickBy).click();
        driver.findElement(alertSize).click();
//      driver.findElement(alertClose).click();  // закрываем всплывающее окно

        var alert  = driver.findElement(alertClose); // создаем элемент с переменной alertClose

        new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.invisibilityOf(alert)); // ожидание пока не скроется элемент "всплывающее окно"

        driver.findElement(addToCart).click();
        var productAddCartText = driver.findElement(productAddCart).getAttribute("href"); // получаем ссылку на товар в корзине
        System.out.println(productAddCartText);
        assertEquals(productAddCartText, productSearchText, "Items in the cart does not coincide with the selected"); // сравниваем  ссылки на товар

    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }



}
