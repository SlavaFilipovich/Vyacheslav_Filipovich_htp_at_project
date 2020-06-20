package steps.cucumber.booking;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import steps.junit.BookingTestSteps;
import utils.LoggerList;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

public class BookingHotelsSteps{
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static BookingTestSteps bookingTestSteps;

    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String MAX_PRICE_TEXT_FROM_MIN = "//a[@data-id='pri-1']//span";
    private static final String MAX_PRICE_TEXT_FROM_MAX = "//a[@data-id='pri-5']//span";
    private static final Logger LOGGER = LogManager.getLogger(BookingHotelsSteps.class);

    public BookingHotelsSteps(){
        driver = BaseSteps.driver;
        mainPage = BaseSteps.mainPage;
        bookingTestSteps = BaseSteps.bookingTestSteps;
        searchResult = BaseSteps.searchResult;
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        LOGGER.info(LoggerList.STARTING_TEST);
        LOGGER.info(LoggerList.GO_TO_BOOKING);
        mainPage.navigateToBooking();
    }

    @When("I enter desired location {string}")
    public void iEnterDesiredLocation(String string) {
        LOGGER.info(LoggerList.ENTER_LOCATION);
        mainPage.enterDesiredLocation(string);
    }

    @And("Enter duration of Trip {int} days in {int} days")
    public void enterDurationTrip(int in, int duration) throws InterruptedException {
        LOGGER.info(LoggerList.ENTER_DURATION);
        mainPage.enterTripDurationFromTo(in, duration);
    }

    @And("Enter number of Adults {int}, Children {int}, Rooms {int} and click Search")
    public void enterNumberOfAdultsChildrenRoomsAndClickSearch(int adults, int children, int rooms) {
        LOGGER.info(LoggerList.ENTER_ADULTS_CHILDREN_ROOMS);
        mainPage.enterAdultsChildrenRooms(adults, children, rooms);
        mainPage.clickSearchButton();
    }

    @And("I set filter with Min price per night")
    public void iSetFilterWithMinPricePerNight() {
        LOGGER.info(LoggerList.SET_FILTER_WITH_MIN_PRICE);
        searchResult.chooseListOfMinPrice();
    }

    @And("I set filter with Max price per night")
    public void iSetFilterWithMaxPricePerNight() {
        LOGGER.info(LoggerList.SET_FILTER_WITH_MAX_PRICE);
        searchResult.chooseListOfMaxPrice();
    }

    @And("Sort list from low to high price")
    public void iSortListFromLowToHighPrice() throws InterruptedException {
        searchResult.clickSortButton();
    }

    @And("I set star {int} for hotels")
    public void iSetStarsOfHotels(int star) throws InterruptedException {
        LOGGER.info(LoggerList.SET_STAR_OF_HOTEL+star);
        searchResult.setStarOfHotel(star);
        Thread.sleep(2000);
    }

    @And("Set background color as green")
    public void setBackgroundColorAsGreen() throws InterruptedException {
        LOGGER.info(LoggerList.SET_ELEMENT_COLOR);
        searchResult.setElementAttribute(10, "backgroundColor", "green");
    }

    @And("Set color of text as red")
    public void setColorOfTextAsRed() throws InterruptedException {
        LOGGER.info(LoggerList.SET_ELEMENT_COLOR);
        searchResult.setElementTextAttribute(10, "color", "red");
    }

    @Then("I verify that price from checkbox-menu greater than from hotel-list with duration {int} days")
    public void iVerifyPriceFromCheckboxGreaterThanFromHotelList(int duration) throws InterruptedException {
        LOGGER.info(LoggerList.VERIFYING_PRICE_OF_HOTEL);
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MIN);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(duration);
        bookingTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox, perNight);
        Thread.sleep(5000);
    }


    @Then("Color of background should be green")
    public void colorOfBackgroundShouldBeGreen() {
        LOGGER.info(LoggerList.CHECKING_ELEMENT_COLOR);
        bookingTestSteps.valueOfAttributesShouldBeChanged(10, "background-color: green;", "style");
    }

    @And("Color of text should be red")
    public void colorOfTextShouldBeRed() {
        LOGGER.info(LoggerList.CHECKING_ELEMENT_COLOR);
        bookingTestSteps.valueOfTextAttributeShouldBeChanged(10, "color: red;", "style");
    }

}
