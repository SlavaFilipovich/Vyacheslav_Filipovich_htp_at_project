package steps.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import steps.BookingTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;

public class AdditionToTripListSteps{
    private static WebDriver driver;
    private static MainPage mainPage;
    private static BookingTestSteps bookingTestSteps;

    public AdditionToTripListSteps(){
        driver = BaseSteps.driver;
        mainPage = BaseSteps.mainPage;
        bookingTestSteps = BaseSteps.bookingTestSteps;
    }

    @Given("I go to booking.com and sign in")
    public void iGoToBookingComAndSignIn() throws InterruptedException {
        mainPage.navigateToSite();
        bookingTestSteps.signInBooking();
    }

    @And("Add hotels to wish list")
    public void addHotelsToWishList() throws InterruptedException {
        bookingTestSteps.addHotelsToFavouriteListAndCheckingColor();
    }

    @Then("Color of hearts should be red")
    public void colorOfHeartsShouldBeRed() {
        bookingTestSteps.colorOfHeartsShouldBeRed();
    }

    @And("These hotels should be appeared in My next Trip")
    public void theseHotelsShouldBeAppearedInMyNextTrip() {
        bookingTestSteps.itemsShouldBeAppearedInMyNextTrip();
    }


}
