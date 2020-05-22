package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class TestSteps {
    private WebDriver driver;
    private String hotelElementXpath = "//*[@data-hotelid][%d]";
    private String hotelTextElementXpath = "//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]";

    public TestSteps(WebDriver driver){
        this.driver = driver;
    }

    public void priceFirstShouldBeGreaterThanSecond(double checkBox, double listOfHotels){
        if(checkBox>listOfHotels) {
            Assert.assertTrue("Something wrong", checkBox>listOfHotels);
        }
        System.out.println(String.format("Price of HotelRoom per night: %s BYN\r\n MaxBudget: %s BYN",listOfHotels,checkBox));
    }

    public void valueOfAttributesShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelElement = driver.findElement(By.xpath(String.format(hotelElementXpath, numberOfHotel)));
        Assert.assertEquals("Change of backgroundColor doesn't work",expected, hotelElement.getAttribute("style"));
    }

    public void valueOfTextAttributeShouldBeChanged(int numberOfHotel, String expected, String typeOfAttribute){
        WebElement hotelTextElement = driver.findElement(By.xpath(String.format(hotelTextElementXpath, numberOfHotel)));
        Assert.assertEquals("Change of textColor doesn't work",expected, hotelTextElement.getAttribute("style"));
    }

}
