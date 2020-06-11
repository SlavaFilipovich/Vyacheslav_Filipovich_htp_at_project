package steps.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.FileNotFoundException;
import java.util.Set;

import steps.junit.BookingTestSteps;
import steps.junit.TrashTestSteps;
import utils.LoggerList;

public class RegistrationSteps {
    private static WebDriver driver;
    private static TrashTestSteps trashSteps;
    private static BookingTestSteps bookingTestSteps;
    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String CONFIRMATION_LINK = "//*[contains(text(),'Подтверждаю')]";
    private static final Logger LOGGER = LogManager.getLogger(RegistrationSteps.class);

    public RegistrationSteps(){
        driver = BaseSteps.driver;
        bookingTestSteps = BaseSteps.bookingTestSteps;
        trashSteps = BaseSteps.trashSteps;
    }

    @Given("I go to Trashmail.com and get temp Email")
    public void iGetToTrashMailComAndGetTempEmail() throws FileNotFoundException, InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        LOGGER.info(LoggerList.GETTING_EMAIL);
        trashSteps.getNewTemporaryEmail(driver);

    }

    @When("Fill in registration form on Booking")
    public void fillInRegistrationForm() throws InterruptedException {
        LOGGER.info(LoggerList.REGISTER_ACCOUNT);
        driver.get(BOOKING_ADDRESS);
        bookingTestSteps.registerAccountBooking();
    }

    @And("Go to yandex.ru to check letter after registration")
    public void iGoToYandexRuToCheckLetterAfterRegistration() throws InterruptedException {
        LOGGER.info(LoggerList.CHECKING_LETTER);
        trashSteps.checkLetterForRegistration("booking.com");
    }

    @And("Click link with confirmation")
    public void clickLinkWithConfirmation() {
        LOGGER.info(LoggerList.CONFIRM_REGISTRATION);
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
    public void goToBookingDashboard() {
        LOGGER.info(LoggerList.GO_TO_DASHBOARD);
        String currentHandle = driver.getWindowHandle();
        bookingTestSteps.goToBookingDashboard();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }

    @Then("Alert message should not be existed")
    public void alertMessageShouldNotBeExisted() {
        LOGGER.info(LoggerList.CHECKING_ALERT_MESSAGE);
        bookingTestSteps.verifyAlertMessageIsNotExisted();
    }

}
