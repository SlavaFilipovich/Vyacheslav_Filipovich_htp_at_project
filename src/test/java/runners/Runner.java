package runners;

import utils.JsonParser;

import java.io.IOException;

public class Runner {
    static JsonParser jsonParser = new JsonParser();

    public static void main(String[] args) throws IOException {

        jsonParser.parseJSON();
        jsonParser.parseGSON();
        jsonParser.parseJackson();
        jsonParser.fromGSON();
    }
}
