package tests.booking;

import actions.GeneralUtils;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import settings.BrowserConfig;
import settings.ScreenConfig;
import settings.ScreenSettings;
import tests.TestSteps;
import web.driver.Driver;
import web.pages.MainBookingPage;
import web.pages.SearchResultsBookingPage;


public class BookingHotelsTest {
    private static WebDriver driver;
    private static String addressSite = "https://booking.com";
    private static MainBookingPage mainPage;
    private static SearchResultsBookingPage searchResult;
    private static TestSteps testSteps;

    @BeforeClass
    public static void preconditionSteps() {
        Driver.initDriver(BrowserConfig.CHROME);
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        driver.get(addressSite);
    }

     @Before
     public void initializePages() {
        mainPage = new MainBookingPage(driver);
        searchResult = new SearchResultsBookingPage(driver);
        testSteps = new TestSteps(driver);
        }


    @Test
    public void bookingMoscowHotel() throws InterruptedException {
        mainPage.enterDesiredLocation("Москва");
        mainPage.enterTripDurationFromTo(5,10);
        mainPage.enterAdultsChildrenRooms(4,2,1);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMinPrice();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu("//a[@data-id='pri-1']//span");
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(10);
        testSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
    }


    @Test
    public void bookingParisTest() throws InterruptedException {
        mainPage.enterDesiredLocation("Париж");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.chooseListOfMaxPrice();
        searchResult.clickSortButton();
        double priceFromCheckBox = searchResult.getMaxPriceFromCheckBoxMenu("//a[@data-id='pri-5']//span");
        double perNight = searchResult.getPricePerNightTheFirstHotelFromList(7);
        testSteps.priceFirstShouldBeGreaterThanSecond(priceFromCheckBox,perNight);
        Thread.sleep(5000);
    }

    @Test
    public void bookingOsloTest() throws InterruptedException {
        mainPage.enterDesiredLocation("Осло");
        mainPage.enterTripDurationFromTo(3,7);
        mainPage.enterAdultsChildrenRooms(4,0,2);
        mainPage.clickSearchButton();
        searchResult.setStarOfHotel(3);
        searchResult.setStarOfHotel(4);
        Thread.sleep(3000);
        searchResult.setElementAttribute(10,"backgroundColor","green");
        searchResult.setElementTextAttribute(10,"color","red");
        testSteps.valueOfAttributesShouldBeChanged(10,"background-color: green;","style");
        testSteps.valueOfTextAttributeShouldBeChanged(10,"color: red;","style");
    }

    @AfterClass
    public static void postConditionSteps(){
        Driver.destroy();
    }
}
