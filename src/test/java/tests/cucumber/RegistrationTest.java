package tests.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.test_steps.BookTestSteps;
import tests.test_steps.TrashTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;

import java.io.FileNotFoundException;
import java.util.Set;

public class RegistrationTest {
    private static WebDriver driver;
    private static TrashTestSteps trashSteps;
    private static BookTestSteps bookingSteps;
    private static final String CONFIRMATION_LINK = "//*[contains(text(),'Подтверждаю')]";

    @Before
    public static void preconditionSteps() {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        trashSteps = new TrashTestSteps(driver);
        bookingSteps = new BookTestSteps(driver);
    }

    @Given("I get temporary Email from TrashMail.com")
    public void iGetTemporaryEmailFromTrashMailCom() throws InterruptedException, FileNotFoundException {
        trashSteps.getNewTemporaryEmail(driver);
    }

    @When("I go to booking.com and fill in registration form")
    public void iGoToBookingComAndFillInRegistrationForm() throws InterruptedException {
        bookingSteps.registerAccountBooking();
    }
    @And("Go to yandex.ru to check letter after registration")
    public void iGoToYandexRuToCheckLetterAfterRegistration() throws InterruptedException {
        trashSteps.checkLetterForRegistration("booking.com");
    }

    @And("Click link with confirmation")
    public void clickLinkWithConfirmation() throws InterruptedException {
        String currentHandle = driver.getWindowHandle();
        driver.findElement(By.xpath(CONFIRMATION_LINK)).click();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }

    @And("Go to Booking Dashboard")
    public void goToBookingDashboard() throws InterruptedException {
        bookingSteps.goToBookingDashboard();
    }

    @Then("Alert message should not be existed")
    public void alertMessageShouldNotBeExisted() throws InterruptedException, FileNotFoundException {
        bookingSteps.verifyAlertMessageIsNotExisted();
    }

    @After
    public static void postConditionSteps() {
        Driver.destroy();
    }
}
