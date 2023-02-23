package model;

import java.util.LinkedList;

public class Location extends Map{

    private final String name;
    private final String address;
    protected final String city;
    private final double longitude;
    private final double latitude;

//    public LinkedList<Location> unvisited;
//    public LinkedList<Location> visited;

    // EFFECTS:
    public Location(String name, String address, String city, double longitude, double latitude) {
        super(city);
        this.name = name;
        this.address = address;
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;

    }

//    // MODIFIES: this
//    // EFFECTS: adds location to unvisited list
//    public void addUnvisitedLocation(Location location) {
//        this.unvisited.add(location);
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS: adds location to visited list
//    public void addVisitedLocation(Location location) {
//        this.visited.add(location);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: removes location from unvisited list
//    public void removeUnvisitedLocation(Location location) {
//        this.unvisited.remove(location);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: removes visited location from visited list
//    public void removeVisitedLocation(Location location) {
//        this.visited.remove(location);
//    }
//
//    // REQUIRES: location is in visited list
//    // MODIFIES: this
//    // EFFECTS: removes location from visited list and adds location to unvisited list
//    public void moveVisitedToUnvisited(Location location) {
//        this.visited.remove(location);
//        this.unvisited.add(location);
//    }
//
//    // REQUIRES: location is in unvisited list
//    // MODIFIES: this
//    // EFFECTS: removes location from unvisited list and adds location to visited list
//    public void moveUnvisitedToVisited(Location location) {
//        this.unvisited.remove(location);
//        this.visited.add(location);
//
//    }


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

//    // EFFECTS: returns list of unvisited locations
//    public LinkedList<Location> getUnvisitedLocations() {
//        return unvisited;
//    }
//
//    // EFFECTS: returns list of visited locations
//    public LinkedList<Location> getVisitedLocations() {
//        return visited;
//    }
//
//    // EFFECTS: returns next unvisited location
//    public Location getNextUnvisitedLocation() {
//        return unvisited.getFirst();
//    }
//
//    // EFFECTS: returns last visited location
//    public Location getLasVisitedLocation() {
//        return visited.getLast();
//    }
}
