package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class MainSteps{

    private WebDriverWait wait;
    private WebDriver driver;
    // Tany
    private MainPage mainPage = new MainPage();

    public MainSteps(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }

    public void selectRuLanguage() {
        driver.findElement(mainPage.selectRuLanguage).click();
    }

    public void clickSearchBox() {
        driver.findElement(mainPage.search).click();
    }

    public void searchItemOfArticle(String searchArticle) {
        driver.findElement(mainPage.search).sendKeys(searchArticle);
    }

    public void clickOnSearchIcon() {
        driver.findElement(mainPage.searchIcon).click();
    }

    public String searchItemUrl(By search, String href) {
        return driver.findElement(search).getAttribute(href);
    }

    public void clickAddToCart() {
        driver.findElement(mainPage.clickBy).click();
    }

    public void chooseSizeItem() {
        driver.findElement(mainPage.alertSize).click();
    }
    public void chooseSizeItemTshort() {
        driver.findElement(mainPage.selectSizeButton).click();
    }

    public void waitInvisibleOfAlert() {
        wait.until(invisibilityOf(driver.findElement(mainPage.alertClose)));
    }

    public void goToCart() {
        driver.findElement(mainPage.addToCart).click();
    }

}
