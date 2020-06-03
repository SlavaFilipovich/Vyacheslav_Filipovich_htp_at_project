package web.pages.booking;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import web.pages.InitializingPage;

import java.util.List;

public class SearchResultsPage extends InitializingPage {
    Actions actions;
    private static final String hotelElementHeart = "//*[@data-hotelid][%d]//button";
    private String hotelTextElementXpath = "//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]";
    private static final String HOTEL_LIST_XPATH = "//*[@id='hotellist_inner']/div[@data-hotelid]";

    @FindBy(xpath = "//a[@data-id='pri-1']")
    private WebElement checkMinPrice;

    @FindBy(xpath = "//a[@data-id='pri-5']")
    private WebElement checkMaxPrice;

    @FindBy(xpath = "//a[@data-category='price']")
    private WebElement sortButton;

    @FindBy(xpath = "//*[@id='hotellist_inner']/div[@data-hotelid]")
    public List<WebElement> listOfHotels;

    @FindBy(xpath = "//div[@class='bui-price-display__value prco-text-nowrap-helper prco-inline-block-maker-helper']")
    private List<WebElement> priceListOfHotels;

    @FindBy(id = "current_account")
    private WebElement buttonSignIn;

    @FindBy(id = "ss")
    private WebElement locationField;

    public SearchResultsPage(WebDriver driver){
        super(driver);
        actions = new Actions(driver);
    }




    public void chooseListOfMinPrice(){
        actions.moveToElement(checkMinPrice).click(checkMinPrice).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void chooseListOfMaxPrice() {
        actions.moveToElement(checkMaxPrice).click(checkMaxPrice).build().perform();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickSortButton(){
        actions.moveToElement(sortButton).click(sortButton).build().perform();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    public void setStarOfHotel(int numberOfStars){
        String xPath = String.format("//a[@data-id='class-%d']",numberOfStars);
        WebElement checkboxStar = driver.findElement(By.xpath(xPath));
        actions.moveToElement(checkboxStar).click(checkboxStar).perform();

    }

    public void setElementAttribute(int numberOfHotel, String attribute, String newValue) throws InterruptedException {
        WebElement hotelElement = driver.findElement(By.xpath(String.format("//*[@data-hotelid][%d]",numberOfHotel)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", hotelElement);
        Thread.sleep(3000);
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].style.%s = '%s'",attribute,newValue), hotelElement);
        Thread.sleep(3000);
    }

    public void setElementTextAttribute(int numberOfHotel, String attribute, String newValue) throws InterruptedException {
        WebElement hotelElement = driver.findElement(By.xpath(String.format("//*[@data-hotelid][%d]", numberOfHotel)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", hotelElement);
        Thread.sleep(3000);
        WebElement nameOfHotel = driver.findElement(By.xpath(String.format("//*[@data-hotelid][%d]//span[contains(@class,'sr-hotel__name')]", numberOfHotel)));
        ((JavascriptExecutor) driver).executeScript(String.format("arguments[0].style.%s = '%s'", attribute, newValue), nameOfHotel);
    }

    public Double getPricePerNightTheFirstHotelFromList(int amountOfDays){
        String cost = priceListOfHotels.get(1).getText().replaceAll("[^0-9.]", "");
        return Double.parseDouble(cost) / amountOfDays;
    }

    public Double getMaxPriceFromCheckBoxMenu(String xPath){
        double minPrice, maxPrice;
        String[] samplePrices = driver.findElement(By.xpath(xPath))
                .getText()
                .split("[-+]");
        String first = samplePrices[0].replaceAll("[^0-9.]", "");
        String second = samplePrices[1].replaceAll("[^0-9.]", "");
        if(first.equals("")){ minPrice = 0; } else minPrice = Double.parseDouble(first);
        if(second.equals("")) maxPrice = 0; else maxPrice = Double.parseDouble(second);
        return Math.max(minPrice, maxPrice);
    }


}
