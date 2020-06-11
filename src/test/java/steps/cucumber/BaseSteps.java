package steps.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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

public class BaseSteps {
    static WebDriver driver;
    static MainPage mainPage;
    static SearchResultsPage searchResult;
    static BookingTestSteps bookingTestSteps;
    static TrashTestSteps trashSteps;

    private  static final Logger LOGGER = LogManager.getLogger(BaseSteps.class);

    @Before
    public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        bookingTestSteps = new BookingTestSteps(driver);
        trashSteps = new TrashTestSteps(driver);
    }

    @After
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}
