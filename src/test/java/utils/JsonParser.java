package utils;

import application_items.Ingredient;
import application_items.Recipe;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonParser {
    private final static String JSON = "C:\\projects\\vyacheslav_filipovich_project\\src\\test\\resources\\recipeJson";
    private final static String JSON_1 = "C:\\projects\\vyacheslav_filipovich_project\\src\\test\\resources\\recipeJson1";

    File file = new File(JSON);

    public void parseJSON() throws IOException {
        String input = new String(Files.readAllBytes(Paths.get(JSON)));
        JSONObject obj = new JSONObject(input);
        System.out.println(obj.getJSONArray("ingredlist").getJSONObject(0));
    }

    public void parseGSON() throws FileNotFoundException {
        Gson gson = new Gson();
        Recipe recipe = gson.fromJson(new JsonReader(new FileReader(JSON)), Recipe.class);
        System.out.println(recipe.ingredlist[0].description);
    }

    public void parseJackson() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Recipe recipe = mapper.readValue(file, Recipe.class);
        System.out.println(recipe.ingredlist[0].description);
    }

    public void fromGSON() throws IOException {
        Gson gson = new Gson();
        Recipe recipe = new Recipe("Borsch", new Ingredient[]{}, 120);
        System.out.println(gson.toJson(recipe));
        Files.write(Paths.get(JSON_1), gson.toJson(recipe).getBytes());
    }

}
