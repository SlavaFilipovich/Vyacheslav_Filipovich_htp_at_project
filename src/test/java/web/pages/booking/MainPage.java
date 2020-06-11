package web.pages.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.InitializingPage;

import static utils.BookingUtils.*;


public class MainPage extends InitializingPage {

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

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class);

    public MainPage(WebDriver driver){
        super(driver);
    }

    public void navigateToBooking(){
        LOGGER.debug("Go to Booking.com");
        driver.get(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("ADDRESS"));
    }

    public void enterDesiredLocation(String place){
        LOGGER.debug("Click"+locationField.getText()+", clear field and send place");
        locationField.click();
        locationField.clear();
        locationField.sendKeys(place);
        locationField.click();
    }

    public void enterTripDurationFromTo(int afterDays, int duration) throws InterruptedException {
        LOGGER.debug("Set up duration of trip "+duration+ " in "+afterDays+" days");
        calendarMenu.click();
        WebElement dateFrom=driver.findElement(By.xpath(generateDateXPAth(afterDays)));
        Thread.sleep(3000);
        dateFrom.click();
        WebElement dateTo=driver.findElement(By.xpath(generateDateXPAth(duration+afterDays)));
        dateTo.click();
    }

    public void enterAdultsChildrenRooms(int adults, int children, int rooms){
        LOGGER.debug("Set up adults "+adults+ ", children "+children+" and rooms "+rooms);
        buttonSettingsTrip.click();
        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+adults+"')", fieldAdults);
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+children+"')", fieldChildren);
        jsDriver.executeScript("arguments[0].setAttribute('value', '"+rooms+"')", fieldRoom);
        buttonSettingsTrip.click();
    }

    public void clickSearchButton(){
        LOGGER.debug("Click search button to get result hotel-page");
        buttonSearch.click();
    }

    public void clickRegister(){
        LOGGER.debug("Click Registration button");
        buttonRegister.click();
    }

    public void clickSignIn(){
        LOGGER.debug("Click SignIn button");
        buttonSignIn.click();
    }








}
