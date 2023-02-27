package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VisitedUnvisitedListTest {

    private Location testLocation1;
    private Location testLocation2;
    private Location testLocation3;
    private VisitedUnvisitedLists list;

    @BeforeEach
    private void setup() {
        list = new VisitedUnvisitedLists();

        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
        testLocation2 = new Location("Art Gallery of Ontario", "317 Dundas St W.",
                43.653860, -79.392770);
        testLocation3 = new Location("Casa Loma", "1 Austin Terrace",
                43.667709, -79.394775);

    }

    @Test
    private void getSizeUnvisitedTest() {
        list.addUnvisitedLocation(testLocation1);
        assertEquals(1, list.getSizeUnvisited());
    }




}
