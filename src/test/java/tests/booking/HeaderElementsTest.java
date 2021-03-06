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
import utils.GeneralUtils;
import utils.LoggerList;
import web.driver.Driver;

import java.io.FileNotFoundException;

public class HeaderElementsTest {
    private static WebDriver driver;
    private static BookingTestSteps bookingSteps;

    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String BOOKING_IMG = "//*[@class='header-wrapper']/img";
    private static final String BUTTON_STAYS = "//*[contains(@data-ga-track, 'accommodation')]";
    private static final String BUTTON_FLIGHTS = "//*[contains(@data-ga-track, 'flights')]";
    private static final String BUTTON_CAR_RENTALS = "//*[contains(@data-ga-track, 'rentalcars')]";
    private static final String BUTTON_ATTRACTIONS = "//*[contains(@data-ga-track, 'attractions')]";
    private static final String BUTTON_AIRPORT_TAXIS = "//*[contains(@data-ga-track, 'airport_taxis')]";
    private static final String BUTTON_CURRENCY = "//*[contains(@data-id, 'currency')]";
    private static final String BUTTON_LANGUAGE = "//*[contains(@data-id, 'language')]";
    private static final String BUTTON_NOTIFICATIONS = "//*[contains(@data-id, 'notifications')]";
    private static final String BUTTON_HELP_CENTER = "//*[contains(@class, 'helpcenter')]/a";
    private static final String BUTTON_LIST_PROPERTY = "//*[contains(@id, 'property')]";
    private static final String BUTTON_YOUR_ACCOUNT = "//*[contains(@id, 'account')]";
    private static final Logger LOGGER = LogManager.getLogger(HeaderElementsTest.class);

    @BeforeClass
    public static void preconditionSteps() throws FileNotFoundException, InterruptedException {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

    @Before
    public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        bookingSteps = new BookingTestSteps(driver);
    }

    @Test
    public void verifyHeaderElementsTest() throws InterruptedException, FileNotFoundException {
        LOGGER.info(LoggerList.STARTING_TEST);
        driver.get(BOOKING_ADDRESS);
        bookingSteps.signInBooking();
        bookingSteps.elementIsDisplayed(BOOKING_IMG);
        bookingSteps.elementIsDisplayed(BUTTON_STAYS);
        bookingSteps.elementIsDisplayed(BUTTON_FLIGHTS);
        bookingSteps.elementIsDisplayed(BUTTON_CAR_RENTALS);
        bookingSteps.elementIsDisplayed(BUTTON_ATTRACTIONS);
        bookingSteps.elementIsDisplayed(BUTTON_AIRPORT_TAXIS);
        bookingSteps.elementIsDisplayed(BUTTON_CURRENCY);
        bookingSteps.elementIsDisplayed(BUTTON_LANGUAGE);
        bookingSteps.elementIsDisplayed(BUTTON_NOTIFICATIONS);
        bookingSteps.elementIsDisplayed(BUTTON_HELP_CENTER);
        bookingSteps.elementIsDisplayed(BUTTON_LIST_PROPERTY);
        bookingSteps.elementIsDisplayed(BUTTON_YOUR_ACCOUNT);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @AfterClass
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}