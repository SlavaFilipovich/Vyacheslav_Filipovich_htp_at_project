package steps.junit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.GeneralUtils;
import utils.LoggerList;
import utils.PathList;
import web.pages.booking.MainPage;
import web.pages.booking.RegistrationPages;
import web.pages.booking.SearchResultsPage;

public class BookingTestSteps {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPages registrationPages;
    private SearchResultsPage searchResultsPage;
    private static String firstHotel;
    private static String secondHotel;
    private WebElement hotelInList;

    private static final String HOTEL_ELEMENT_XPATH = "//*[@data-hotelid][%d]";
    private static final String HOTEL_TEXT_ELEMENT_XPATH = "//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]";
    private static final String YOUR_ACCOUNT_XPATH = "//*[@id='profile-menu-trigger--content']//ancestor::a";
    private static final String MY_DASHBOARD_XPATH = "//*[@class='profile-menu__item profile_menu__item--mydashboard']";
    private static final String MY_WISHLIST_XPATH = "//*[@class='profile-menu__item profile_menu__item--wishlists']";
    private static final String ALERT_MESSAGE_XPATH = "//*[@class='email-confirm-banner']";
    private static final String LOGIN_XPATH = "//input[@id = 'username']";
    private static final String PASSWORD_XPATH = "//input[@id = 'password']";
    private static final String SUBMIT_BUTTON_XPATH = "//button[@type = 'submit']";
    private static final String hotelElementHeart = "//*[@data-hotelid][%d]//button";
    private static String hotelTextElementXpath = "//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]";
    private static final String HOTEL_LIST_XPATH = "//*[@id='hotellist_inner']/div";

    private static final Logger LOGGER = LogManager.getLogger(BookingTestSteps.class);

    public BookingTestSteps(WebDriver driver){
        this.driver = driver;
        mainPage = new MainPage(driver);
        registrationPages = new RegistrationPages(driver);
        searchResultsPage = new SearchResultsPage(driver);

    }

    public void addHotelsToFavouriteList() throws InterruptedException {
        //List<WebElement> list = driver.findElements(By.xpath(HOTEL_LIST_XPATH));
        LOGGER.debug("Looking for heart from first hotel and click...");
        WebElement firstHotelHeart = driver.findElement(By.xpath(String.format(hotelElementHeart, 1)));
        firstHotel = firstHotelHeart.getAttribute("data-hotel-id");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", firstHotelHeart);
        firstHotelHeart.click();
        Thread.sleep(2000);
        LOGGER.debug("Looking for heart from last hotel and click...");
        WebElement lastHotelHeart = driver.findElement(By.xpath(String.format(hotelElementHeart, searchResultsPage.listOfHotels.size()-1)));
        secondHotel = lastHotelHeart.getAttribute("data-hotel-id");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", lastHotelHeart);
        lastHotelHeart.click();
        Thread.sleep(2000);
    }

    public void colorOfHeartsShouldBeRed(){
        LOGGER.debug("Checking color of clicked hearts...");
        WebElement firstHotelHeart = driver.findElement(By.xpath(String.format(hotelElementHeart, 1)));
        WebElement lastHotelHeart = driver.findElement(By.xpath(String.format(hotelElementHeart, searchResultsPage.listOfHotels.size()-1)));
        Assert.assertEquals("rgb(204, 0, 0)", firstHotelHeart.getCssValue("fill"));
        Assert.assertEquals("rgb(204, 0, 0)", lastHotelHeart.getCssValue("fill"));
        LOGGER.debug("Colors are correct");
    }

    public void itemsShouldBeAppearedInMyNextTrip(){
        LOGGER.debug("Checking appearing marked hotels in the list");
        goToMyWishLists();
        hotelInList = driver.findElement(By.xpath("//*[contains(@data-index,'0')]/div"));
        Assert.assertEquals(firstHotel, hotelInList.getAttribute("data-id"));
        hotelInList = driver.findElement(By.xpath("//*[contains(@data-index,'1')]/div"));
        Assert.assertEquals(secondHotel, hotelInList.getAttribute("data-id"));
        LOGGER.debug("Hotels are in the list");
    }

    public void goToMyWishLists(){
        LOGGER.debug("Go to My Wish List");
        driver.findElement(By.xpath(YOUR_ACCOUNT_XPATH)).click();
        driver.findElement(By.xpath(MY_WISHLIST_XPATH)).click();
    }

    public void goToBookingDashboard() {
        LOGGER.debug("Go My Dashboard");
        driver.findElement(By.xpath(YOUR_ACCOUNT_XPATH)).click();
        driver.findElement(By.xpath(MY_DASHBOARD_XPATH)).click();
    }

    public void registerAccountBooking() throws InterruptedException {
        LOGGER.debug("Registration account...");
        mainPage.clickRegister();
        registrationPages.fillInLoginField();
        registrationPages.fillInPasswordFields();
        Thread.sleep(3000);
        LOGGER.debug("Registration is finished");
    }

    public void signInBooking() throws InterruptedException {
        LOGGER.debug("SignIn to account...");
        mainPage.clickSignIn();
        driver.findElement(By.xpath(LOGIN_XPATH)).sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("E-MAIL"));
        driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)).click();
        driver.findElement(By.xpath(PASSWORD_XPATH)).sendKeys(GeneralUtils.getProperties(PathList.BOOKING_PROP).getProperty("PASSWORD"));
        driver.findElement(By.xpath(SUBMIT_BUTTON_XPATH)).click();
        Thread.sleep(3000);
        LOGGER.debug("Logging in account is finished");
    }

    public void priceFirstShouldBeGreaterThanSecond(double checkBox, double listOfHotels){
        if(checkBox>listOfHotels) {
            Assert.assertTrue("Something wrong", checkBox>listOfHotels);
        }
        LOGGER.debug(String.format("Price of HotelRoom per night: %s BYN\r\n MaxBudget: %s BYN",listOfHotels,checkBox));
    }

    public void valueOfAttributesShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelElement = driver.findElement(By.xpath(String.format(HOTEL_ELEMENT_XPATH, numberOfHotel)));
        Assert.assertEquals("Change of backgroundColor doesn't work",expected, hotelElement.getAttribute("style"));
        LOGGER.debug("Value of 'Style' attribute of "+numberOfHotel+" element is changed on "+expected);
    }

    public void valueOfTextAttributeShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelTextElement = driver.findElement(By.xpath(String.format(HOTEL_TEXT_ELEMENT_XPATH, numberOfHotel)));
        Assert.assertEquals("Change of textColor doesn't work",expected, hotelTextElement.getAttribute("style"));
        LOGGER.debug("Value of 'Style' Text-attribute of "+numberOfHotel+" element is changed on "+expected);
    }


    public void verifyAlertMessageIsNotExisted() {
        LOGGER.debug("Checking existence of Alert message...");
        Assert.assertEquals(driver.findElements(By.xpath(ALERT_MESSAGE_XPATH)).size(), 0);
    }

    public boolean elementIsDisplayed(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));
        LOGGER.info(LoggerList.EXISTING_ELEMENT+element.getText());
        return element.isDisplayed();
    }
}
