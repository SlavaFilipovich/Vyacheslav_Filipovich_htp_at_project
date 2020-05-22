package settings;

import org.openqa.selenium.WebDriver;

public class ScreenSettings {
    public static void setScreenMode(ScreenConfig config, WebDriver driver) {
        switch (config) {
            case FULL_SCREEN:
                setWindowMode(driver);
            case MAXIMIZE:
                setMaximizeMode(driver);
        }
    }

    private static void setMaximizeMode(WebDriver driver) {
        driver.manage().window().maximize();
    }

    private static void setWindowMode(WebDriver driver) {
        driver.manage().window().fullscreen();
    }
}
