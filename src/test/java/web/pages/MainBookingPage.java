package web.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static actions.BookingUtils.*;


public class MainBookingPage extends InitializingBookingPage{

    @FindBy(id = "current_account_create")
    private WebElement buttonRegister;

    @FindBy(id = "current_account")
    private WebElement buttonSignIn;

    @FindBy(id = "ss")
    private WebElement locationField;

    @FindBy(xpath = "//*[@data-mode='checkin']")
    private WebElement calendarMenu;

    @FindBy(xpath = "//label[@class='xp__input']")
    private WebElement buttonSettingsTrip;

    @FindBy(id = "group_adults")
    private WebElement fieldAdults;

    @FindBy(id = "no_rooms")
    private WebElement fieldRoom;

    @FindBy(id = "group_children")
    private WebElement fieldChildren;

    @FindBy(xpath = "//button[@data-sb-id='main']")
    private WebElement buttonSearch;


    public MainBookingPage(WebDriver driver){
        super(driver);
    }

    public void enterDesiredLocation(String place){
        locationField.click();
        locationField.clear();
        locationField.sendKeys(place);
        locationField.click();
    }

    public void enterTripDurationFromTo(int afterDays, int duration) throws InterruptedException {
        calendarMenu.click();
        WebElement dateFrom=driver.findElement(By.xpath(generateDateXPAth(afterDays)));
        Thread.sleep(3000);
        dateFrom.click();
        WebElement dateTo=driver.findElement(By.xpath(generateDateXPAth(duration)));
        dateTo.click();
    }

    public void enterAdultsChildrenRooms(int adults, int children, int rooms){
        buttonSettingsTrip.click();
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+adults+"')", fieldAdults);
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+children+"')", fieldChildren);
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+rooms+"')", fieldRoom);
        buttonSettingsTrip.click();
    }

    public void clickSearchButton(){
        buttonSearch.click();
    }

    public void clickRegister(){
        buttonRegister.click();
    }

    public void clickSignIn(){
        buttonSignIn.click();
    }








}
