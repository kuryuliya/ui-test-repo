package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

public class BasketSteps {
    private WebDriverWait wait;
    private WebDriver driver;
    // Tany
    private MainPage mainPage = new MainPage();


    public BasketSteps(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = wait;
    }


}
