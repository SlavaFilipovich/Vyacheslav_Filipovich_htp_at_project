package web.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import settings.BrowserConfig;


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
            default:
                throw new Exception("Unexpected Configuration of Driver");
        }
    }

    private static WebDriver getChromeDriver() {
        //String pathToDriver = DriverHandler.class.getClassLoader().getResource("webdriver/chromedriver.exe").getPath();
        System.setProperty("webdriver.chrome.silentOutput", "true");
        System.setProperty("webdriver.chrome.driver", "C://Tools/chromedriver.exe");      //"C://Tools/chromedriver.exe"
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-popup-blocking");
        return new ChromeDriver();
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
