package persistence;

import model.Location;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonTest {

    protected void checkLocation(String name, String address, double latitude, double longitude, Location location) {
        assertEquals(name, location.getName());
        assertEquals(address, location.getAddress());
        assertEquals(latitude, location.getLatitude());
        assertEquals(longitude, location.getLongitude());
    }

}
