package web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import settings.BrowserConfig;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverHandler {


    public static WebDriver getDriver(BrowserConfig browserConfig) throws Exception {
        switch (browserConfig) {
            case CHROME:
                return getChromeDriver();
            case IE:
                return getIEDriver();
            case EDGE:
                return getEDGEDriver();
            case OPERA:
                return getOperaDriver();
            case FIREFOX:
                return getFirefoxDriver();
            case REMOTE:
                return getRemoteDriver();
            default:
                throw new Exception("Unexpected Configuration of Driver");
        }
    }

    private static WebDriver getChromeDriver() {
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C://Tools/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        return new ChromeDriver(options);
    }

    private static WebDriver getRemoteDriver() throws MalformedURLException{
        ChromeOptions options = new ChromeOptions();
        RemoteWebDriver webDriver = new RemoteWebDriver(new URL("https://localhost:4444/wd/hub"), options);
        return webDriver;
    }

    private static WebDriver getFirefoxDriver() {
        System.setProperty("webdriver.gecko.silentOutput", "true");
        System.setProperty("webdriver.gecko.driver", "src/test/java/executable_files/geckodriver.exe");
        return new FirefoxDriver();
    }

    private static WebDriver getOperaDriver() {
        System.setProperty("webdriver.opera.silentOutput", "true");
        System.setProperty("webdriver.opera.driver", "src/test/java/executable_files/operadriver.exe");
        return new OperaDriver();
    }

    private static WebDriver getEDGEDriver() {
        //System.setProperty("webdriver.chrome.silentOutput", "true");
        //System.setProperty("webdriver.chrome.driver", "src/test/java/executable_files/chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver getIEDriver() {
        //System.setProperty("webdriver.chrome.silentOutput", "true");
        //System.setProperty("webdriver.chrome.driver", "src/test/java/executable_files/chromedriver.exe");
        return new ChromeDriver();
    }


}
