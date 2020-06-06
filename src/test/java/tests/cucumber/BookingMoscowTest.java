package tests.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.test_steps.BookTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

public class BookingMoscowTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static BookTestSteps bookTestSteps;

    private static final String MAX_PRICE_TEXT_FROM_MIN = "//a[@data-id='pri-1']//span";

    @Before
    public static void preconditionSteps() {
        //Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        bookTestSteps = new BookTestSteps(driver);
    }

    @Given("I go to booking.com")
    public void iGoToBookingCom() {
        mainPage.navigateToSite();
    }

    @When("I enter desired location")
    public void iEnterDesiredLocation() {
        mainPage.enterDesiredLocation("Москва");
    }

    @And("Enter duration of Trip")
    public void enterDurationTrip() throws InterruptedException {
        mainPage.enterTripDurationFromTo(5, 10);
    }

    @And("Enter number of Adults, Children, Rooms")
    public void enterNumberOfAdultsChildrenRooms() throws InterruptedException {
        mainPage.enterAdultsChildrenRooms(4, 2, 1);
        mainPage.clickSearchButton();
    }

    @And("I choose List of min price")
    public void iChooseListOfMinPrice() {
        searchResult.chooseListOfMinPrice();
    }

    @Then("I verify that price from checkbox-menu greater than from hotel-list")
    public void iVerifyPriceFromCheckboxGreaterThanFromHotelList() throws InterruptedException {
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MIN);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(10);
        bookTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox, perNight);
        Thread.sleep(5000);
    }

    @After
    public static void postConditionSteps() {
        Driver.destroy();
    }

}
