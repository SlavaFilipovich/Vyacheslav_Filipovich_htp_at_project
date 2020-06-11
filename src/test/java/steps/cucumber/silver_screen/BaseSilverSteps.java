package steps.cucumber.silver_screen;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import settings.ScreenConfig;
import settings.ScreenSettings;
import utils.GeneralUtils;
import utils.LoggerList;
import web.driver.Driver;

public class BaseSilverSteps {
    static WebDriver driver;

    private  static final Logger LOGGER = LogManager.getLogger(BaseSilverSteps.class);

    @Before
    public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
    }

    @After
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }
}
