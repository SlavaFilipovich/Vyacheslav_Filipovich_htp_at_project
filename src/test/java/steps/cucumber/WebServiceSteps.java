package steps.cucumber;

import application_objects.Search;
import com.google.gson.Gson;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import steps.junit.WebserviceTestSteps;
import utils.LoggerList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceSteps {
    private static Gson gson;
    Search search;
    List<String> list;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceSteps.class);

    @Before
    public static void preConditions() {
        LOGGER.info(LoggerList.STARTING_TEST);
        gson = new Gson();
    }

    @Given("I look for users by {number} of condition")
    public void iLookForUsersByNumberOfConditions(int number) throws FileNotFoundException {
        search = WebserviceTestSteps.getSearchDataFromFile(gson, number);
    }

    @When("I get response from server")
    public void iGetResponseFromServer() throws IOException, URISyntaxException {
        LOGGER.info(LoggerList.GETTING_RESPONSE);
        String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
        list = WebserviceTestSteps.getAllUserNames(response);
    }

    @And("Print result of looking for users")
    public void printResultOfLookingForUsers(){
        list.forEach(System.out::println);
    }

    @Then("I compare the given response with our input {string}")
    public void iCompareGivenResponseWithOurInput(String string){
        Assert.assertTrue(WebserviceTestSteps.partialCheck(list, string));
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @Then("I compare list size with my expected result {string}")
    public void iCompareListSizeWithMyExpectedResult(String string){
        LOGGER.info(LoggerList.CHECKING_ALERT_MESSAGE);
        Assert.assertEquals(Integer.parseInt(string), list.size());
        LOGGER.info(LoggerList.FINISHING_TEST);
    }

    @Test
    public void byPartialShortNameTest() throws IOException, URISyntaxException {
        Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 1);
        String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
        List<String> list = WebserviceTestSteps.getAllUserNames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebserviceTestSteps.partialCheck(list,"a"));
    }
    @Test
    public void byFullShortNameTest() throws IOException, URISyntaxException {
        Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 2);
        String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
        List<String> list = WebserviceTestSteps.getAllUserNames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebserviceTestSteps.fullCheck(list,"a"));
    }
    @Test
    public void byPartialLongNameTest() throws IOException, URISyntaxException {
        Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 3);
        String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
        List<String> list = WebserviceTestSteps.getAllUserNames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebserviceTestSteps.partialCheck(list,"al"));
    }
    @Test
    public void byFullLongNameTest() throws IOException, URISyntaxException {
        Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 4);
        String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
        List<String> list = WebserviceTestSteps.getAllUserNames(response);
        list.forEach(System.out::println);
        Assert.assertTrue(WebserviceTestSteps.fullCheck(list,"saldo"));
    }

}
