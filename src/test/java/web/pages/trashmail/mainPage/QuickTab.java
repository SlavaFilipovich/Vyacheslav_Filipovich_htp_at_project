package web.pages.trashmail.mainPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

public class QuickTab extends InitializingPage {

    @FindBy(xpath = "//a[@href='#tab-mob-register']")
    private WebElement tabNewUser;

    @FindBy(xpath = "//a[@href='#tab-mob-manager']")
    private WebElement tabAddressManager;

    @FindBy(xpath = "//a[@href='#tab-mob-quick']")
    private WebElement tabQuick;

    @FindBy(xpath = "//input[@id='fe-mob-name']")
    private WebElement fieldDisposeEmail;

    @FindBy(xpath = "//div[@id='tab-mob-quick']//input[@ng-model='user.email']")
    public WebElement fieldRealEmail;

    @FindBy(xpath = "//div[@id='tab-mob-quick']//select[@ng-model='selectedForward']")
    public WebElement menuNumberOfForwards;

    @FindBy(xpath = "//div[@id='tab-mob-quick']//select[@ng-model='selectedLifespan']")
    public WebElement menuLifeSpan;

    @FindBy(xpath = "//select[@ng-model='selectedDomain']/option[@value ='trashmail.com']")
    public WebElement menuDomain;

    @FindBy(xpath = "//div[@id='tab-mob-quick']//select[@ng-model='selectedLifespan']")
    public WebElement buttonCreateDisposeEmail;


    public QuickTab(WebDriver driver) {
        super(driver);
    }

    public void navigateToSite(){
        driver.get(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("ADDRESS"));
    }

    public void enterRealEmail(){
        fieldRealEmail.sendKeys(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("E-MAIL"));
    }

    public void setForwardsAndLifespan(){
        menuNumberOfForwards.sendKeys("1");
        menuLifeSpan.sendKeys("1 day");
    }

    public void clickCreateEmail(){
        buttonCreateDisposeEmail.click();
    }

    public void saveNameEmail(){
        GeneralUtils.getProperties(PathList.BOOKING_PROP).setProperty("E-MAIL",
                (fieldDisposeEmail.getAttribute("value").concat(menuDomain.getAttribute("value"))));
    }


}
