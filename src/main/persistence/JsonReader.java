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

import static model.Map.unvisited;

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
//        JSONArray name = jsonObject.getJSONArray("list");
        String name = jsonObject.getString("name");
        Map map = new Map(name);
        addLocationsJSON(map, jsonObject);
        // CONVERT NAME TO ARRAYLIST
//        ArrayList<Object> newArrayList = toArrayList(name);
        // List<Location> name = new ArrayList<>;
//        Map map = new Map(name);
//        addLocationsJSON(newArrayList, jsonObject, list);
//        return newArrayList;
        // TODO: Convert to arraylist
        return map;
    }

    public static ArrayList<Object> toArrayList(JSONArray jArray) {
        ArrayList<Object> locationList = new ArrayList<Object>();
        if (jArray != null) {
            for (Object location : jArray) {
                locationList.add(location);
            }
        }
        return locationList;
    }

    public static void addLocationsJSON(Map map, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("locations");
        for (Object json : jsonArray) {
            JSONObject nextLocation = (JSONObject) json;
            addLocationJSON(map, nextLocation);
        }
    }

    public static void addLocationJSON(Map map, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        double latitude = jsonObject.getDouble("latitude");
        double longitude = jsonObject.getDouble("longitude");

        Location location = new Location(name, address, latitude, longitude);
        Map.addLocation(unvisited, location);
    }


}
