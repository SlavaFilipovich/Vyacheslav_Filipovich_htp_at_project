package steps.cucumber.booking;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import steps.junit.BookingTestSteps;
import utils.LoggerList;
import web.pages.booking.MainPage;

public class AdditionToTripListSteps{
    private static WebDriver driver;
    private static MainPage mainPage;
    private static BookingTestSteps bookingTestSteps;
    private static final Logger LOGGER = LogManager.getLogger(AdditionToTripListSteps.class);

    public AdditionToTripListSteps(){
        driver = BaseSteps.driver;
        mainPage = BaseSteps.mainPage;
        bookingTestSteps = BaseSteps.bookingTestSteps;
    }

    @Given("I go to booking.com and sign in")
    public void iGoToBookingComAndSignIn() throws InterruptedException {
        LOGGER.info(LoggerList.STARTING_TEST);
        LOGGER.info(LoggerList.GO_TO_BOOKING);
        mainPage.navigateToBooking();
        bookingTestSteps.signInBooking();
    }

    @And("Add hotels to wish list")
    public void addHotelsToWishList() throws InterruptedException {
        LOGGER.info(LoggerList.ADDITION_TO_WISH_LIST);
        bookingTestSteps.addHotelsToFavouriteList();
    }

    @Then("Color of hearts should be red")
    public void colorOfHeartsShouldBeRed() {
        LOGGER.info(LoggerList.CHECKING_ELEMENT_COLOR);
        bookingTestSteps.colorOfHeartsShouldBeRed();
    }

    @And("These hotels should be appeared in My next Trip")
    public void theseHotelsShouldBeAppearedInMyNextTrip() {
        LOGGER.info(LoggerList.CHECKING_APPEARANCE_IN_WISH_LIST);
        bookingTestSteps.itemsShouldBeAppearedInMyNextTrip();
    }


}
