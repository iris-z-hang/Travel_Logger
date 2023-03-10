package model;

import org.json.JSONObject;
import persistence.Writable;

// represents a place or location that someone would visit
public class Location implements Writable {

    private final String name;
    private final String address;
    private final double longitude;
    private final double latitude;

    // EFFECTS: constructs new location with name, address, latitude, and longitude
    public Location(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    //EFFECTS: returns name of location
    public String getName() {
        return name;
    }

    // EFFECTS: returns location address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns latitude of location
    public double getLatitude() {
        return latitude;
    }

    // EFFECTS: returns longitude of location
    public double getLongitude() {
        return longitude;
    }

    public String toString() {
        return name + ": " + address + ", " + latitude + ", " + longitude;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("address", address);
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        return json;
    }
}
