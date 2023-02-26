package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for Map class
public class MapTest {

    private Map testMap;
    private Location testLocation1;
    private Location testLocation2;
    private Location testLocation3;

    @BeforeEach
    public void setup() {
        testMap = new Map("testCity");

        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
        testLocation3 = new Location("Casa Loma", "1 Austin Terrace",
                43.667709, -79.394775);


        testMap.addUnvisitedLocation(testLocation1);
        testMap.addVisitedLocation(testLocation1);
    }

    @Test
    public void constructorTest() {
        assertEquals("testCity", testMap.getCity());
    }


    @Test
    public void addUnvisitedLocationTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        testMap.addUnvisitedLocation(testLocation2);
        assertEquals(2, testMap.getSizeUnvisited());
        assertEquals(testLocation2, testMap.getUnvisitedLocations().get(1));

    }

    @Test
    public void addVisitedLocationTest() {
        assertEquals(1, testMap.getSizeVisited());
        testMap.addVisitedLocation(testLocation2);
        assertEquals(2, testMap.getSizeVisited());
        assertEquals(testLocation2, testMap.getVisitedLocations().get(1));
    }

    @Test
    public void setTripFinishedTest() {
        assertFalse(testMap.getTripFinished());
        testMap.setTripFinished(true);
        assertTrue(testMap.getTripFinished());
    }

    @Test
    public void removeUnvisitedLocationTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        assertTrue(testMap.removeUnvisitedLocation(testLocation1));
        assertEquals(0, testMap.getSizeUnvisited());
        assertFalse(testMap.removeUnvisitedLocation(testLocation3));

    }

    @Test
    public void removeVisitedLocationTest() {
        assertEquals(1, testMap.getSizeVisited());
        assertTrue(testMap.removeVisitedLocation(testLocation1));
        assertEquals(0, testMap.getSizeVisited());
        assertFalse(testMap.removeVisitedLocation(testLocation3));
    }

    @Test
    public void moveVisitedToUnvisitedTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        assertEquals(1, testMap.getSizeVisited());

        assertTrue(testMap.moveVisitedToUnvisited(testLocation1.getName()));

        assertEquals(2, testMap.getSizeUnvisited());
        assertEquals(0, testMap.getSizeVisited());

        testMap.addVisitedLocation(testLocation3);
        assertTrue(testMap.moveVisitedToUnvisited(testLocation3.getName()));

        assertEquals(3, testMap.getSizeUnvisited());
        assertEquals(0, testMap.getSizeVisited());

        assertFalse(testMap.moveVisitedToUnvisited(testLocation3.getName()));

    }

    @Test
    public void moveUnvisitedToVisitedTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        assertEquals(1, testMap.getSizeVisited());

        assertTrue(testMap.moveUnvisitedToVisited(testLocation1.getName()));

        assertEquals(0, testMap.getSizeUnvisited());
        assertEquals(2, testMap.getSizeVisited());

        testMap.addUnvisitedLocation(testLocation3);
        assertTrue(testMap.moveUnvisitedToVisited(testLocation3.getName()));

        assertEquals(0, testMap.getSizeUnvisited());
        assertEquals(3, testMap.getSizeVisited());

        assertFalse(testMap.moveUnvisitedToVisited(testLocation3.getName()));

    }

    @Test
    public void findLocationByNameUnvisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByNameUnvisited(testLocation1.getName()));
        testMap.addUnvisitedLocation(testLocation2);
        assertEquals(testLocation2, testMap.findLocationByNameUnvisited(testLocation2.getName()));
        assertEquals(testLocation1, testMap.findLocationByNameUnvisited(testLocation3.getName()));

    }

    @Test
    public void findLocationByNameVisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByNameVisited(testLocation1.getName()));
        testMap.addVisitedLocation(testLocation2);
        assertEquals(testLocation2, testMap.findLocationByNameVisited(testLocation2.getName()));
        assertEquals(testLocation1, testMap.findLocationByNameVisited(testLocation3.getName()));

    }

//    @Test
//    public void printUnvisitedLocationsTest() {
//        assertEquals("Royal Ontario Museum", testMap.printUnvisitedLocations());
//    }
//
//    @Test
//    public void printVisitedLocationsTest() {
//
//    }

    @Test
    public void getInformationUnvisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
        testMap.getInformationUnvisited(testLocation1.getName()));

        testMap.addUnvisitedLocation(testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                testMap.getInformationUnvisited(testLocation2.getName()));


    }

    @Test
    public void getInformationVisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                testMap.getInformationVisited(testLocation1.getName()));

        testMap.addVisitedLocation(testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                testMap.getInformationVisited(testLocation2.getName()));
    }

    @Test
    public void distanceTwoPointsTest() {
        testMap.addUnvisitedLocation(testLocation2);
        assertEquals(1.5544, testMap.distanceTwoPoints(testLocation1, testLocation2));

    }

}
