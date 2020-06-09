package steps.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.jsoup.Connection;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import steps.BookingTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
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

    public BookingHotelsSteps(){
        driver = BaseSteps.driver;
        mainPage = BaseSteps.mainPage;
        bookingTestSteps = BaseSteps.bookingTestSteps;
        searchResult = BaseSteps.searchResult;
    }


//   @Before
//    public static void preconditionSteps() {
//        //Driver.initDriver();
//        driver = Driver.getDriver();
//        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
//        GeneralUtils.setTimeOuts(driver);
//        mainPage = new MainPage(driver);
//        searchResult = new SearchResultsPage(driver);
//        bookingTestSteps = new BookingTestSteps(driver);
//    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        driver.get(BOOKING_ADDRESS);
    }

    @When("I enter desired location")
    public void iEnterDesiredLocation() {
        mainPage.enterDesiredLocation("Москва");
    }

    @And("Enter duration of Trip")
    public void enterDurationTrip() throws InterruptedException {
        mainPage.enterTripDurationFromTo(5, 10);
    }

    @And("Enter number of Adults, Children, Rooms and click Search")
    public void enterNumberOfAdultsChildrenRoomsAndClickSearch() throws InterruptedException {
        mainPage.enterAdultsChildrenRooms(4, 2, 1);
        mainPage.clickSearchButton();
    }

    @And("I set filter with Min price per night")
    public void iSetFilterWithMinPricePerNight() {
        searchResult.chooseListOfMinPrice();
    }

    @And("I set filter with Max price per night")
    public void iSetFilterWithMaxPricePerNight() throws InterruptedException {
        searchResult.chooseListOfMaxPrice();
    }

    @And("Sort list from low to high price")
    public void iSortListFromLowToHighPrice() throws InterruptedException {
        searchResult.clickSortButton();
    }

    @And("I set stars of hotels")
    public void iSetStarsOfHotels() throws InterruptedException {
        searchResult.setStarOfHotel(3);
        searchResult.setStarOfHotel(4);
        Thread.sleep(3000);
    }

    @And("Set background color as green")
    public void setBackgroundColorAsGreen() throws InterruptedException {
        searchResult.setElementAttribute(10, "backgroundColor", "green");
    }

    @And("Set color of text as red")
    public void setColorOfTextAsRed() throws InterruptedException {
        searchResult.setElementTextAttribute(10, "color", "red");
    }

    @Then("I verify that price from checkbox-menu greater than from hotel-list")
    public void iVerifyPriceFromCheckboxGreaterThanFromHotelList() throws InterruptedException {
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MIN);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(10);
        bookingTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox, perNight);
        Thread.sleep(5000);
    }


    @Then("Filter price should be greater than hotel price in list")
    public void filterPriceShouldBeGreaterThanHotelPriceInList() throws InterruptedException {
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MAX);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(7);
        bookingTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox, perNight);
        Thread.sleep(5000);
    }

    @Then("Color of background should be green")
    public void colorOfBackgroundShouldBeGreen() {
        bookingTestSteps.valueOfAttributesShouldBeChanged(10, "background-color: green;", "style");
    }

    @And("Color of text should be red")
    public void colorOfTextShouldBeRed() {
        bookingTestSteps.valueOfTextAttributeShouldBeChanged(10, "color: red;", "style");
    }

}
