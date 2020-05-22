package web.driver;

import org.openqa.selenium.WebDriver;
import settings.BrowserConfig;

public class Driver {
    private static WebDriver driver;

    private Driver() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    public static void initDriver(BrowserConfig browserConfig){
        if(driver == null) {
            try {
                driver = DriverHandler.getDriver(browserConfig);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static WebDriver getDriver(){
        if(driver == null){
            try {
                driver = DriverHandler.getDriver(BrowserConfig.CHROME);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void destroy() {
        driver.close();
        driver.quit();
    }
}
