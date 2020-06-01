package web.pages.trashmail.mainPage;

import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

public class NewUserTab extends InitializingPage {

    @FindBy(xpath = "//div[@id='tab-mob-register']//input[@ng-model='user.name']")
    public WebElement fieldUserName;

    @FindBy(xpath = "//div[@id='tab-mob-register']//input[@ng-model='user.password']")
    public WebElement fieldPassword;

    @FindBy(xpath = "//div[@id='tab-mob-register']//input[@ng-model='user.passwordRepeat']")
    public WebElement fieldConfirmPassword;

    @FindBy(xpath = "//div[@id='tab-mob-register']//input[@ng-model='user.email']")
    public WebElement fieldRealEmail;

    @FindBy(xpath = "//div[@id='tab-mob-register']//button[contains(text(), 'Register')]")
    private WebElement buttonRegister;


    public NewUserTab(WebDriver driver) {
        super(driver);
    }

    public void registerNewUser(){
        //clickFieldAndClear(fieldUserName);
        fieldUserName.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("LOGIN"));
        Timeout.seconds(2);
        //clickFieldAndClear(fieldPassword);
        fieldPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
        Timeout.seconds(2);
        //clickFieldAndClear(fieldConfirmPassword);
        fieldConfirmPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
        Timeout.seconds(2);
        //clickFieldAndClear(fieldRealEmail);
        fieldRealEmail.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("E-MAIL"));
        Timeout.seconds(2);
        clickRegisterButton();
        GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).setProperty("REGISTRATION", "true");
        Timeout.seconds(2);

    }

    public void clickFieldAndClear(WebElement element){
        element.click();
        element.clear();
    }

    public void clickRegisterButton(){
        buttonRegister.click();
    }


}
