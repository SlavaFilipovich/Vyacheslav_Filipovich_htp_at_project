package tests.booking;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.test_steps.BookTestSteps;
import tests.test_steps.TrashTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;

import java.io.FileNotFoundException;
import java.util.Set;

public class RegistrationTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static TrashTestSteps trashSteps;
    private static BookTestSteps bookingSteps;

    private static final String CONFIRMATION_LINK = "//*[contains(text(),'Подтверждаю')]";

    @BeforeClass
    public static void preconditionSteps() throws FileNotFoundException, InterruptedException {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        trashSteps = new TrashTestSteps(driver);
        bookingSteps = new BookTestSteps(driver);
        trashSteps.getNewTemporaryEmail(driver);
    }

    @Before
    public void initializePages() {

    }

    @Test
    public void bookingRegistrationTest() throws InterruptedException, FileNotFoundException {
        bookingSteps.registerAccount();
        trashSteps.checkLetterForRegistration("booking.com");
        String currentHandle = driver.getWindowHandle();
        driver.findElement(By.xpath(CONFIRMATION_LINK)).click();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
        bookingSteps.goToBookingDashboard();
        bookingSteps.verifyAlertMessageIsNotExisted();
    }
}
