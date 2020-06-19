package at_classes.classes;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SeleniumAppiumTest {
    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName","Pixel 2");
        caps.setCapability("platformName","android");
        caps.setCapability("browserName","chrome");

        WebDriver driver = new RemoteWebDriver(new URL("http://0.0.0.0:4723/wd/hub"), caps);
        driver.get("https://silverscreen.by");
        Thread.sleep(3000);

        JavascriptExecutor jsDriver = (JavascriptExecutor) driver;

        //driver.findElement(By.xpath("//*[contains(text(),'Афиша')]")).click();
        driver.quit();
    }
}
