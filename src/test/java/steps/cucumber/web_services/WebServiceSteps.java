package steps.cucumber.web_services;

import application_objects.ExpectedSearchList;
import application_objects.ResultData;
import application_objects.Search;
import application_objects.User;
import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import steps.junit.WebserviceTestSteps;
import utils.LoggerList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceSteps {
    private static Gson gson;
    private static Search[] searches;
    private static ResultData resultData;
    private static List<ResultData> resultDataList;
    private static ExpectedSearchList expectedSearchList;
    private static WebserviceTestSteps webserviceTestSteps;
    private static List<User> response;
    private static List<User> expected;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceSteps.class);

    @Before
    public static void preConditions() {
        gson = new Gson();
        webserviceTestSteps = new WebserviceTestSteps();
    }

    @Given("I start test...")
    public void iStartTest() throws FileNotFoundException {
        LOGGER.info(LoggerList.STARTING_TEST);
    }

    @When("I get users by condition {int}")
    public void iLookForUsersByCondition(int condition) throws IOException, URISyntaxException {
        Search searchCondition = WebserviceTestSteps.getSearchDataFromSearchFile(gson, condition);
        String responseString = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, searchCondition);
        response = webserviceTestSteps.getDataFromResponseGSON(responseString);
        LOGGER.debug (response.toString());
        LOGGER.info(LoggerList.GETTING_RESPONSE);
        expected = webserviceTestSteps.getDataFromExpectedGSON(condition);
        LOGGER.debug (expected.toString());
    }

    @Then("I compare gotten usernames with expected result for {string}")
    public void iCompareGottenUsernamesWithExpectedResultFor(String string){
        LOGGER.info("I compare gotten UserNames with expected result by "+string);
        webserviceTestSteps.checkGottenResultEqualExpectedResult(response,expected);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @Then("I compare gotten amount of Users with expected result")
    public void iCompareGottenAmountOfUsersWithExpectedResult(){
        LOGGER.info("I compare gotten amount of Users with expected result = "+expected.size());
        webserviceTestSteps.checkingAmountOfUsers(response,expected);
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

}
