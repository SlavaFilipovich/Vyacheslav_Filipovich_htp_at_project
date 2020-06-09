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

public class AdditionToTripListTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static BookingTestSteps bookingSteps;

    @Before
    public void initializePages() {
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        bookingSteps = new BookingTestSteps(driver);
        mainPage = new MainPage(driver);
    }

   @Given("I go to booking.com and sign in")
   public void iGoToBookingComAndSignIn() throws InterruptedException {
        bookingSteps.signInBooking();
   }

    @When("I enter desired location")
    public void iEnterDesiredLocation() {
        mainPage.enterDesiredLocation("Madrid");
    }

    @And("enter duration of my trip")
    public void enterDurationOfMyTrip() throws InterruptedException {
        mainPage.enterTripDurationFromTo(30,5);
    }

    @And("enter number of Adults, Children, Rooms")
    public void enterNumberOfAdultsChildrenRooms() throws InterruptedException {
        mainPage.enterAdultsChildrenRooms(2, 0, 1);
    }

     @And("click Search")
     public void clickSearch(){
         mainPage.clickSearchButton();
     }

    @And("add hotels to wish list")
    public void addHotelsToWishList() throws InterruptedException {
        bookingSteps.addHotelsToFavouriteListAndCheckingColor();
    }

    @Then("Color of hearts should be red")
    public void colorOfHeartsShouldBeRed(){
        bookingSteps.colorOfHeartsShouldBeRed();
    }

    @And("These hotels should be appeared in My next Trip")
    public void theseHotelsShouldBeAppearedInMyNextTrip(){
        bookingSteps.itemsShouldBeAppearedInMyNextTrip();
    }

    @After
    public static void postConditionSteps() {
        Driver.destroy();
    }
}
