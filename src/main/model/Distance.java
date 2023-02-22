package model;

public class Distance extends Location {

    private final double EARTH_RADIUS = 6371;
    private final String UNITS = "KM";

    public Distance(String name, String address, String city, double longitude, double latitude) {
        super(name, address, city, longitude, latitude);
    }

    // EFFECTS: returns the shortest distance between two coordinate points
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
