package steps.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import steps.BookingTestSteps;
import steps.TrashTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

public class BaseSteps {
    static WebDriver driver;
    static MainPage mainPage;
    static SearchResultsPage searchResult;
    static BookingTestSteps bookingTestSteps;
    static TrashTestSteps trashSteps;

    @Before
    public void initializePages() {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        bookingTestSteps = new BookingTestSteps(driver);
        trashSteps = new TrashTestSteps(driver);
    }

    @After
    public static void postConditionSteps() {
        Driver.destroy();
    }
}
