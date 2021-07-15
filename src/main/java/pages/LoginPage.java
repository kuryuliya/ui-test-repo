package pages;

import org.openqa.selenium.By;

public class LoginPage {
    public final By selectRuLanguage = By.xpath("//a[@href='/lang/ru']");
    public final By signInTab = By.xpath("//*[@class='header_profile top_menu_item']");
    public final By loginField = By.name("email");
    public final By passwordField = By.name("password");
    public final By nextBtn = By.xpath("//button[contains(text(),'Войти')]");
}
