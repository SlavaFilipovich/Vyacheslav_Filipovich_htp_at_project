package steps.cucumber.silver_screen;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SilverScreenSteps {
    private static WebDriver driver;
    private static final String SEARCHING_FIELD = "";
    private static final String SEARCH_BUTTON = "";

    private static final Logger LOGGER = LogManager.getLogger(SilverScreenSteps.class);

    public SilverScreenSteps(){
        driver = BaseSilverSteps.driver;
    }

    @Given("I open an app")
    public void iOpenApp(){
        driver.get("https://silverscreen.by");
    }

    @When("I search for <search word> word")
    public void iSearchForSearchWord(String word){

    }

    @When("I login with <login> and <password>")
    public void iLoginWithLoginAndPassword(String login, String password){

    }

    @When("I left blank <field> field")
    public void iLeftBlankField(String field){

    }

    @Then("I see the list of movie items")
    public void iSeeTheListOfMovieItems(){

    }

    @Then("I can see Red Carpet Club <level> in upper right corner")
    public void iCanSeeRedCarpetClubLevelInUpperRightCorner(){

    }

    @Then("I see <message> message")
    public void iSeeMessage(){

    }

    @And("each item name or description contains <search word>")
    public void eachItemNameOrDescriptionContainsSearchWord(){

    }





}
