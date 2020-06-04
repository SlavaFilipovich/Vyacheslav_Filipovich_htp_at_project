package web.pages.trashmail.mainPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class QuickTab extends InitializingPage {

    @FindBy(xpath = "//a[@href='#tab-mob-register']")
    public WebElement tabNewUser;

    @FindBy(xpath = "//a[@href='#tab-mob-manager']")
    public WebElement tabAddressManager;

    @FindBy(xpath = "//a[@href='#tab-mob-quick']")
    public WebElement tabQuick;

    @FindBy(xpath = "//input[@id='fe-mob-name']")
    public WebElement fieldDisposeEmail;

    @FindBy(xpath = "//div[@id='tab-mob-quick']//input[@ng-model='user.email']")
    public WebElement fieldRealEmail;

    @FindBy(xpath = "//*[@id='fe-mob-fwd-nb']")
    public WebElement menuNumberOfForwards;

    @FindBy(xpath = "//*[@id='fe-mob-life-span']")
    public WebElement menuLifeSpan;

    @FindBy(xpath = "//select[@ng-model='selectedDomain']/option[@value ='trashmail.com']")
    public WebElement menuDomain;

    @FindBy(xpath = "//button[@id='fe-mob-submit']")
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
        menuNumberOfForwards.click();
        driver.findElement(By.xpath("//*[@id='fe-mob-fwd-nb']/option[contains(text(), '1')]")).click();
        menuLifeSpan.click();
        driver.findElement(By.xpath("//*[@id='fe-mob-life-span']/option[contains(text(), '1 day')]")).click();
    }

    public void clickCreateEmail(){
        buttonCreateDisposeEmail.click();
    }

    public void saveNameEmail() throws FileNotFoundException {
        Properties prop = GeneralUtils.getProperties(PathList.BOOKING_PROP);
        OutputStream out = new FileOutputStream(PathList.BOOKING_PROP);
        String value = driver.findElement(By.xpath("//*[contains(@value,'trash')]")).getAttribute("value");
        prop.put("E-MAIL", value);
        try {
            prop.store(out, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
