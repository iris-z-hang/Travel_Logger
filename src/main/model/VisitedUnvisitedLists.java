package model;

import java.util.ArrayList;

// abstract class that represents common behaviours between unvisited and visited lists of locations
public class VisitedUnvisitedLists extends Map {

    private ArrayList<Location> unvisited;
    private ArrayList<Location> visited;

    // EFFECTS: constructor for VisitedUnvisitedLists class with setup for unvisited and visited arrayLists
    public VisitedUnvisitedLists() {
        super(Map.city);
        unvisited = new ArrayList<>();
        visited = new ArrayList<>();
    }

    // EFFECTS: returns the size of the unvisited list
    public int getSizeUnvisited() {
        return getSize(unvisited);
    }

    // EFFECTS: returns the size of the visited list
    public int getSizeVisited() {
        return getSize(visited);
    }


    // MODIFIES: this
    // EFFECTS: adds location to unvisited list, the same location can be added multiple times
    public void addUnvisitedLocation(Location location) {
        addLocation(unvisited, location);
    }

    // MODIFIES: this
    // EFFECTS: adds location to visited list, the same location can be added multiple times
    public void addVisitedLocation(Location location) {
        addLocation(visited, location);
    }

    // MODIFIES: this
    // EFFECTS: removes location from unvisited list if exists and returns true, else returns false
    public boolean removeUnvisitedLocation(Location location) {
        return removeLocation(unvisited, location);
    }

    // MODIFIES: this
    // EFFECTS: removes visited location from visited list if exists and returns true, else returns false
    public boolean removeVisitedLocation(Location location) {
        return removeLocation(visited, location);
    }

    // MODIFIES: this
    // EFFECTS: removes location from visited list and adds location to unvisited list
    //          returns true if successful (location present in visited list) else false
    public boolean moveVisitedToUnvisited(String name) {
        return moveLocation(visited, unvisited, name);
    }

    // MODIFIES: this
    // EFFECTS: removes location from unvisited list and adds location to visited list
    //          returns true if successful (location present in unvisited list) else false
    public boolean moveUnvisitedToVisited(String name) {
        return moveLocation(unvisited, visited, name);
    }


    // EFFECTS: returns list of unvisited locations
    public ArrayList<Location> getUnvisitedLocations() {
        return getLocations(unvisited);
    }

    // EFFECTS: returns list of visited locations
    public ArrayList<Location> getVisitedLocations() {
        return getLocations(visited);
    }

    // REQUIRES: there is a location that matches name on unvisited list
    // EFFECTS: returns the unvisited location with name that matches the parameter name
    public Location findLocationByNameUnvisited(String name) {
        return findLocationByName(unvisited, name);
    }

    // REQUIRES: there is a location that matches name on visited list
    // EFFECTS: returns the visited location with name that matches the parameter name
    public Location findLocationByNameVisited(String name) {
        return findLocationByName(visited, name);
    }

    // EFFECTS: returns the location information which includes name, address, latitude, and longitude for unvisited
    public String getInformationUnvisited(String name) {
        return getInformation(unvisited, name);
    }

    // EFFECTS: returns the location information which includes name, address, latitude, and longitude for visited
    public String getInformationVisited(String name) {
        return getInformation(visited, name);
    }


}
