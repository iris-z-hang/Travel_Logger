package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    private Location testLocation1;

    @BeforeEach
    public void setup() {
        testLocation1 = new Location("Royal Ontario Museum", "100 Queens Park",
                43.667709, -79.394775);
    }

    @Test
    public void constructorTest() {
        assertEquals("Royal Ontario Museum", testLocation1.getName());
        assertEquals("100 Queens Park", testLocation1.getAddress());
        assertEquals(43.667709, testLocation1.getLatitude());
        assertEquals(-79.394775, testLocation1.getLongitude());
    }

    @Test
    public void toStringTest() {
        assertEquals("Royal Ontario Museum: 100 Queens Park, 43.667709, -79.394775", testLocation1.toString());
    }
}
