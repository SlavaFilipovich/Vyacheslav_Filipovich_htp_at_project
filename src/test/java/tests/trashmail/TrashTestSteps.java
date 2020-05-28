package tests.trashmail;

import org.junit.Test;
import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.trashmail.mainPage.ManagerTab;
import web.pages.trashmail.mainPage.NewUserTab;
import web.pages.trashmail.mainPage.QuickTab;


public class TrashTestSteps {
    private WebDriver driver;
    private NewUserTab newUser;
    private QuickTab quickTab;
    private ManagerTab managerTab;


    public TrashTestSteps(WebDriver driver){
        this.driver = driver;
        newUser = new NewUserTab(driver);
        quickTab = new QuickTab(driver);
        managerTab = new ManagerTab(driver);
    }

    @Test
    public void getNewTemporaryEmail(){
        quickTab.navigateToSite();
        if(GeneralUtils.getProperties(PathList.TRASHMAIL_PROP).getProperty("REGISTRATION").equals("true")){
            managerTab.loginToTrashMail();
            quickTab.saveNameEmail();
            quickTab.setForwardsAndLifespan();
            quickTab.enterRealEmail();
            quickTab.clickCreateEmail();
            Timeout.seconds(5);

        }
        else {
            newUser.registerNewUser();
            getNewTemporaryEmail();
        }

    }

    public void checkLetterForRegistration(String sender){
        driver.navigate().to(GeneralUtils.getProperties(PathList.MY_EMAIL_PROP).getProperty("ADDRESS"));
        if(!driver.findElement(By.xpath("//a[@data-statlog='mail.login.usermenu.exit']")).isDisplayed()){
            loginToEmail();
        }
        else{
            driver.findElements(By.xpath("//a[@href='https://mail.yandex.by/']")).get(0).click();
            GeneralUtils.waitVisibilityOfElement(driver, "//a[@data-title = 'Входящие']");

        }


    }

    private void loginToEmail() {

    }


}
