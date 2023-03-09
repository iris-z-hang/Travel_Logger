package persistence;

import model.Location;
import org.json.JSONObject;

import java.util.ArrayList;

// interface for JSON object

public interface Writable {
    JSONObject toJson();

    //JSONObject toJson(ArrayList<Location> list);
}
