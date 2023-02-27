package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VisitedUnvisitedListTest {

    private Location testLocation1;
    private Location testLocation2;
    private Location testLocation3;
    private VisitedUnvisitedLists list;

    @BeforeEach
    public void setup() {
        list = new VisitedUnvisitedLists();

        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
        testLocation3 = new Location("Casa Loma", "1 Austin Terrace",
                43.667709, -79.394775);

        list.addUnvisitedLocation(testLocation1);
        list.addVisitedLocation(testLocation1);

    }

    @Test
    public void getSizeUnvisitedTest() {
        list.addUnvisitedLocation(testLocation1);
        assertEquals(2, list.getSizeUnvisited());
        assertEquals(testLocation1, list.getUnvisitedLocations().get(1));
    }

    @Test
    public void getSizeVisitedTest() {
        list.addVisitedLocation(testLocation1);
        assertEquals(2, list.getSizeVisited());
        assertEquals(testLocation1, list.getVisitedLocations().get(1));
    }

    @Test
    public void removeUnvisitedLocationTest() {
        assertEquals(1, list.getSizeUnvisited());
        assertTrue(list.removeUnvisitedLocation(testLocation1));
        assertEquals(0, list.getSizeUnvisited());
        assertFalse(list.removeUnvisitedLocation(testLocation3));

    }

    @Test
    public void removeVisitedLocationTest() {
        assertEquals(1, list.getSizeVisited());
        assertTrue(list.removeVisitedLocation(testLocation1));
        assertEquals(0, list.getSizeVisited());
        assertFalse(list.removeVisitedLocation(testLocation3));
    }

    @Test
    public void moveVisitedToUnvisitedTest() {
        assertEquals(1, list.getSizeUnvisited());
        assertEquals(1, list.getSizeVisited());

        assertTrue(list.moveVisitedToUnvisited(testLocation1.getName()));

        assertEquals(2, list.getSizeUnvisited());
        assertEquals(0, list.getSizeVisited());

        list.addVisitedLocation(testLocation3);
        assertTrue(list.moveVisitedToUnvisited(testLocation3.getName()));

        assertEquals(3, list.getSizeUnvisited());
        assertEquals(0, list.getSizeVisited());

        assertFalse(list.moveVisitedToUnvisited(testLocation3.getName()));

    }

    @Test
    public void moveUnvisitedToVisitedTest() {
        assertEquals(1, list.getSizeUnvisited());
        assertEquals(1, list.getSizeVisited());

        assertTrue(list.moveUnvisitedToVisited(testLocation1.getName()));

        assertEquals(0, list.getSizeUnvisited());
        assertEquals(2, list.getSizeVisited());

        list.addUnvisitedLocation(testLocation3);
        assertTrue(list.moveUnvisitedToVisited(testLocation3.getName()));

        assertEquals(0, list.getSizeUnvisited());
        assertEquals(3, list.getSizeVisited());

        assertFalse(list.moveUnvisitedToVisited(testLocation3.getName()));

    }

    @Test
    public void findLocationByNameUnvisitedTest() {
        assertEquals(testLocation1, list.findLocationByNameUnvisited(testLocation1.getName()));
        list.addUnvisitedLocation(testLocation2);
        assertEquals(testLocation2, list.findLocationByNameUnvisited(testLocation2.getName()));
        assertEquals(testLocation1, list.findLocationByNameUnvisited(testLocation3.getName()));

    }

    @Test
    public void findLocationByNameVisitedTest() {
        assertEquals(testLocation1, list.findLocationByNameVisited(testLocation1.getName()));
        list.addVisitedLocation(testLocation2);
        assertEquals(testLocation2, list.findLocationByNameVisited(testLocation2.getName()));
        assertEquals(testLocation1, list.findLocationByNameVisited(testLocation3.getName()));

    }

    @Test
    public void getInformationUnvisitedTestEmpty() {
        assertEquals("", list.getInformationUnvisited(testLocation3.getName()));
    }

    @Test
    public void getInformationVisitedTestEmpty() {
        assertEquals("", list.getInformationVisited(testLocation3.getName()));
    }


    @Test
    public void getInformationUnvisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                list.getInformationUnvisited(testLocation1.getName()));

        list.addUnvisitedLocation(testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                list.getInformationUnvisited(testLocation2.getName()));


    }

    @Test
    public void getInformationVisitedTest() {
        assertEquals("Name: " + testLocation1.getName() + ", Address: " + testLocation1.getAddress()
                        + ", Latitude: " + testLocation1.getLatitude() + " , Longitude: "
                        + testLocation1.getLongitude(),
                list.getInformationVisited(testLocation1.getName()));

        list.addVisitedLocation(testLocation2);

        assertEquals("Name: " + testLocation2.getName() + ", Address: " + testLocation2.getAddress()
                        + ", Latitude: " + testLocation2.getLatitude() + " , Longitude: "
                        + testLocation2.getLongitude(),
                list.getInformationVisited(testLocation2.getName()));
    }


}
