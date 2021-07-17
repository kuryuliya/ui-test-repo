package steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;

public class LoginSteps{
    private WebDriver driver;
    private WebDriverWait wait;
    private LoginPage loginPage = new LoginPage();
    private MainPage mainPage =  new MainPage();

    public LoginSteps(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }


    public void selectRuLanguage() { driver.findElement(mainPage.selectRuLanguage).click();
    }

    public void clickOnSingInTab() { driver.findElement(loginPage.signInTab).click();
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
        driver.get(path);
    }

    public boolean isElementDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
    }

}
