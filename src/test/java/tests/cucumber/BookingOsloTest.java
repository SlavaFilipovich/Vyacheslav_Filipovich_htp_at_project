package tests.cucumber;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.test_steps.BookTestSteps;
import utils.GeneralUtils;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;

public class BookingOsloTest {

    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static BookTestSteps bookTestSteps;

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
        mainPage.enterDesiredLocation("Осло");
    }

    @And("Enter duration of Trip")
    public void enterDurationTrip() throws InterruptedException {
        mainPage.enterTripDurationFromTo(3, 7);
    }

    @And("Enter number of Adults, Children, Rooms")
    public void enterNumberOfAdultsChildrenRooms() throws InterruptedException {
        mainPage.enterAdultsChildrenRooms(4, 0, 2);
        mainPage.clickSearchButton();
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

    @Then("Color of background should be green")
    public void colorOfBackgroundShouldBeGreen(){
        bookTestSteps.valueOfAttributesShouldBeChanged(10, "background-color: green;", "style");
    }

    @And("Color of background should be green")
    public void colorOfTextShouldBeRed(){
        bookTestSteps.valueOfTextAttributeShouldBeChanged(10, "color: red;", "style");
    }

    @After
    public static void postConditionSteps() {
        Driver.destroy();
    }
}
