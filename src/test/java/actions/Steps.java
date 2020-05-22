package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Steps {

    public static void findElementAndClick(WebDriver driver, String xPath) {
        WebElement element = driver.findElement(By.xpath(xPath));
        element.click();
    }

    public static void moveToElementAndClick(WebDriver driver, String xPath) {
        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(By.xpath(xPath));
        actions.moveToElement(element).click(element).perform();
    }

    public static void scrollToElementAndClick(WebDriver driver, String xPath) {
        WebElement checkboxApple = driver.findElement(By.xpath(xPath));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", checkboxApple);
        checkboxApple.click();
    }

}
