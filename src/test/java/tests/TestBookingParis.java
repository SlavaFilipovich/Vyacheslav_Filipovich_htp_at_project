package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TestBookingParis {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.navigate().to("https://booking.com");
        WebElement elCity = driver.findElement(By.id("ss"));
        elCity.sendKeys("Париж");
        elCity.click();

        driver.findElement(By.xpath("//*[@data-mode='checkin']")).click();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 3);
        Date threeDays = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 7);
        Date sevenDay = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datePlusThreeDays = dateFormat.format(threeDays);
        String datePlusSevenDays = dateFormat.format(sevenDay);
        WebElement dateFrom=driver.findElement(By.xpath(String.format("//*[contains(@data-date,'%s')]", datePlusThreeDays)));
        dateFrom.click();
        WebElement dateTo=driver.findElement(By.xpath(String.format("//*[contains(@data-date,'%s')]", datePlusSevenDays)));
        dateTo.click();

        WebElement settings = driver.findElement(By.xpath("//label[@class='xp__input']"));
        settings.click();

        WebElement buttonAdults = driver.findElement(By.xpath("//*[@aria-describedby='group_adults_desc'][2]"));
        buttonAdults.click();buttonAdults.click();
        WebElement buttonRoom = driver.findElement(By.xpath("//*[@aria-describedby='no_rooms_desc'][2]"));
        buttonRoom.click();
        settings.click();settings.click();
        WebElement checkPrice = driver.findElement(By.xpath("//button[@class='sb-searchbox__button ']"));
        checkPrice.click();
        WebElement checkbox = driver.findElement(By.xpath("//a[@data-id='pri-5']"));
        checkbox.click();
        Thread.sleep(2000);
        WebElement sortElement = driver.findElement(By.cssSelector(""));
        sortElement.click();


        String sampleMaxPrice = driver.findElement(By.xpath("//a[@data-id='pri-5']//span")).getText().replaceAll("[^0-9.]", "");
        int maxPrice = Integer.parseInt(sampleMaxPrice);
        //driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        Thread.sleep(5000);
        List<WebElement> hotelsList = driver.findElements(By.xpath("//div[@class='bui-price-display__value prco-inline-block-maker-helper']"));
        String maxCost = hotelsList.get(1).getText().replaceAll("[^0-9.]", "");
        int budgetNight = Integer.parseInt(maxCost) / 7;

        if(budgetNight<maxPrice) {
            Assert.assertTrue("Something wrong", budgetNight>maxPrice);
        } else
            System.out.println(String.format("Budget per night: %s BYN\r\nMaxPrice: %s BYN",budgetNight,maxPrice));

        Thread.sleep(5000);
        driver.quit();
    }
}
