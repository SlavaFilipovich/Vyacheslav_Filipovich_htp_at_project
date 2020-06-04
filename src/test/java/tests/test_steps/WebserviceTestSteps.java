package tests.test_steps;

import application_objects.Search;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import utils.PathList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WebserviceTestSteps {
    private static final String URL = "http://178.124.206.46:8001/app/ws/";
    private static Search[] searches;


    public static String setHttpResponse(Gson gson, Search search) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder(URL);
        HttpPost request = new HttpPost(builder.build());
        request.setEntity(new StringEntity(gson.toJson(search)));
        HttpResponse response = client.execute(request);
        String responseData = EntityUtils.toString(response.getEntity());
        System.out.println(responseData);
        return responseData;
    }


    public static Search getSearchDataFromFile(Gson gson, int condition) throws FileNotFoundException {
        searches = gson.fromJson(new JsonReader(new FileReader(PathList.WS_JSON)), Search[].class);
        return searches[condition];
    }

    public static List<String> getAllUsernames(String response) {
        Pattern pattern = Pattern.compile("\"username\": \"[A-z]+\"");
        Matcher matcher = pattern.matcher(response);
        List<String> list = new ArrayList<>();
        while (matcher.find())
            list.add(matcher.group());
        list = list.stream().map(s -> s.replaceAll("\"username\": ", "")).map(s -> s.replaceAll("\"", "")).collect(Collectors.toList());
        return list;
    }

    public static boolean fullCheck(List<String> list, String check) {
        Pattern pattern = Pattern.compile(String.format("^%s$",check));
        for (String x : list) {
            if (!pattern.matcher(x).matches())
                return false;
        }
        return true;
    }
    public static boolean partialCheck(List<String> list, String check) {
        Pattern pattern = Pattern.compile(String.format(".*%s.*",check));
        for (String x : list) {
            if (!pattern.matcher(x).matches())
                return false;
        }
        return true;
    }
}
