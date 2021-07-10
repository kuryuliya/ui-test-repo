import static org.testng.Assert.assertTrue;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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
    private final By search = By.xpath("//div[@class='flex header__search-container']//form[@class='search']//input[@type='search']");
    String searchWord = "джинсы";
    private final By searchIcon = By.xpath("//button[@type='submit']");
    private final By clickBy = By.xpath("//*[@id='11649387:675']//*[text()='Купить']");
    private final By alertSize = By.xpath("//div[@class='popup__content size-list-popup']//*[text()='140см']");
    private final By addToCart = By.xpath("//a[@ts-swap-push='.header_basket']//*[@class='header_basket-title t-bold']");
    private final By productAddCart = By.xpath("//div[@class='cart_pd-info']//a[@href='/product/11649387:675/']");
    private final By productSearch = By.xpath("//div[@class='product__info_container']//a[@href='/product/11649387:675/']");


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
        String productSearchText = driver.findElement(productSearch).toString();
        System.out.println(productSearchText);
        driver.findElement(clickBy).click();
        driver.findElement(alertSize).click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(addToCart).click();
        String productAddCartText = driver.findElement(productAddCart).toString();
        System.out.println(productAddCartText);
        assertTrue(productAddCartText.contains("/product/11649387:675/") && productSearchText.contains("/product/11649387:675/"), "Items in the cart does not coincide with the selected");

    }

    @AfterSuite
    public void quitDriver() {
        driver.quit();
    }



}
