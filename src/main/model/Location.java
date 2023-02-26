package model;

import java.util.LinkedList;

// represents a place or location that someone would visit
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
//    // EFFECTS: returns the full location including name, address, coordinates
//    public String getInformation(String name) {
//        String info = "";
//
//        for (Location location: unvisited) {
//            String locationName = location.getName();
//            if (locationName.equals(name)) {
//                info = name + ": " + address + ", " + latitude + " " + longitude;
//                break;
//            }
//        }
//        return info;
//    }

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


}
