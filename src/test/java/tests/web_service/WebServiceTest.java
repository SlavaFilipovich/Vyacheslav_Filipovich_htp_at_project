package tests.web_service;

import application_objects.Search;
import application_objects.User;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import steps.junit.WebserviceTestSteps;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceTest {
        private static Gson gson;
        private static WebserviceTestSteps webserviceTestSteps;
        private static List<User> response;
        private static List<User> expected;
        private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

        @BeforeClass
        public static void preCondition() {
            gson = new Gson();
            webserviceTestSteps = new WebserviceTestSteps();
        }

        @Test
        public void allUsersNamesTest() throws IOException, URISyntaxException {
            commonSteps(0);
            webserviceTestSteps.checkingAmountOfUsers(response,expected);
        }

        @Test
        public void byPartialLongNameTest() throws IOException, URISyntaxException {
            commonSteps(1);
            webserviceTestSteps.checkGottenResultEqualExpectedResult(response,expected);
        }
        @Test
        public void byFullLongNameTest() throws IOException, URISyntaxException {
            commonSteps(2);
            webserviceTestSteps.checkGottenResultEqualExpectedResult(response,expected);
        }
        @Test
        public void byPartialShortNameTest() throws IOException, URISyntaxException {
           commonSteps(3);
            webserviceTestSteps.checkGottenResultEqualExpectedResult(response,expected);
        }
        @Test
        public void byFullShortNameTest() throws IOException, URISyntaxException {
            commonSteps(4);
            webserviceTestSteps.checkGottenResultEqualExpectedResult(response, expected);
        }

        public void commonSteps(int condition) throws IOException, URISyntaxException {
            Search searchCondition = WebserviceTestSteps.getSearchDataFromSearchFile(gson, condition);
            String responseString = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, searchCondition);
            response = webserviceTestSteps.getDataFromResponseGSON(responseString);
            LOGGER.debug (response.toString());
            expected = webserviceTestSteps.getDataFromExpectedGSON(condition);
            LOGGER.debug (expected.toString());
        }

    }
