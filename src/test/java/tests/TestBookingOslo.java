package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestBookingOslo {
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        System.setProperty("webdriver.chrome.silentOutput", "true");
        driver = new ChromeDriver();

        driver.get("https://booking.com");
        WebElement elCity = driver.findElement(By.id("ss"));
        elCity.sendKeys("Осло");
        elCity.click();

        driver.findElement(By.xpath("//*[@data-mode='checkin']")).click();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date oneNight = calendar.getTime();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date oneDay = calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String datePlusOneNight = dateFormat.format(oneNight);
        String datePlusOneDay = dateFormat.format(oneDay);
        WebElement dateFrom=driver.findElement(By.xpath(String.format("//*[contains(@data-date,'%s')]", datePlusOneDay)));
        WebElement dateTo=driver.findElement(By.xpath(String.format("//*[contains(@data-date,'%s')]", datePlusOneNight)));

        Actions actions = new Actions(driver);
        actions.click(dateFrom).moveToElement(dateTo).click().build().perform();

        WebElement settingTrip = driver.findElement(By.xpath("//label[@class='xp__input']"));
        WebElement buttonChildren = driver.findElement(By.xpath("//*[@aria-describedby='group_children_desc'][2]"));
        WebElement checkPrice = driver.findElement(By.xpath("//button[@class='sb-searchbox__button ']"));

        actions.click(settingTrip).moveToElement(buttonChildren).click()
                .moveToElement(settingTrip).doubleClick()
                .moveToElement(checkPrice).click().build().perform();

        Thread.sleep(3000);



        Thread.sleep(3000);



        Thread.sleep(5000);
        driver.quit();
    }
}
