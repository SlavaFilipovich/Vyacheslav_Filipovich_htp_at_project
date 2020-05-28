package tests.booking;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.trashmail.TrashTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;

public class RegistrationTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static TrashTestSteps testSteps;

    @BeforeClass
    public static void preconditionSteps() {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

    @Before
    public void initializePages() {
        testSteps = new TrashTestSteps(driver);
    }


    @Test
    public void bookingRegistrationTest() throws InterruptedException {
        testSteps.getNewTemporaryEmail();
    }
}
