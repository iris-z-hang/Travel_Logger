package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Map {
    public boolean tripFinished;
    protected static String city;

    public ArrayList<Location> unvisited;
    public ArrayList<Location> visited;

    private final double EARTH_RADIUS = 6371;
    private final String UNITS = "KM";

    public Map(String city) {
        Map.city = city;
        tripFinished = false;

        unvisited = new ArrayList<>();
        visited = new ArrayList<>();
    }

    public void setTripFinished(boolean tripFinished) {
        this.tripFinished = tripFinished;
    }

    // MODIFIES: this
    // EFFECTS: adds location to unvisited list
    public void addUnvisitedLocation(Location location) {
        unvisited.add(location);

    }

    // MODIFIES: this
    // EFFECTS: adds location to visited list
    public void addVisitedLocation(Location location) {
        visited.add(location);
    }

    // MODIFIES: this
    // EFFECTS: removes location from unvisited list if exists and returns true, else returns false
    public boolean removeUnvisitedLocation(Location location) {
        int index = 0;
        if (unvisited.contains(location)) {
            index = unvisited.indexOf(location);
            unvisited.remove(index);
            return true;
        }
        return false;
    }

    // MODIFIES: this
    // EFFECTS: removes visited location from visited list if exists and returns true, else returns false
    public boolean removeVisitedLocation(Location location) {
        int index = 0;
        if (visited.contains(location)) {
            index = visited.indexOf(location);
            visited.remove(index);
            return true;
        }
        return false;
    }

    // REQUIRES: location is in visited list
    // MODIFIES: this
    // EFFECTS: removes location from visited list and adds location to unvisited list
    public void moveVisitedToUnvisited(Location location) {
        visited.remove(location);
        unvisited.add(location);
    }

    // REQUIRES: location is in unvisited list
    // MODIFIES: this
    // EFFECTS: removes location from unvisited list and adds location to visited list
    public void moveUnvisitedToVisited(Location location) {
        unvisited.remove(location);
        visited.add(location);

    }

    // EFFECTS: returns list of unvisited locations
    public ArrayList<Location> getUnvisitedLocations() {
        return unvisited;
    }

    // EFFECTS: returns list of visited locations
    public ArrayList<Location> getVisitedLocations() {
        return visited;
    }

    // EFFECTS: prints list of unvisited locations
    public void printUnvisitedLocations() {
        for (Location location : unvisited) {
            // TODO: DO THIS ACTION IN UI NOT HERE
            System.out.println(location.getName());
        }
    }

    // EFFECTS: returns list of visited locations
    public void printVisitedLocations() {
        for (Location location : visited) {
            // TODO: DO THIS ACTION IN UI NOT HERE
            System.out.println(location.getName());
        }
    }

    public Location findLocationByNameUnvisited(String name) {
//        int index = 0;
//        for (Location location : unvisited) {
//            if (location.getName().equals(name)) {
//                index = unvisited.indexOf(location);
//            }
//        }
//        return unvisited.get(index);

        int index = 0;
        for (int i = 0; i < unvisited.size(); i++) {
            if (unvisited.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        System.out.println("Location not found."); // TODO: remove after fixing bug
        return unvisited.get(index); 
    }

    public Location findLocationByNameVisited(String name) {
        Location foundLocation = null;
        for (Location location : visited) {
            if (location.getName().equals(name)) {
                foundLocation = location;
            }
            else {
                // TODO: DO THIS ACTION IN UI NOT HERE
                System.out.println("Not found.");
            }
        }
        return foundLocation;
    }

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
