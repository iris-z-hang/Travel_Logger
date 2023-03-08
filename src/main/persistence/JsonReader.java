package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Location;
import model.Map;
import model.VisitedUnvisitedLists;
import model.VisitedUnvisitedLists.*;
import org.json.*;
import ui.MapFunctions;

import static model.VisitedUnvisitedLists.unvisited;
import static model.VisitedUnvisitedLists.visited;

//import static ui.MapFunctions.unvisited;

// a reader that reads visited/unvisited lists from source file
public class JsonReader {

    static String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader (String source) {
        JsonReader.source = source;
    }

    // EFFECTS: reads VisitedUnvisitedLists from file and returns it;
    // IOException thrown if error occurs when reading data from a file


    public Map readUnvisited() throws IOException {
        return ReaderHelper.read(unvisited);
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseMapUnvisited(jsonObject);
    }

    public Map readVisited() throws IOException {
        return ReaderHelper.read(visited);
//        String jsonData = readFile(source);
//        JSONObject jsonObject = new JSONObject(jsonData);
//        return parseMapVisited(jsonObject);
    }

//    // EFFECTS: reads source file as string and returns it
//    private String readFile(String source) throws IOException {
//        StringBuilder contentBuilder = new StringBuilder();
//
//        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
//            stream.forEach(s -> contentBuilder.append(s));
//        }
//
//        return contentBuilder.toString();
//    }

    private Map parseMapUnvisited(JSONObject jsonObject) {
        return ReaderHelper.parseMap(jsonObject, unvisited);
    }

    private Map parseMapVisited(JSONObject jsonObject) {
        return ReaderHelper.parseMap(jsonObject, visited);
    }

    private void addUnvisitedLocationsJSON(Map map, JSONObject jsonObject) {
        ReaderHelper.addLocationsJSON(map, jsonObject, unvisited);

//        JSONArray jsonArray = jsonObject.getJSONArray("locations");
//        for (Object json : jsonArray) {
//            JSONObject nextLocation = (JSONObject) json;
//            addUnvisitedLocationJSON(map, nextLocation);
//        }
    }

    private void addUnvisitedLocationJSON(Map map, JSONObject jsonObject) {
        ReaderHelper.addLocationJSON(map, jsonObject, unvisited);
//        String name = jsonObject.getString("name");
//        String address = jsonObject.getString("address");
//        double latitude = jsonObject.getDouble("latitude");
//        double longitude = jsonObject.getDouble("longitude");
//
//        Location location = new Location(name, address, latitude, longitude);
//        map.addLocation(unvisited, location);
    }

    private void addVisitedLocationsJSON(Map map, JSONObject jsonObject) {
        ReaderHelper.addLocationsJSON(map, jsonObject, visited);

    }

    private void addVisitedLocationJSON(Map map, JSONObject jsonObject) {
        ReaderHelper.addLocationJSON(map, jsonObject, visited);

    }

}
