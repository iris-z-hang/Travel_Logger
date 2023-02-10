package model;

import java.util.LinkedList;

public class Location {

    private final String name;
    private final String address;
    private final double longitude;
    private final double latitude;

    private LinkedList<Location> unvisited;
    private LinkedList<Location> visited;

    // EFFECTS:
    public Location(String name, String address, double longitude, double latitude) {
        this.name = name;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;

        unvisited = new LinkedList<>();
        visited = new LinkedList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds unvisited location to unvisited list
    public void addUnvisitedLocation(Location location) {
        unvisited.add(location);
    }

    // MODIFIES: this
    // EFFECTS: adds visited location to visited list
    public void addVisitedLocation(Location location) {
        visited.add(location);
    }


    // getters
    // EFFECTS: returns the full location including name, address, coordinates
    public String getLocation() {
        return name + ": " + address + "," + longitude + latitude;
    }

    //EFFECTS: returns name of location
    public String getName() {
        return name;
    }

    // EFFECTS: returns location address
    public String getAddress() {
        return address;
    }

    // EFFECTS: returns longitude of location
    public double getLongitude() {
        return longitude;
    }

    // EFFECTS: returns latitude of location
    public double getLatitude() {
        return latitude;
    }

    // EFFECTS: returns list of unvisited locations
    public LinkedList<Location> getUnvisitedLocations() {
        return unvisited;
    }

    // EFFECTS: returns list of visited locations
    public LinkedList<Location> getVisitedLocations() {
        return visited;
    }

    // EFFECTS: returns next unvisited location
    public Location getNextUnvisitedLocation() {
        return unvisited.getFirst();
    }
}
