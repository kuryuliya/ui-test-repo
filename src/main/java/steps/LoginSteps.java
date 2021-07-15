package steps;

import org.openqa.selenium.By;
import pages.LoginPage;

public class LoginSteps extends BaseSteps {

    private LoginPage loginPage = new LoginPage();

    public void selectRuLanguage() {
        driver.findElement(loginPage.selectRuLanguage).click();
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

    public boolean isElementDisplayed(By xpath){

    }
}
