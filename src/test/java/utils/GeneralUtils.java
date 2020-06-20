package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
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

    public static void waitVisibilityOfElement(WebDriver driver, String xPath){
        WebElement element = driver.findElement(By.xpath(xPath));
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOf(element));
    }

    public static Properties getProperties(String path) {
        Properties properties = new Properties();
        InputStream input;
        try {
            input = new FileInputStream(path);
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
