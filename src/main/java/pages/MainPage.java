package pages;

import org.openqa.selenium.By;

public class MainPage {

    public final By selectRuLanguage = By.xpath("//a[@href='/lang/ru']");

    // Tany

    public final By search = By.xpath("//*[@class='search_input']");
    public final By searchIcon = By.xpath("//*[@class='search__btn']");
    public final By clickBy = By.xpath("//*[@class='w-full box-border t-center catalog__add-to-cart']");
    public final By addToCart = By.xpath("//*[@class='header_basket']");
    public final By alertSize = By.xpath("//button[@value='2423014060']");
    public final By alertClose = By.xpath("//div[@class='msg']//*[@ts-action='remove']"); // всплывающее окно, после нажатия кнопки купить


}
