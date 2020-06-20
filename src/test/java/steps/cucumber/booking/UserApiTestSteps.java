package steps.cucumber.booking;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserApiTestSteps {
    private static final Logger LOGGER = LogManager.getLogger(UserApiTestSteps.class);

    @Given("I start execution")
    public void iStartExecution() {
        LOGGER.info("I start execution");
    }

    @When("I search user by {string} name")
    public void iSearchUserByName(String string) {
        LOGGER.info("I search user by "+string);
    }

    @Then("I verify that I got {string}")
    public void iVerifyThatIGot(String string) {
        LOGGER.info("I I verify that I got "+string);
    }
}
