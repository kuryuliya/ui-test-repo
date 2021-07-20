import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import steps.MainSteps;

public class KastaTest extends BaseTest {



    @Test
    public void authorizeByValidUserToKasta() {

        var verificationLocator = By.xpath("//*[contains(text(),'Курницкя Юлия')]");

        login.selectRuLanguage();
        login.clickOnSingInTab();
        login.typeUsername(USERNAME);
        login.clickNextButton();
        login.typePassword(PASSWORD);
        login.clickNextButton();

        login.goTo(BASE_URL + "me");

        assertTrue(login.isElementDisplayed(verificationLocator), "An user is unauthorized");
    }

//    @Test
//    public void authorizeByInvalidUserToKasta() {
//        var verificationLocatorEmail = By.xpath("//div[@class='auth_title']//*[@class='email']");
//        var verificationErrorMessage = By.xpath("//div[@class='auth_title']");
//
//        driver.get(BASE_URL);
//        driver.findElement(selectRuLanguage).click();
//        driver.findElement(signInTab).click();
//        driver.findElement(loginField).sendKeys(USERNAME);
//        driver.findElement(nextBtn).click();
//        driver.findElement(passwordField).sendKeys("csedcsf");
//        driver.findElement(nextBtn).click();
//
//        //assertEquals(driver.findElement(verificationErrorMessage).getText(),"Неверно указан пароль для", "Error");
//        assertEquals(driver.findElement(verificationLocatorEmail).getText(), USERNAME, "Email");
//
//    }

    // Tany
    @Test
    public void addToCartTest() {

        var searchArticle = "218631605";
        var productAddCart = By.xpath("//a[@href='/product/11649387:675/']");
        var productSearch = By.xpath("//a[@href='/product/11649387:675/']");
        var attribute = "href";


        main.selectRuLanguage();
        main.clickSearchBox();
        main.searchItemOfArticle(searchArticle);
        main.clickOnSearchIcon();

        var productSearchText = main.searchItemUrl(productSearch, attribute);
        main.clickAddToCart();
        main.chooseSizeItem();
        main.waitInvisibleOfAlert();
        main.goToCart();
        var productAddCartText = main.searchItemUrl(productAddCart, attribute);

        assertEquals(productAddCartText, productSearchText,
                "Items in the cart does not coincide with the selected"); // сравниваем  ссылки на товар
    }

    @Test
    public void checkUserBasketKasta() {

        var searchArticle = "220386025";
        var productSearch = By.xpath("//*[@class='product__img']//a");
        var productAddCart = By.xpath("//*[@class='cart_pd-info']//a");
        var attribute = "href";

        main.selectRuLanguage();
        main.clickSearchBox();
        main.searchItemOfArticle(searchArticle);
        main.clickOnSearchIcon();

        var productSearchText = main.searchItemUrl(productSearch, attribute);

        main.clickAddToCart();
        main.chooseSizeItem();
        main.waitInvisibleOfAlert();
        main.goToCart();

        var productAddCartText = main.searchItemUrl(productAddCart, attribute);

       assertEquals(productSearchText, productAddCartText, "Tshort is not same as at the basket");
   }

}
