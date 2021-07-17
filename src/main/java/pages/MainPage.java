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

    //Jeny

    public final By searchField = By.xpath("//input[@class='search_input']");
    public final By searchButton = By.xpath("//button[@class='search__btn']");
    public final By closeWindow = By.xpath("//div[@class='msg']//*[@ts-action='remove']");
    public final By buyButton = By.xpath("//*[@style='order:0']//*[text()='Купить']");
    public final By selectSizeButton = By.xpath("//*[@class='size_list popup_size-list']//button[1]");
    public final By basket = By.xpath("//*[@href='/basket/']");


    }


