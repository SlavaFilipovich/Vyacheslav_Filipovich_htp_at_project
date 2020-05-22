package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestWhetherGoogle {
    static WebDriver driver;

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C:\\Tools\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.navigate().to("https://google.com");
        //driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[1]/div/div[2]/input")).sendKeys("погода Минск");
        driver.findElement(By.xpath("//input[@aria-label='Найти']")).sendKeys("погода Минск");
        //driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[2]/div[1]/div[3]/center/input[1]")).submit();
        driver.findElement(By.xpath("//input[@aria-label='Поиск в Google'] ")).submit();


    }
}
