package tests.trashmail;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.booking.TestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

public class TrashMailTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static tests.booking.TestSteps testSteps;

    @BeforeClass
    public static void preconditionSteps() {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

    @Before
    public void initializePages() {
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        testSteps = new TestSteps(driver);
    }

    @Test
    public void trashMailTest(){

    }


    @AfterClass
    public static void postConditionSteps(){
        Driver.destroy();
    }
}
