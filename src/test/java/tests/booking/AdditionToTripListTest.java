package tests.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import steps.junit.BookingTestSteps;
import steps.junit.TrashTestSteps;
import utils.GeneralUtils;
import utils.LoggerList;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;


public class AdditionToTripListTest {
    private static WebDriver driver;
    private static SearchResultsPage searchResultsPage;
    private static MainPage mainPage;
    private static TrashTestSteps trashSteps;
    private static BookingTestSteps bookingSteps;
    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final Logger LOGGER = LogManager.getLogger(AdditionToTripListTest.class);


    @BeforeClass
    public static void preconditionSteps() {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

    @Before
    public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        trashSteps = new TrashTestSteps(driver);
        bookingSteps = new BookingTestSteps(driver);
        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Test
    public void additionToTripListTest() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);

        mainPage.navigateToBooking();
        bookingSteps.signInBooking();
        mainPage.enterDesiredLocation("Madrid");
        mainPage.enterTripDurationFromTo(30, 5);
        mainPage.enterAdultsChildrenRooms(2, 0, 1);
        mainPage.clickSearchButton();
        bookingSteps.addHotelsToFavouriteList();
        bookingSteps.colorOfHeartsShouldBeRed();
        bookingSteps.itemsShouldBeAppearedInMyNextTrip();
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @AfterClass
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}
