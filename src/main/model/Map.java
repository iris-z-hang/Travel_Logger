package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {
    public boolean tripFinished;
    protected String city;

    public LinkedList<Location> unvisited;
    public LinkedList<Location> visited;

    private final double EARTH_RADIUS = 6371;
    private final String UNITS = "KM";

    public Map(String city) {
        this.city = city;
        tripFinished = false;

        this.unvisited = new LinkedList<>();
        this.visited = new LinkedList<>();
    }

    public void setTripFinished(boolean tripFinished) {
        this.tripFinished = tripFinished;
    }

    // MODIFIES: this
    // EFFECTS: adds location to unvisited list
    public void addUnvisitedLocation(Location location) {
        this.unvisited.add(location);

    }

    // MODIFIES: this
    // EFFECTS: adds location to visited list
    public void addVisitedLocation(Location location) {
        this.visited.add(location);
    }

    // MODIFIES: this
    // EFFECTS: removes location from unvisited list if exists and returns true, else returns false
    public boolean removeUnvisitedLocation(Location location) {
        if (unvisited.contains(location)) {
            this.unvisited.remove(location);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes visited location from visited list if exists and returns true, else returns false
    public boolean removeVisitedLocation(Location location) {
        if (unvisited.contains(location)) {
            this.visited.remove(location);
            return true;
        }
        return false;
    }

    // REQUIRES: location is in visited list
    // MODIFIES: this
    // EFFECTS: removes location from visited list and adds location to unvisited list
    public void moveVisitedToUnvisited(Location location) {
        this.visited.remove(location);
        this.unvisited.add(location);
    }

    // REQUIRES: location is in unvisited list
    // MODIFIES: this
    // EFFECTS: removes location from unvisited list and adds location to visited list
    public void moveUnvisitedToVisited(Location location) {
        this.unvisited.remove(location);
        this.visited.add(location);

    }

    // EFFECTS: returns list of unvisited locations
    public LinkedList<Location> getUnvisitedLocations() {
        return unvisited;
    }

    // EFFECTS: returns list of visited locations
    public LinkedList<Location> getVisitedLocations() {
        return visited;
    }

    // EFFECTS: prints list of unvisited locations
    public void printUnvisitedLocations() {
        for (Location location : unvisited) {
            System.out.println(location.getName());
        }
    }

    // EFFECTS: returns list of visited locations
    public void printVisitedLocations() {
        for (Location location : visited) {
            System.out.println(location.getName());
        }
    }

    // EFFECTS: returns next unvisited location
    public Location getNextUnvisitedLocation() {
        return unvisited.getFirst();
    }

    // EFFECTS: returns last visited location
    public Location getLasVisitedLocation() {
        return visited.getLast();
    }

    public Location findLocationByNameUnvisited(String name) {
        Location foundLocation = null;
        for (Location location : unvisited) {
            if (location.getName().equals(name)) {
                foundLocation = location;
            }
            else {
                System.out.println("Not found.");
            }
        }
        return foundLocation;
    }

    public Location findLocationByNameVisited(String name) {
        Location foundLocation = null;
        for (Location location : visited) {
            if (location.getName().equals(name)) {
                foundLocation = location;
            }
            else {
                System.out.println("Not found.");
            }
        }
        return foundLocation;
    }

    public double distanceTwoPoints(Location location1, Location location2) {
        double longitude1 = location1.getLongitude();
        double latitude1 = location1.getLatitude();
        double longitude2 = location2.getLongitude();
        double latitude2 = location2.getLatitude();

        double latDistance = Math.toRadians(latitude2 - latitude1);
        double longDistance = Math.toRadians(longitude2 - longitude1);

        double haversineFormulaPart1 = Math.pow(Math.sin(latDistance / 2), 2) + Math.pow(Math.sin(longDistance / 2), 2)
                * Math.cos(latitude1) * Math.cos(latitude2);

        double haversineFormulaPart2 = 2 * Math.asin(Math.sqrt(haversineFormulaPart1));

        return EARTH_RADIUS * haversineFormulaPart2;
    }

//    public int distanceManyPoints() {
//        double totalDistance = 0;
//
//        for (Location location : visited) {
//
//        }
//        return 0;
//    }



// https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/

}
