package steps.junit;

import application_objects.ExpectedSearchList;
import application_objects.ResultData;
import application_objects.Search;
import application_objects.User;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import utils.PathList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class WebserviceTestSteps {
    private static final String URL = "http://178.124.206.46:8001/app/ws/";
    private static Search[] searches;
    private static ResultData resultData;
    private static List<ResultData> resultDataList;
    private static ExpectedSearchList expectedSearchList;
    private static final Logger LOGGER = LogManager.getLogger(WebserviceTestSteps.class);

    public static String getSearchDataFromHttpResponse(Gson gson, Search search) throws URISyntaxException, IOException {
        LOGGER.debug("Creating client and http request...");
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder(PathList.WS_URL);
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(gson.toJson(search)));
        LOGGER.debug("Creating http response...");
        HttpResponse response = client.execute(request);
        String responseData = EntityUtils.toString(response.getEntity());
        return responseData;
    }

    public static Search getSearchDataFromSearchFile(Gson gson, int condition) throws FileNotFoundException {
        LOGGER.debug("Getting data from Search JSon file...");
        searches = gson.fromJson(new JsonReader(new FileReader(PathList.WS_SEARCH_JSON)), Search[].class);
        return searches[condition];
    }

    public List <User> getDataFromResponseGSON(String response){
        LOGGER.debug("Getting users data from Response...");
        Gson gson = new Gson();
        resultData = gson.fromJson(response, ResultData.class);
        return resultData.getData();
    }

    public List <User> getDataFromExpectedGSON(int condition) throws FileNotFoundException {
        LOGGER.debug("Getting users data from Expected JSon file...");
        Gson gson = new Gson();
        expectedSearchList = gson.fromJson(new JsonReader(new FileReader(PathList.WS_RESULTS_JSON)), ExpectedSearchList.class);
        resultDataList = expectedSearchList.getResultDataList();
        return resultDataList.get(condition).getData();
    }

    public void checkingAmountOfUsers(List<User> response, List<User> expected){
        LOGGER.debug("Checking amount of users...");
        Assert.assertTrue(response.size() == expected.size());
    }

    public void checkGottenResultEqualExpectedResult(List<User> response, List<User> expected){
        LOGGER.debug("Comparison of userNames: response and expectation...");
        Assert.assertTrue(response.equals(expected));
    }
}
