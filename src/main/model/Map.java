package model;

import java.util.ArrayList;

// represents a map of a city that contains locations
public class Map extends ListFunctions {
    private boolean tripFinished;
    protected static String city;

    private ArrayList<Location> unvisited;
    private ArrayList<Location> visited;

    // constructor for map class with city name and tripFinished set to false
    public Map(String city) {
        Map.city = city;
        tripFinished = false;

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

    // EFFECTS: returns the size of the visited list
    public boolean getTripFinished() {
        return tripFinished;
    }

    // MODIFIES: this
    // EFFECTS: sets TripFinished to true or false
    public void setTripFinished(boolean tripFinished) {
        this.tripFinished = tripFinished;
    }

    // MODIFIES: this
    // EFFECTS: adds location to unvisited list, the same location can be added multiple times
    public void addUnvisitedLocation(Location location) {
        addLocation(unvisited, location);
    }

    // MODIFIES: this
    // EFFECTS: adds location to visited list, the same location can be added multiple times
    public void addVisitedLocation(Location location) {
        addLocation(unvisited, location);
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

    // EFFECTS: returns the city
//    public String getCity() {
//        return city;
//    }

    // EFFECTS: returns list of unvisited locations
    public ArrayList<Location> getUnvisitedLocations() {
        return getLocations(unvisited);
        // return unvisited;
    }

    // EFFECTS: returns list of visited locations
    public ArrayList<Location> getVisitedLocations() {
        return getLocations(visited);
        // return visited;
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

    // EFFECTS: calculates distance between two locations using their longitude and latitude by the Haversine formula
    //          rounds answer to four decimal places
    public double distanceTwoPoints(Location location1, Location location2) {
        double latitude1 = location1.getLatitude();
        double longitude1 = location1.getLongitude();
        double latitude2 = location2.getLatitude();
        double longitude2 = location2.getLongitude();

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double longDistance = Math.toRadians(longitude2 - longitude1);

        double haversineFormulaPart1 = Math.pow(Math.sin(latDistance / 2), 2) + Math.pow(Math.sin(longDistance / 2), 2)
                * Math.cos(latitude1) * Math.cos(latitude2);

        double haversineFormulaPart2 = 2 * Math.asin(Math.sqrt(haversineFormulaPart1));

        return Math.round((6371 * haversineFormulaPart2) * 10000d) / 10000d;
    }



// https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/

}
