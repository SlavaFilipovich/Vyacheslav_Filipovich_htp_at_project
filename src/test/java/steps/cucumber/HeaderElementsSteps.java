package steps.cucumber;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import steps.junit.BookingTestSteps;
import utils.LoggerList;


public class HeaderElementsSteps {
    private static WebDriver driver;
    private static BookingTestSteps bookingTestSteps;
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
    private static final Logger LOGGER = LogManager.getLogger(HeaderElementsSteps.class);

    public HeaderElementsSteps(){
        driver = BaseSteps.driver;
        bookingTestSteps = BaseSteps.bookingTestSteps;
    }

    @When("I sign in")
    public void iSignIn() throws InterruptedException {
        LOGGER.info(LoggerList.LOGIN_ACCOUNT);
        bookingTestSteps.signInBooking();
    }

    @Then("I verify existing header elements")
    public void iVerifyExistingHeaderElements(){
        bookingTestSteps.elementIsDisplayed(BOOKING_IMG);
        bookingTestSteps.elementIsDisplayed(BUTTON_STAYS);
        bookingTestSteps.elementIsDisplayed(BUTTON_FLIGHTS);
        bookingTestSteps.elementIsDisplayed(BUTTON_CAR_RENTALS);
        bookingTestSteps.elementIsDisplayed(BUTTON_ATTRACTIONS);
        bookingTestSteps.elementIsDisplayed(BUTTON_AIRPORT_TAXIS);
        bookingTestSteps.elementIsDisplayed(BUTTON_CURRENCY);
        bookingTestSteps.elementIsDisplayed(BUTTON_LANGUAGE);
        bookingTestSteps.elementIsDisplayed(BUTTON_NOTIFICATIONS);
        bookingTestSteps.elementIsDisplayed(BUTTON_HELP_CENTER);
        bookingTestSteps.elementIsDisplayed(BUTTON_LIST_PROPERTY);
        bookingTestSteps.elementIsDisplayed(BUTTON_YOUR_ACCOUNT);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

}