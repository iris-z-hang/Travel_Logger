package persistence;

import model.Map;
import org.json.JSONObject;
import java.io.*;

// Represents a writer that writes JSON representation of visited/unvisited lists to file
public class JsonWriter {
    private static final int TAB = 4;
    private static PrintWriter writer;
    private static String destination;

    // EFFECTS: constructs a writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot be opened for writing
    public static void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public static void write(Map map) {
        JSONObject json = map.toJson();
        saveToFile(json.toString(4));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public static void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    public static void saveToFile(String json) {
        writer.print(json);
    }
}
