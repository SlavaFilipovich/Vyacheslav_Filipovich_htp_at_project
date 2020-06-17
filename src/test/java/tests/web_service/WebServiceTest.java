package tests.web_service;

import application_objects.Search;
import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import steps.junit.WebserviceTestSteps;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebServiceTest {
        private static Gson gson;
    private static final Logger LOGGER = LogManager.getLogger(WebServiceTest.class);

        @BeforeClass
        public static void preCondition() {
            gson = new Gson();
        }

        @Test
        public void allUsersNamesTest() throws IOException, URISyntaxException {
            Search searchCondition = WebserviceTestSteps.getSearchDataFromFile(gson, 0);
            String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, searchCondition);
            List<String> list = WebserviceTestSteps.getAllUserNames(response);
            LOGGER.debug (list.toString());
            Assert.assertEquals(6, list.size());
        }

        @Test
        public void byPartialShortNameTest() throws IOException, URISyntaxException {
            Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 1);
            String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
            List<String> list = WebserviceTestSteps.getAllUserNames(response);
            LOGGER.debug (list.toString());
            //list.forEach(System.out::println);
            Assert.assertTrue(WebserviceTestSteps.partialCheck(list,"a"));
        }
        @Test
        public void byFullShortNameTest() throws IOException, URISyntaxException {
            Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 2);
            String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
            List<String> list = WebserviceTestSteps.getAllUserNames(response);
            LOGGER.debug (list.toString());
            ///list.forEach(System.out::println);
            Assert.assertTrue(WebserviceTestSteps.fullCheck(list,"a"));
        }
        @Test
        public void byPartialLongNameTest() throws IOException, URISyntaxException {
            Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 3);
            String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
            List<String> list = WebserviceTestSteps.getAllUserNames(response);
            LOGGER.debug (list.toString());
            //list.forEach(System.out::println);
            Assert.assertTrue(WebserviceTestSteps.partialCheck(list,"al"));
        }
        @Test
        public void byFullLongNameTest() throws IOException, URISyntaxException {
            Search search = WebserviceTestSteps.getSearchDataFromFile(gson, 4);
            String response = WebserviceTestSteps.getSearchDataFromHttpResponse(gson, search);
            List<String> list = WebserviceTestSteps.getAllUserNames(response);
            LOGGER.debug (list.toString());
            //list.forEach(System.out::println);
            Assert.assertTrue(WebserviceTestSteps.fullCheck(list,"bertha"));
        }

    }
