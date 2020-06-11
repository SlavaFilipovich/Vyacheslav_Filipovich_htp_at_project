package web.pages.trashmail.mainPage;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;
import web.pages.booking.SearchResultsPage;

public class ManagerTab extends InitializingPage {

    @FindBy(xpath = "//div[@id='tab-mob-manager']//input[@ng-model='user.name']")
    public WebElement fieldUserName;

    @FindBy(xpath = "//div[@id='tab-mob-manager']//input[@ng-model='user.password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//div[@id='tab-mob-manager']//button[contains(text(), 'Sign in')]")
    private WebElement buttonSignIn;

    private static final Logger LOGGER = LogManager.getLogger(ManagerTab.class);

    public ManagerTab(WebDriver driver) {
        super(driver);
    }

    public void loginToTrashMail(){
        LOGGER.debug("Login to Trashmail.com");
        fieldUserName.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("LOGIN"));
        Timeout.seconds(2);
        //clickFieldAndClear(fieldPassword);
        fieldPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
        Timeout.seconds(2);
        clickSignInButton();
    }

    public void clickFieldAndClear(WebElement element){
        LOGGER.debug("Click on field and clear one");
        element.click();
        element.clear();
    }

    public void clickSignInButton(){
        LOGGER.debug("Click on SignIn button");
        buttonSignIn.click();
    }

}
