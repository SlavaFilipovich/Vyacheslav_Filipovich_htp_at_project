package web.pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.rules.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

public class RegistrationPages extends InitializingPage {

    @FindBy(xpath = "//input[@id = 'login_name_register']")
    private WebElement loginEmailField;

    @FindBy(xpath = "//input[@id = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@id = 'confirmed_password']")
    private WebElement confirmPasswordField;

    @FindBy(xpath = "//button[@type = 'submit']")
    private WebElement buttonRegister;

    private  static final Logger LOGGER = LogManager.getLogger(RegistrationPages.class);

    public RegistrationPages(WebDriver driver) {
        super(driver);
    }

    public void fillInLoginField(){
        LOGGER.debug("Filling in LoginField");
        loginEmailField.sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("E-MAIL"));
        buttonRegister.click();
    }

    public void fillInPasswordFields(){
        LOGGER.debug("Filling in PasswordField");
        passwordField.sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("PASSWORD"));
        LOGGER.debug("Filling in confirmation field");
        confirmPasswordField.sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("PASSWORD"));
        buttonRegister.click();
    }

    public void fillInPasswordField(){
        passwordField.sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("PASSWORD"));
        buttonRegister.click();
    }




}
