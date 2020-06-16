package steps.cucumber.silver_screen;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.GeneralUtils;
import utils.PathList;
import web.pages.silver_screen.SilverPage;

import java.util.List;

public class SilverScreenSteps {
    private static WebDriver driver;
    private static Actions actions;
    private static SilverPage silverPage;
    private static int amountOfFilms;
    private static List<WebElement> filmsList;
    //private static String searchWord = GeneralUtils.getProperties(PathList.SILVER_PROP).getProperty("SEARCH_WORD");
    private static final String SEARCHING_RESULT_XPATH = "//*[contains(@class, 'responsive')]//a/span[contains(text(), '%s')]";
    private static final String LINE_FILMS = "//*[contains(@class, 'responsive')]//a/span[contains(text(), 'Фильмы')]";
    private static final String MY_LEVEL_XPATH = "(//*[contains(text(),'Мой уровень')])[1]";

    private static final Logger LOGGER = LogManager.getLogger(SilverScreenSteps.class);

    public SilverScreenSteps(){
        driver = BaseSilverSteps.driver;
        actions = BaseSilverSteps.actions;
        silverPage = BaseSilverSteps.silverPage;
    }

    @Given("I open an app")
    public void iOpenApp(){
        LOGGER.info("I'm opening an App");
        silverPage.navigateToSilverScreen();
    }

    @When("I search for {string} word")
    public void iSearchForSearchWord(String searchWord){
        LOGGER.info("Searching films for word");
        silverPage.searchFilms(searchWord);
    }

    @When("I login with <login> and <password>")
    public void iLoginWithLoginAndPassword(){
        LOGGER.info("Typing login and password...");
        silverPage.fillInLoginForSite();
        silverPage.fillInPasswordForSite();
        silverPage.clickSignIn();
    }

    @When("I left blank {string} field")
    public void iLeftBlankField(String field){
        if(field.equals("login")){
            silverPage.fillInPasswordForSite();
        }
        else if(field.equals("password"))
            silverPage.fillInLoginForSite();
    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems() throws InterruptedException {
        Thread.sleep(3000);
        LOGGER.info("Getting number from line Films");
        amountOfFilms = Integer.parseInt(driver.findElement(By.xpath(LINE_FILMS)).getText().replaceAll("\\D", ""));
        Assert.assertNotEquals(0, amountOfFilms);
    }

    @Then("I can see Red Carpet Club {string} in upper right corner")
    public void iCanSeeRedCarpetClubLevelInUpperRightCorner(String level){
        Assert.assertTrue(BaseSilverSteps.searchForContainingWord(MY_LEVEL_XPATH, level));
    }

    @Then("I see {string} message")
    public void iSeeMessage(String message){
        silverPage.clickSignIn();
    }

    @And("each item name or description contains {string}")
    public void eachItemNameOrDescriptionContainsSearchWord(String searchWord){
        LOGGER.info("Getting number from line Films");
        filmsList = driver.findElements(By.xpath(String.format(SEARCHING_RESULT_XPATH, searchWord)));
        Assert.assertTrue(amountOfFilms == filmsList.size());
    }

}
