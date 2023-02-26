package model;

import java.util.ArrayList;

import static model.Map.city;

public class ListFunctions{

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

}
