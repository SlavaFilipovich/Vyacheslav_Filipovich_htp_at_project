package utils;

import application_objects.Search;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.HttpClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class MyHttpClient {

    public static void main(String[] args) throws IOException, URISyntaxException {
        Search search = new Search("a", false);
        MyHttpClient client = new MyHttpClient();
        client.search(search);
    }

    public void search(Search search) throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("http://178.124.206.46:8001/app/ws/");
        HttpPost request = new HttpPost(builder.build());
        //request.setEntity(new StringEntity("{}"));
        request.setEntity(new StringEntity(MyHttpClient.fromGSON(search)));
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
    public void searchHZ() throws URISyntaxException, IOException {
        HttpClient client = HttpClientBuilder.create().build();
        URIBuilder builder = new URIBuilder("https://www.nbrb.by/api/exrates/currencies/1");
        HttpGet request = new HttpGet(builder.build());
        HttpResponse response = client.execute(request);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }


    public static String fromGSON(Search search){
        Gson gson = new Gson();
        return gson.toJson(search);
    }
}
