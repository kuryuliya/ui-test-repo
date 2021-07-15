package steps;

import org.openqa.selenium.By;
import pages.LoginPage;
import pages.MainPage;

public class LoginSteps extends BaseSteps {

    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage = new MainPage();

    public void selectRuLanguage() { driver.findElement(mainPage.selectRuLanguage).click();
    }

    public void clickOnSingInTab() {
        driver.findElement(loginPage.signInTab).click();
    }

    public void typeUsername(String username) {
        driver.findElement(loginPage.loginField).sendKeys(username);
    }

    public void clickNextButton() {
        driver.findElement(loginPage.nextBtn).click();
    }

    public void typePassword(String password) {
        driver.findElement(loginPage.passwordField).sendKeys(password);
    }

    public void goTo(String path) {
        driver.get(BASE_URL + "me");
    }



    public boolean isElementDisplayed(By locator) {
        driver.findElement(locator).click();
        return false;
    }

}
