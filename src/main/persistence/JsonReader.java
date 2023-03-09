package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.Location;
import model.Map;
import org.json.*;
import ui.MapFunctions;

import static ui.MapFunctions.unvisited;
import static ui.MapFunctions.visited;

//import static ui.MapFunctions.unvisited;

// a reader that reads visited/unvisited lists from source file
public class JsonReader {

    static String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader (String source) {
        this.source = source;
    }

    // EFFECTS: reads Map from file and returns it;
    // IOException thrown if error occurs when reading data from a file
    public static Map read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public static String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }


    public static Map parseMap(JSONObject jsonObject) {
        JSONArray name = jsonObject.getJSONArray("unvisited");
        JSONArray name2 = jsonObject.getJSONArray("visited");
        Map map = new Map(MapFunctions.cityName);

        addLocationsJSON(map, jsonObject);
        return map;
    }

    public static void addLocationsJSON(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unvisited");
        JSONArray jsonArray2 = jsonObject.getJSONArray("visited");

        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSON(map, nextLocation);
        }

        for (Object json : jsonArray2) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSON2(map, nextLocation);
        }

    }

    public static void addLocationJSON(Map map, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        map.addLocation(unvisited, location);
    }

    public static void addLocationJSON2(Map map, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        map.addLocation(visited, location);
    }


}
