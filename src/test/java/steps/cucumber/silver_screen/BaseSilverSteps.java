package steps.cucumber.silver_screen;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import settings.ScreenConfig;
import settings.ScreenSettings;
import utils.GeneralUtils;
import utils.LoggerList;
import web.driver.Driver;
import web.pages.silver_screen.SilverPage;

public class BaseSilverSteps {
    static WebDriver driver;
    static Actions actions;
    static SilverPage silverPage;

    private  static final Logger LOGGER = LogManager.getLogger(BaseSilverSteps.class);

    @Before
    public void initializePages() {
        LOGGER.info(LoggerList.INITIALIZING_DRIVER);
        Driver.initDriver();
        driver = Driver.getDriver();
        actions = new Actions(driver);
        ScreenSettings.setScreenMode(ScreenConfig.FULL_SCREEN, driver);
        GeneralUtils.setTimeOuts(driver);
        LOGGER.info(LoggerList.INITIALIZING_PAGES);
        silverPage = new SilverPage(driver);

    }

    @After
    public static void postConditionSteps() {
        LOGGER.info(LoggerList.KILLING_DRIVER);
        Driver.destroy();
    }

    public static Boolean searchForContainingWord(String xPath, String word){
        String searchingLine = driver.findElement(By.xpath(xPath)).getText();
        return searchingLine.toLowerCase().contains(word.toLowerCase());
    }
}
