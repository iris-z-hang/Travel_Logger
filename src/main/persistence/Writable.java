package persistence;

import org.json.JSONObject;

// interface for JSON object

public interface Writable {
    JSONObject toJson();

}
