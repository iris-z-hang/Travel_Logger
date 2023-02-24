package model;

import java.util.LinkedList;

public class Location extends Map{

    private final String name;
    private final String address;
    protected final String city;
    private final double longitude;
    private final double latitude;

    // EFFECTS:
    public Location(String name, String address, String city, double longitude, double latitude) {
        super(city);
        this.name = name;
        this.address = address;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    // getters
    // EFFECTS: returns the full location including name, address, coordinates
    public String getLocation() {
        return name + ": " + address + ", " + longitude + " " + latitude;
    }

    //EFFECTS: returns name of location
    public String getName() {
        return name;
    }

    // EFFECTS: returns location address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns city name
    public String getCity() {
        return city;
    }

    // EFFECTS: returns longitude of location
    public double getLongitude() {
        return longitude;
    }

    // EFFECTS: returns latitude of location
    public double getLatitude() {
        return latitude;
    }

}
