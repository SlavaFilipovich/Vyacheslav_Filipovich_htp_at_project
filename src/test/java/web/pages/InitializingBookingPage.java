package web.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class InitializingBookingPage {
    WebDriver driver;

    public InitializingBookingPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
