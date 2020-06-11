package tests.booking;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import steps.junit.BookingTestSteps;
import steps.junit.TrashTestSteps;
import utils.GeneralUtils;
import utils.LoggerList;
import web.driver.Driver;
import web.pages.booking.MainPage;

import java.io.FileNotFoundException;
import java.util.Set;

public class RegistrationTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static TrashTestSteps trashSteps;
    private static BookingTestSteps bookingSteps;

    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String CONFIRMATION_LINK = "//*[contains(text(),'Подтверждаю')]";
    private static final Logger LOGGER = LogManager.getLogger(RegistrationTest.class);

    @BeforeClass
    public static void preconditionSteps() throws FileNotFoundException, InterruptedException {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        trashSteps = new TrashTestSteps(driver);
        bookingSteps = new BookingTestSteps(driver);
        trashSteps.getNewTemporaryEmail(driver);
    }

    @Test
    public void bookingRegistrationTest() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        driver.get(BOOKING_ADDRESS);
        bookingSteps.registerAccountBooking();
        trashSteps.checkLetterForRegistration("booking.com");
        String currentHandle = driver.getWindowHandle();
        driver.findElement(By.xpath(CONFIRMATION_LINK)).click();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
            else driver.switchTo().activeElement();
        }
        bookingSteps.goToBookingDashboard();
        bookingSteps.verifyAlertMessageIsNotExisted();
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @AfterClass
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}
