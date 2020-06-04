package tests.booking;

import tests.test_steps.BookTestSteps;
import utils.GeneralUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import web.driver.Driver;
import web.pages.booking.MainPage;
import web.pages.booking.SearchResultsPage;


public class BookingHotelsTest {
    private static WebDriver driver;
    private static MainPage mainPage;
    private static SearchResultsPage searchResult;
    private static BookTestSteps bookTestSteps;

    public static final String MAX_PRICE_TEXT_FROM_MAX = "//a[@data-id='pri-5']//span";
    public static final String MAX_PRICE_TEXT_FROM_MIN = "//a[@data-id='pri-1']//span";

    @BeforeClass
    public static void preconditionSteps() {
        //Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
    }

     @Before
     public void initializePages() {
        mainPage = new MainPage(driver);
        searchResult = new SearchResultsPage(driver);
        bookTestSteps = new BookTestSteps(driver);
        }


    @Test
    public void bookingMoscowHotel() throws InterruptedException {
        mainPage.navigateToSite();
        mainPage.enterDesiredLocation("Москва");
        mainPage.enterTripDurationFromTo(5,10);
        mainPage.enterAdultsChildrenRooms(4,2,1);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMinPrice();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MIN);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(10);
        bookTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
    }


    @Test
    public void bookingParisTest() throws InterruptedException {
        mainPage.navigateToSite();
        mainPage.enterDesiredLocation("Париж");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMaxPrice();
        searchResult.clickSortButton();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu(MAX_PRICE_TEXT_FROM_MAX);
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(7);
        bookTestSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
    }

    @Test
    public void bookingOsloTest() throws InterruptedException {
        mainPage.navigateToSite();
        mainPage.enterDesiredLocation("Осло");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.setStarOfHotel(3);
        searchResult.setStarOfHotel(4);
        Thread.sleep(3000);
        searchResult.setElementAttribute(10,"backgroundColor","green");
        searchResult.setElementTextAttribute(10,"color","red");
        bookTestSteps.valueOfAttributesShouldBeChanged(10,"background-color: green;","style");
        bookTestSteps.valueOfTextAttributeShouldBeChanged(10,"color: red;","style");
    }

    @AfterClass
    public static void postConditionSteps(){

        Driver.destroy();
    }
}
