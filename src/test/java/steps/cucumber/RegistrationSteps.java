package steps.cucumber;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.io.FileNotFoundException;
import java.util.Set;

import steps.BookingTestSteps;
import steps.TrashTestSteps;

public class RegistrationSteps {
    private static WebDriver driver;
    private static TrashTestSteps trashSteps;
    private static BookingTestSteps bookingTestSteps;
    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String CONFIRMATION_LINK = "//*[contains(text(),'Подтверждаю')]";

    public RegistrationSteps(){
        driver = BaseSteps.driver;
        bookingTestSteps = BaseSteps.bookingTestSteps;
        trashSteps = BaseSteps.trashSteps;
    }

//    @Before
//    public static void preconditionSteps() throws FileNotFoundException, InterruptedException {
//        Driver.initDriver();
//        driver = Driver.getDriver();
//        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
//        GeneralUtils.setTimeOuts(driver);
//        trashSteps = new TrashTestSteps(driver);
//        bookingSteps = new BookingTestSteps(driver);
//    }

    @Given("I go to Trashmail.com and get temp Email")
    public void iGetToTrashMailComAndGetTempEmail() throws FileNotFoundException, InterruptedException {
        trashSteps.getNewTemporaryEmail(driver);

    }

    @When("Fill in registration form on Booking")
    public void fillInRegistrationForm() throws InterruptedException {
        driver.get(BOOKING_ADDRESS);
        bookingTestSteps.registerAccountBooking();
    }

    @And("Go to yandex.ru to check letter after registration")
    public void iGoToYandexRuToCheckLetterAfterRegistration() throws InterruptedException {
        trashSteps.checkLetterForRegistration("booking.com");
    }

    @And("Click link with confirmation")
    public void clickLinkWithConfirmation() {
        String currentHandle = driver.getWindowHandle();
        driver.findElement(By.xpath(CONFIRMATION_LINK)).click();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }

    @And("Go to Booking Dashboard")
    public void goToBookingDashboard() throws InterruptedException {
        String currentHandle = driver.getWindowHandle();
        bookingTestSteps.goToBookingDashboard();
        Set<String> handles = driver.getWindowHandles();
        for (String actual : handles) {
            if (actual.equalsIgnoreCase(currentHandle)) {
                driver.switchTo().window(currentHandle);
            }
        }
    }

    @Then("Alert message should not be existed")
    public void alertMessageShouldNotBeExisted() {
        bookingTestSteps.verifyAlertMessageIsNotExisted();
    }

}
