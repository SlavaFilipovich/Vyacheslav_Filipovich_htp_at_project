package web.pages.trashmail.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

public class ManagerTab extends InitializingPage {

    @FindBy(xpath = "//div[@id='tab-mob-manager']//input[@ng-model='user.name']")
    public WebElement fieldUserName;

    @FindBy(xpath = "//div[@id='tab-mob-manager']//input[@ng-model='user.password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//div[@id='tab-mob-manager']//button[contains(text(), 'Sign in')]")
    private WebElement buttonSignIn;

    public ManagerTab(WebDriver driver) {
        super(driver);
    }

    public void loginToTrashMail(){
        clickFieldAndClear(fieldUserName);
        fieldUserName.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("LOGIN"));
        clickFieldAndClear(fieldPassword);
        fieldPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
    }

    public void clickFieldAndClear(WebElement element){
        element.click();
        element.clear();
    }

    public void clickSignInButton(){
        buttonSignIn.click();
    }

}
