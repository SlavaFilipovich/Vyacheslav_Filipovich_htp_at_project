package tests.test_steps;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import web.pages.booking.MainPage;
import web.pages.booking.RegistrationPage;


public class BookTestSteps {
    private WebDriver driver;
    private MainPage mainPage;
    private RegistrationPage registrationPage;

    private static final String BOOKING_ADDRESS = "https://booking.com";
    private static final String HOTEL_ELEMENT_XPATH = "//*[@data-hotelid][%d]";
    private static final String HOTEL_TEXT_ELEMENT_XPATH = "//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]";
    private static final String YOUR_ACCOUNT_XPATH = "//*[@id='profile-menu-trigger--content']";
    private static final String MY_DASHBOARD_XPATH = "//*[@id='profile-menu-trigger--content']";
    private static final String ALERT_MESSAGE_XPATH = "//*[@class='email-confirm-banner']";

    public BookTestSteps(WebDriver driver){
        this.driver = driver;
        mainPage = new MainPage(driver);
        registrationPage = new RegistrationPage(driver);

    }

    public void goToBookingDashboard() throws InterruptedException {
        driver.get(BOOKING_ADDRESS);
        Thread.sleep(3000);
        driver.findElement(By.xpath(YOUR_ACCOUNT_XPATH)).click();
        driver.findElement(By.xpath(MY_DASHBOARD_XPATH)).click();
    }

    public void registerAccount() throws InterruptedException {
        driver.get(BOOKING_ADDRESS);
        mainPage.clickRegister();
        registrationPage.fillInLoginField();
        registrationPage.fillInPasswordFields();
        Thread.sleep(3000);
    }

    public void priceFirstShouldBeGreaterThanSecond(double checkBox, double listOfHotels){
        if(checkBox>listOfHotels) {
            Assert.assertTrue("Something wrong", checkBox>listOfHotels);
        }
        System.out.println(String.format("Price of HotelRoom per night: %s BYN\r\n MaxBudget: %s BYN",listOfHotels,checkBox));
    }

    public void valueOfAttributesShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelElement = driver.findElement(By.xpath(String.format(HOTEL_ELEMENT_XPATH, numberOfHotel)));
        Assert.assertEquals("Change of backgroundColor doesn't work",expected, hotelElement.getAttribute("style"));
    }

    public void valueOfTextAttributeShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelTextElement = driver.findElement(By.xpath(String.format(HOTEL_TEXT_ELEMENT_XPATH, numberOfHotel)));
        Assert.assertEquals("Change of textColor doesn't work",expected, hotelTextElement.getAttribute("style"));
    }


    public void verifyAlertMessageIsNotExisted() {
        Assert.assertEquals(driver.findElements(By.xpath(ALERT_MESSAGE_XPATH)).size(), 0);
    }
}
