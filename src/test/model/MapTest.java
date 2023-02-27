package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for Map class
public class MapTest {


    private Map testMap;
    private Location testLocation1;
    private Location testLocation2;
    private Location testLocation3;

    ArrayList<Location> unvisitedTest;
    ArrayList<Location> visitedTest;

    @BeforeEach
    public void setup() {
        testMap = new Map("Toronto");

        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
        testLocation3 = new Location("Casa Loma", "1 Austin Terrace",
                43.667709, -79.394775);

        unvisitedTest = new ArrayList<>();
        visitedTest = new ArrayList<>();

        testMap.addLocation(unvisitedTest, testLocation1);
        testMap.addLocation(visitedTest, testLocation1);
    }

    @Test
    public void constructorTest() {
        assertEquals("Toronto", testMap.getCity());
    }

    @Test
    public void setTripFinishedTest() {
        assertFalse(testMap.getTripFinished());
        testMap.setTripFinished(true);
        assertTrue(testMap.getTripFinished());
    }

    @Test
    public void distanceTwoPointsTest() {
        assertEquals(1.5544, testMap.distanceTwoPoints(testLocation1, testLocation2));
    }

    @Test
    public void addUnvisitedLocationTest() {
        assertEquals(1, testMap.getSize(unvisitedTest));
        testMap.addLocation(unvisitedTest, testLocation2);
        assertEquals(2, testMap.getSize(unvisitedTest));
        assertEquals(testLocation2, testMap.getLocations(unvisitedTest).get(1));

    }

    @Test
    public void addVisitedLocationTest() {
        assertEquals(1, testMap.getSize(visitedTest));
        testMap.addLocation(visitedTest, testLocation2);
        assertEquals(2, testMap.getSize(visitedTest));
        assertEquals(testLocation2, testMap.getLocations(visitedTest).get(1));
    }


    @Test
    public void removeUnvisitedLocationTest() {
        assertEquals(1, testMap.getSize(unvisitedTest));
        assertTrue(testMap.removeLocation(unvisitedTest, testLocation1));
        assertEquals(0, testMap.getSize(unvisitedTest));
        assertFalse(testMap.removeLocation(unvisitedTest, testLocation3));

    }

    @Test
    public void removeVisitedLocationTest() {
        assertEquals(1, testMap.getSize(visitedTest));
        assertTrue(testMap.removeLocation(visitedTest, testLocation1));
        assertEquals(0, testMap.getSize(visitedTest));
        assertFalse(testMap.removeLocation(visitedTest, testLocation3));
    }

    @Test
    public void moveVisitedToUnvisitedTest() {
        assertEquals(1, testMap.getSize(unvisitedTest));
        assertEquals(1, testMap.getSize(visitedTest));

        assertTrue(testMap.moveLocation(visitedTest, unvisitedTest, testLocation1.getName()));

        assertEquals(2, testMap.getSize(unvisitedTest));
        assertEquals(0, testMap.getSize(visitedTest));

        testMap.addLocation(visitedTest, testLocation3);
        assertTrue(testMap.moveLocation(visitedTest, unvisitedTest, testLocation3.getName()));

        assertEquals(3, testMap.getSize(unvisitedTest));
        assertEquals(0, testMap.getSize(visitedTest));

        assertFalse(testMap.moveLocation(visitedTest, unvisitedTest, testLocation3.getName()));

    }

    @Test
    public void moveUnvisitedToVisitedTest() {
        assertEquals(1, testMap.getSize(unvisitedTest));
        assertEquals(1, testMap.getSize(visitedTest));

        assertTrue(testMap.moveLocation(unvisitedTest, visitedTest, testLocation1.getName()));

        assertEquals(0, testMap.getSize(unvisitedTest));
        assertEquals(2, testMap.getSize(visitedTest));

        testMap.addLocation(unvisitedTest, testLocation3);
        assertTrue(testMap.moveLocation(unvisitedTest, visitedTest, testLocation3.getName()));

        assertEquals(0, testMap.getSize(unvisitedTest));
        assertEquals(3, testMap.getSize(visitedTest));

        assertFalse(testMap.moveLocation(unvisitedTest, visitedTest, testLocation3.getName()));

    }

    @Test
    public void findLocationByNameUnvisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByName(unvisitedTest, testLocation1.getName()));
        testMap.addLocation(unvisitedTest, testLocation2);
        assertEquals(testLocation2, testMap.findLocationByName(unvisitedTest, testLocation2.getName()));
        assertEquals(testLocation1, testMap.findLocationByName(unvisitedTest, testLocation3.getName()));

    }

    @Test
    public void findLocationByNameVisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByName(visitedTest, testLocation1.getName()));
        testMap.addLocation(visitedTest, testLocation2);
        assertEquals(testLocation2, testMap.findLocationByName(visitedTest, testLocation2.getName()));
        assertEquals(testLocation1, testMap.findLocationByName(visitedTest, testLocation3.getName()));

    }

    @Test
    public void getInformationUnvisitedTestEmpty() {
        Map testMap2 = new VisitedUnvisitedLists();
        ArrayList<Location> unvisitedTest2 = new ArrayList<>();

        assertEquals("", testMap2.getInformation(unvisitedTest2, testLocation1.getName()));
    }

    @Test
    public void getInformationVisitedTestEmpty() {
        Map testMap2 = new VisitedUnvisitedLists();
        ArrayList<Location> visitedTest2 = new ArrayList<>();

        assertEquals("", testMap2.getInformation(visitedTest2, testLocation1.getName()));
    }


    @Test
    public void getInformationUnvisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                testMap.getInformation(unvisitedTest, testLocation1.getName()));

        testMap.addLocation(unvisitedTest, testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                testMap.getInformation(unvisitedTest, testLocation2.getName()));


    }

    @Test
    public void getInformationVisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                testMap.getInformation(visitedTest, testLocation1.getName()));

        testMap.addLocation(visitedTest, testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                testMap.getInformation(visitedTest, testLocation2.getName()));
    }

}