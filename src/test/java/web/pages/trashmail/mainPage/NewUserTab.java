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

    private final Logger LOGGER = LogManager.getLogger(NewUserTab.class);

    public NewUserTab(WebDriver driver) {
        super(driver);
    }

    public void registerNewUser(){
        LOGGER.debug("Start registration of new user on Trashmail");
        LOGGER.debug("Filling in login...");
        fieldUserName.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("LOGIN"));
        Timeout.seconds(2);
        LOGGER.debug("Filling in password...");
        fieldPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
        Timeout.seconds(2);
        LOGGER.debug("Confirmation of password...");
        fieldConfirmPassword.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("PASSWORD"));
        Timeout.seconds(2);
        LOGGER.debug("Filling in E-mail...and click Register");
        fieldRealEmail.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("E-MAIL"));
        Timeout.seconds(2);
        clickRegisterButton();
        Timeout.seconds(2);
    }

    public void clickRegisterButton(){
        LOGGER.debug("Click on Register button");
        buttonRegister.click();
    }
}
