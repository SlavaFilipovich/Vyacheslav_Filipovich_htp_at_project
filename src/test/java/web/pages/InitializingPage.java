package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InitializingPage {
    public WebDriver driver;

    public InitializingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
