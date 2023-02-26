package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// unit tests for Map class
public class MapTest {

    private Map testMap;
    private Location testLocation1;
    private Location testLocation2;

    @BeforeEach
    public void setup() {
        testMap = new Map("testCity");

        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
//        testLocation3 = new Location("Casa Loma", "1 Austin Terrace",
//                43.667709, -79.394775);
//
//        unvisitedTest = new ArrayList<>();
//        visitedTest = new ArrayList<>();

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


    }

    @Test
    public void addVisitedLocationTest() {
        assertEquals(1, testMap.getSizeVisited());
        testMap.addVisitedLocation(testLocation2);
        assertEquals(2, testMap.getSizeVisited());
    }

    @Test
    public void removeUnvisitedLocationTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        testMap.removeUnvisitedLocation(testLocation1);
        assertEquals(0, testMap.getSizeUnvisited());

    }

    @Test
    public void removeVisitedLocationTest() {
        assertEquals(1, testMap.getSizeVisited());
        testMap.removeVisitedLocation(testLocation1);
        assertEquals(0, testMap.getSizeVisited());
    }

    @Test
    public void moveVisitedToUnvisitedTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        assertEquals(1, testMap.getSizeVisited());

        testMap.moveVisitedToUnvisited(testLocation1.getName());

        assertEquals(2, testMap.getSizeUnvisited());
        assertEquals(0, testMap.getSizeVisited());

    }

    @Test
    public void moveUnvisitedToVisitedTest() {
        assertEquals(1, testMap.getSizeUnvisited());
        assertEquals(1, testMap.getSizeVisited());

        testMap.moveUnvisitedToVisited(testLocation1.getName());

        assertEquals(0, testMap.getSizeUnvisited());
        assertEquals(2, testMap.getSizeVisited());

    }

    @Test
    public void findLocationByNameUnvisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByNameUnvisited(testLocation1.getName()));

    }

    @Test
    public void findLocationByNameVisitedTest() {
        assertEquals(testLocation1, testMap.findLocationByNameVisited(testLocation1.getName()));

    }

    @Test
    public void getInformationUnvisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
        testMap.getInformationUnvisited(testLocation1.getName()));

    }

    @Test
    public void getInformationVisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                testMap.getInformationVisited(testLocation1.getName()));
    }

    @Test
    public void distanceTwoPointsTest() {
        testMap.addUnvisitedLocation(testLocation2);
        assertEquals(1.5544, testMap.distanceTwoPoints(testLocation1, testLocation2));

    }

}
