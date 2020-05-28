package web.driver;

import org.openqa.selenium.WebDriver;
import settings.BrowserConfig;
import utils.GeneralUtils;
import utils.PathList;

public class Driver {
    private static WebDriver driver;


    private Driver() throws IllegalAccessException{
        throw new IllegalAccessException("Utility class");
    }

    public static void initDriver(){
        if(driver == null) {
            try {
                driver = DriverHandler.getDriver(BrowserConfig.valueOf(GeneralUtils.getProperties(PathList.INSTALL_PROP).getProperty("BROWSER")));
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
