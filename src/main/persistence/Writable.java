package persistence;

import org.json.JSONObject;

import java.util.ArrayList;

// interface for JSON object

public interface Writable {
    JSONObject toJson();

}
