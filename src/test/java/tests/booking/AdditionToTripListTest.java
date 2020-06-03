package tests.booking;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.test_steps.BookTestSteps;
import tests.test_steps.TrashTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

import java.io.FileNotFoundException;
import java.util.Set;

public class AdditionToTripListTest {
    private static WebDriver driver;
    private static SearchResultsPage searchResultsPage;
    private static MainPage mainPage;
    private static TrashTestSteps trashSteps;
    private static BookTestSteps bookingSteps;


    @BeforeClass
    public static void preconditionSteps() throws FileNotFoundException, InterruptedException {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

    @Before
    public void initializePages() {
        trashSteps = new TrashTestSteps(driver);
        bookingSteps = new BookTestSteps(driver);
        mainPage = new MainPage(driver);
        searchResultsPage = new SearchResultsPage(driver);
    }

    @Test
    public void additionToTripListTest() throws InterruptedException, FileNotFoundException {
        bookingSteps.signInBooking();
        mainPage.enterDesiredLocation("Madrid");
        mainPage.enterTripDurationFromTo(30, 5);
        mainPage.enterAdultsChildrenRooms(2, 0, 1);
        mainPage.clickSearchButton();
        bookingSteps.addHotelsToFavouriteListAndCheckingColor();
        bookingSteps.colorOfHeartsShouldBeRed();
        bookingSteps.itemsShouldBeAppearedInMyNextTrip();
    }

    @AfterClass
    public static void postConditionSteps() {
        Driver.destroy();
    }
}
