package model;

import java.util.ArrayList;

public class Map {

    private boolean tripFinished;
    protected static String city;

    public Map(String city) {
        Map.city = city;
        tripFinished = false;
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

    public String getCity() {
        return city;
    }

    public int getSize(ArrayList<Location> list) {
        return list.size();
    }

    // EFFECTS: returns list of unvisited locations
    public ArrayList<Location> getLocations(ArrayList<Location> list) {
        return list;
    }

    public void addLocation(ArrayList<Location> list, Location location) {
        list.add(location);

    }

    public boolean removeLocation(ArrayList<Location> list, Location location) {
        int index = 0;
        if (list.contains(location)) {
            index = list.indexOf(location);
            list.remove(index);
            return true;
        }
        return false;
    }

    public boolean moveLocation(ArrayList<Location> list1, ArrayList<Location> list2, String name) {
        for (Location location : list1) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                list2.add(location);
                list1.remove(location);
                return true;
            }
        }
        return false;
    }

    // REQUIRES: there is a location that matches name on unvisited list
    // EFFECTS: returns the unvisited location with name that matches the parameter name
    public Location findLocationByName(ArrayList<Location> list, String name) {
        int index = 0;
        for (Location location : list) {
            String locationName = location.getName();

            if (locationName.equals(name)) {
                index = list.indexOf(location);
                break;
            }
        }
        return list.get(index);
    }

    // EFFECTS: returns the location information which includes name, address, latitude, and longitude for unvisited
    public String getInformation(ArrayList<Location> list, String name) {
        String info = "";

        for (Location location: list) {
            String locationName = location.getName();
            if (locationName.equals(name)) {
                info = "Name: " + name + ", Address: " + location.getAddress() + ", Latitude: "
                        + location.getLatitude() + " , Longitude: " + location.getLongitude();
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

        return Math.round((6371 * haversineFormulaPart2) * 10000d) / 10000d;
    }

}
