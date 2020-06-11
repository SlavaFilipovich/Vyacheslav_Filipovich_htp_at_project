package tests.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.junit.BookingTestSteps;
import utils.GeneralUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import utils.LoggerList;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;


public class BookingHotelsTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static BookingTestSteps bookingTestSteps;

    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String MAX_PRICE_TEXT_FROM_MAX = "//a[@data-id='pri-5']//span";
    private static final String MAX_PRICE_TEXT_FROM_MIN = "//a[@data-id='pri-1']//span";
    private static final Logger LOGGER = LogManager.getLogger(BookingHotelsTest.class);

    @BeforeClass
    public static void preconditionSteps() {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        //Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

     @Before
     public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        bookingTestSteps = new BookingTestSteps(driver);
        }


    @Test
    public void bookingMoscowHotel() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        mainPage.navigateToBooking();
        mainPage.enterDesiredLocation("Москва");
        mainPage.enterTripDurationFromTo(5,10);
        mainPage.enterAdultsChildrenRooms(4,2,1);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMinPrice();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MIN);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(10);
        bookingTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @Test
    public void bookingParisTest() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        mainPage.navigateToBooking();
        mainPage.enterDesiredLocation("Париж");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMaxPrice();
        searchResult.clickSortButton();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MAX);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(7);
        bookingTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @Test
    public void bookingOsloTest() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        mainPage.navigateToBooking();
        mainPage.enterDesiredLocation("Осло");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.setStarOfHotel(3);
        searchResult.setStarOfHotel(4);
        Thread.sleep(3000);
        searchResult.setElementAttribute(10,"backgroundColor","green");
        searchResult.setElementTextAttribute(10,"color","red");
        bookingTestSteps.valueOfAttributesShouldBeChanged(10,"background-color: green;","style");
        bookingTestSteps.valueOfTextAttributeShouldBeChanged(10,"color: red;","style");
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @AfterClass
    public static void postConditionSteps(){
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}
