package tests.test_steps;

import org.junit.rules.Timeout;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.GeneralUtils;
import utils.PathList;
import web.driver.Driver;
import web.pages.trashmail.mainPage.ManagerTab;
import web.pages.trashmail.mainPage.NewUserTab;
import web.pages.trashmail.mainPage.QuickTab;

import java.io.FileNotFoundException;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class TrashTestSteps {
    private  WebDriver driver;
    private NewUserTab newUser;
    private QuickTab quickTab;
    private ManagerTab managerTab;
    private Boolean registration = false;

    public static final String ERROR_USER_EXISTS = "//*[contains(text(),'has already been taken')]";
    public static final String ERROR_NO_REGISTRATION = "//*[contains(text(),'address is not registered')]";
    public static final String SIGN_UP_XPATH = "//a[contains(@class, 'HeadBanner-Button-Enter')]";
    public static final String LOGIN_FIELD_ID = "passp-field-login";
    public static final String PASSWORD_FIELD_ID = "passp-field-passwd";
    public static final String SUBMIT_XPATH = "//button[@type = 'submit']";


    public TrashTestSteps(WebDriver driver){
        this.driver = driver;
        newUser = new NewUserTab(driver);
        quickTab = new QuickTab(driver);
        managerTab = new ManagerTab(driver);
    }

    public void getNewTemporaryEmail(WebDriver driver) throws InterruptedException, FileNotFoundException {
        driver.get("https://trashmail.com");
        getNewEmail();
        if (driver.findElements(By.xpath(ERROR_NO_REGISTRATION)).size() > 0)
        {
            quickTab.tabNewUser.click();
            newUser.registerNewUser();
            checkLetterForRegistration("TrashMail.com");
            if(driver.findElements(By.xpath(ERROR_USER_EXISTS)).size() > 0){
                    //registration = true;
                quickTab.tabQuick.click();
                getNewEmail();
            }
        }
        Timeout.seconds(3);
    }

    public void getNewEmail() throws InterruptedException, FileNotFoundException {
        quickTab.setForwardsAndLifespan();
        quickTab.enterRealEmail();
        quickTab.clickCreateEmail();
        Thread.sleep(3);
        quickTab.saveNameEmail();
    }

    public void checkLetterForRegistration(String sender) throws InterruptedException {
        driver.navigate().to(GeneralUtils.getProperties(PathList.MY_EMAIL_PROP).getProperty("ADDRESS"));
        Thread.sleep(3000);//if(!(driver.findElement(By.xpath("//a[@data-statlog='mail.login.usermenu.exit']")).isDisplayed())){
        loginToEmail();
        WebElement letter = driver.findElement(By.xpath(String.format("//*[contains(text(), '%s')]", sender)));
        Driver.navigateToElementAndClick(driver,letter);
        //Driver.scrollToElementAndClick(driver,letter);
        TimeUnit.SECONDS.sleep(3);
    }

    private void loginToEmail() throws InterruptedException {
        driver.findElement(By.xpath(SIGN_UP_XPATH)).click();
        driver.findElement(By.id(LOGIN_FIELD_ID)).sendKeys(GeneralUtils.getProperties(PathList.MY_EMAIL_PROP).getProperty("LOGIN"));
        driver.findElement(By.xpath(SUBMIT_XPATH)).click();
        TimeUnit.SECONDS.sleep(3);
        driver.findElement(By.id(PASSWORD_FIELD_ID)).sendKeys(GeneralUtils.getProperties(PathList.MY_EMAIL_PROP).getProperty("PASSWORD"));
        driver.findElement(By.xpath(SUBMIT_XPATH)).click();
        TimeUnit.SECONDS.sleep(3);
    }



}