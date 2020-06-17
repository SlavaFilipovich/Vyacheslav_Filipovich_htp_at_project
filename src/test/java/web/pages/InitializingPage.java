package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

public class InitializingPage {
    public WebDriver driver;
    public Actions actions;

    public InitializingPage(WebDriver driver){
        this.driver = driver;
        actions = new Actions(driver);
        PageFactory.initElements(this.driver, this);
    }
}
