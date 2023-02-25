package model;

import java.util.LinkedList;

public class Location extends Map{

    private final String name;
    private final String address;
    private final double longitude;
    private final double latitude;

    // EFFECTS:
    public Location(String name, String address, double latitude, double longitude) {
        super(Map.city);
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    // getters
    // EFFECTS: returns the full location including name, address, coordinates
    public String getLocation() {
        return name + ": " + address + ", " + latitude + " " + longitude;
    }

    //EFFECTS: returns name of location
    public String getName() {
        return this.name;
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


}
