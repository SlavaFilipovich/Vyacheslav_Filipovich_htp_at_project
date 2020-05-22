package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class GeneralUtils {

    public static Double convertStringsToDouble(String line){
        double number = Double.parseDouble(line.replaceAll("[^0-9,]", "").replace(",","."));
        return number;
    }

    public static void setTimeOuts(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(10,TimeUnit.SECONDS);
    }

    public static void removeTimeOuts(WebDriver driver){
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(0,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(0,TimeUnit.SECONDS);
    }

    public static void waitVisibilityOfElement(WebDriver driver, String xPath){
        WebElement element = driver.findElement(By.xpath(xPath));
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(element));
    }

}
