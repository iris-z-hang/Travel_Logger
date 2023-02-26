package model;

import java.util.ArrayList;

// represents a map of a city that contains locations
public class Map {
    // MAP CLASS DESCRIPTION
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

    // EFFECTS: returns the size of the unvisited list
    public int getSizeUnvisited() {
        return unvisited.size();
    }

    // EFFECTS: returns the size of the visited list
    public int getSizeVisited() {
        return visited.size();
    }

    // MODIFIES: this
    // EFFECTS: sets TripFinished to true or false
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
    //          returns true if successful (location present in visited list) else false
    public boolean moveVisitedToUnvisited(String name) {
        for (Location location : visited) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                unvisited.add(location);
                visited.remove(location);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: location is in unvisited list
    // MODIFIES: this
    // EFFECTS: removes location from unvisited list and adds location to visited list
    //          returns true if successful (location present in unvisited list) else false
    public boolean moveUnvisitedToVisited(String name) {
        for (Location location : unvisited) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                visited.add(location);
                unvisited.remove(location);
                return true;
            }
        }
        return false;
    }

    public String getCity() {
        return city;
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
            System.out.println(location.getName());
        }
    }

    // EFFECTS: returns list of visited locations
    public void printVisitedLocations() {
        for (Location location : visited) {
            System.out.println(location.getName());
        }
    }

    // EFFECTS: returns the unvisited location with name that matches the parameter name else returns nothing
    public Location findLocationByNameUnvisited(String name) {
        int index = 0;
        for (Location location : unvisited) {
            String locationName = location.getName();

            if (locationName.equals(name)) {
                index = unvisited.indexOf(location);
                break;
            }
        }
        return unvisited.get(index);
    }

    // EFFECTS: returns the visited location with name that matches the parameter name else returns nothing
    public Location findLocationByNameVisited(String name) {
        int index = 0;
        for (Location location : visited) {
            String locationName = location.getName();

            if (locationName.equals(name)) {
                index = visited.indexOf(location);
                break;
            }
        }
        return visited.get(index);
    }

    // EFFECTS: returns the full location including name, address, coordinates
    public String getInformationUnvisited(String name) {
        String info = "";

        for (Location location: unvisited) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                info = "Name: " + name + ", Address: " + location.getAddress() + ", Latitude: " +
                        location.getLatitude() + " , Longitude: " + location.getLongitude();
                break;
            }
        }
        return info;
    }

    // EFFECTS: returns the full location including name, address, coordinates
    public String getInformationVisited(String name) {
        String info = "";

        for (Location location: visited) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                info = "Name: " + name + ", Address: " + location.getAddress() + ", Latitude: " +
                        location.getLatitude() + " , Longitude: " + location.getLongitude();
                break;
            }
        }
        return info;
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

        return Math.round((EARTH_RADIUS * haversineFormulaPart2) * 10000d) / 10000d;
    }




// https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/

}
