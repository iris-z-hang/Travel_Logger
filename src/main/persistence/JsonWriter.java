package persistence;

import model.Location;
import model.VisitedUnvisitedLists;
import org.json.JSONObject;
import java.io.*;
import java.util.ArrayList;

// Represents a writer that writes JSON representation of visited/unvisited lists to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

//    // MODIFIES: this
//    // EFFECTS: writes JSON representation of workroom to file
//    public void write(ArrayList<Location> location) {
//        JSONObject json = location.toJson(list);
//        saveToFile(json.toString(TAB));
//    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeUnvisited(Location location) {
        JSONObject json = location.toJson(VisitedUnvisitedLists.unvisited);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void writeVisited(Location location) {
        JSONObject json = location.toJson(VisitedUnvisitedLists.unvisited);
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}
