package tests.fake;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import settings.BrowserConfig;
import web.driver.Driver;

public class FakeBaseSteps {

    private  static final Logger LOGGER = LogManager.getLogger(FakeBaseSteps.class);

    @Before
    public void beforeTest(){
        LOGGER.info("Initializing WebDriver..");
        Driver.initDriver();
        //Driver.getDriver(BrowserConfig.REMOTE);
    }

    @After
    public void afterTest(){
        LOGGER.info("Killing WebDriver...");
        Driver.destroy();
    }
}
