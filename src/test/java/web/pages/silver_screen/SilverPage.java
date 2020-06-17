package web.pages.silver_screen;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

import java.util.List;

public class SilverPage extends InitializingPage {

    @FindBy(xpath = "//*[@placeholder = 'Поиск']")
    private WebElement searchingField;

    @FindBy(xpath = "//button[@type = 'reset' and @opacity]")
    private WebElement buttonSearch;

    @FindBy(xpath = "//input[@type = 'email']")
    private WebElement loginField;

    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passwordField;

    @FindBy(xpath = "//*[contains(text(), 'Войти')]")
    private WebElement sigInButton;

    @FindBy(xpath = "//button[@type = 'reset']")
    private List<WebElement> resetButtons;

    @FindBy(xpath = "//div[@class = 'sc-kXeGPI llPrHk']")
    private WebElement redCarpetButton;


    private static final Logger LOGGER = LogManager.getLogger(SilverPage.class);

    public SilverPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSilverScreen(){
        LOGGER.debug("I go to silverscreen.by");
        driver.get("https://silverscreen.by");
    }

    public void searchFilms(String word) throws InterruptedException {
        LOGGER.debug("Searching films by name...");
        actions.moveToElement(resetButtons.get(1))
                .moveToElement(searchingField)
                .click()
                .sendKeys(word)
                .sendKeys(Keys.ENTER)
                .build().perform();
        Thread.sleep(3000);
    }

    public void fillInLoginForSite(){
        LOGGER.debug("Filling in LoginField...");
        actions.moveToElement(redCarpetButton).perform();
        loginField.sendKeys(GeneralUtils.getProperties(PathList.SILVER_PROP).getProperty("LOGIN"));
    }

    public void fillInPasswordForSite(){
        LOGGER.debug("Filling in PasswordField...");
        actions.moveToElement(redCarpetButton).perform();
        passwordField.sendKeys(GeneralUtils.getProperties(PathList.SILVER_PROP).getProperty("PASSWORD"));
    }

    public void clickSignIn(){
        LOGGER.debug("Click Sign in...");
        sigInButton.click();
    }


    public void loginToSite(String login, String password) {
        LOGGER.debug("Login to site...");
        actions.moveToElement(redCarpetButton).perform();
        loginField.sendKeys(login);
        passwordField.sendKeys(password);
    }
}
