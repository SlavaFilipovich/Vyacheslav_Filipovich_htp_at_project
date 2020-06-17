package web.driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import settings.BrowserConfig;
import utils.GeneralUtils;
import utils.PathList;


public class Driver {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private Driver() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class");
    }

    public static void initDriver() {
        if (driver.get() == null) {
            try {
                driver.set(DriverHandler.getDriver(BrowserConfig.valueOf(GeneralUtils.getProperties(PathList.INSTALL_PROP).getProperty("BROWSER"))));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            try {
                driver.set(DriverHandler.getDriver(BrowserConfig.CHROME));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    public static WebDriver getDriver(BrowserConfig config) {
        if (driver.get() == null) {
            try {
                driver.set(DriverHandler.getDriver(config));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver.get();
    }

    public static void destroy() {
        driver.get().close();
        driver.get().quit();
        driver.remove();
    }

    public static void scrollToElementAndClick(WebDriver driver, WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public static void navigateToElementAndClick(WebDriver driver, WebElement element){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click(element).perform();
    }
}
