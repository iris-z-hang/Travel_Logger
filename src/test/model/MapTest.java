package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapTest {

    private Map testMap;
    private Location testLocation1;
    private Location testLocation2;
    private Location testLocation3;

    private List<Location> unvisitedTest;
    private List<Location> visitedTest;

    @BeforeEach
    public void setup() {
        Map testMap = new Map("testCity");

        Location testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        Location testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
        Location testLocation = new Location("Casa Loma", "1 Austin Terrace",
                43.667709, -79.394775);

        unvisitedTest = new ArrayList<>();
        visitedTest = new ArrayList<>();
    }

    @Test
    public void findLocationByNameUnvisited() {

    }


}
