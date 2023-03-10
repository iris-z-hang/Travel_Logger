package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Location;
import model.Map;
import org.json.*;

// a reader that reads visited/unvisited lists from source file
public class JsonReader {

    static String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Map from file and returns it;
    // IOException thrown if error occurs when reading data from a file
    public Map read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // parses Map from JSON object and returns it
    public Map parseMap(JSONObject jsonObject) {
        JSONArray name = jsonObject.getJSONArray("unvisited");
        JSONArray name2 = jsonObject.getJSONArray("visited");
        Map map = new Map("city name");

        addLocationsJSN(map, jsonObject);
        return map;
    }

    // MODIFIES: map
    // EFFECTS: parses locations from JSON object and adds them to map
    public void addLocationsJSN(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("unvisited");
        JSONArray jsonArray2 = jsonObject.getJSONArray("visited");

        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSN(map, nextLocation);
        }

        for (Object json : jsonArray2) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSN2(map, nextLocation);
        }

    }

    // MODIFIES: map
    // EFFECTS: parses location from JSON object and adds it to map - for unvisited list
    public void addLocationJSN(Map map, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        map.addLocation(map.getUnvisited(), location);
    }

    // MODIFIES: map
    // EFFECTS: parses location from JSON object and adds it to map - for visited list
    public void addLocationJSN2(Map map, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        map.addLocation(map.getVisited(), location);
    }


}
