package persistence;

import model.Location;
import model.Map;
import model.VisitedUnvisitedLists;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class ReaderHelper {

    // EFFECTS: reads VisitedUnvisitedLists from file and returns it;
    // IOException thrown if error occurs when reading data from a file
    public static Map read(ArrayList<Location> list) throws IOException {
        String jsonData = readFile(JsonReader.source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMap(jsonObject, list);
    }

    // EFFECTS: reads source file as string and returns it
    public String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    public static Map parseMap(JSONObject jsonObject, ArrayList<Location> list) {
        String city = jsonObject.getString("city");
        Map map = new Map(city);
        addLocationsJSON(map, jsonObject, list);
        return map;
    }

    public static void addLocationsJSON(Map map, JSONObject jsonObject, ArrayList<Location> list) {
        JSONArray jsonArray = jsonObject.getJSONArray("locations");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSON(map, nextLocation, list);
        }
    }

    public static void addLocationJSON(Map map, JSONObject jsonObject, ArrayList<Location> list) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        map.addLocation(list, location);
    }
}

