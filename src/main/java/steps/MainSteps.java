package steps;

import org.openqa.selenium.By;
import pages.MainPage;

import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOf;

public class MainSteps extends BaseSteps {

    // Tany
    private MainPage mainPage = new MainPage();

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
        String text = driver.findElement(search).getAttribute(href);
        return text;

    }

    public void clickAddToCart() {
        driver.findElement(mainPage.clickBy).click();
    }

    public void chooseSizeItem() {
        driver.findElement(mainPage.alertSize).click();
    }

    public void waitInvisibleOfAlert() {
        wait.until(invisibilityOf(driver.findElement(mainPage.alertClose)));
    }

    public void goToCart() {
        driver.findElement(mainPage.addToCart).click();
    }

}
