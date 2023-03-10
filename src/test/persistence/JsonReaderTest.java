package persistence;

import model.Location;
import model.Map;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ui.MapFunctions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Map map = reader.read();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMapUnvisited() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMap.json");
        try {
            Map map = reader.read();
            assertEquals("Map name", map.getCity());
            assertEquals(0, map.getSize(map.getUnvisited()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyMapVisited() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMap.json");
        try {
            Map map = reader.read();
            assertEquals("Map name", map.getCity());
            assertEquals(0, map.getSize(map.getVisited()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMapUnvisited() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMap.json");
        try {
            Map map = reader.read();
            assertEquals("Map name", map.getCity());
            List<Location> locations = map.getLocations(map.getUnvisited());
            assertEquals(2, locations.size());
            checkLocation("ONE", "ONE Street", 1, 1, locations.get(0));
            checkLocation("TWO", "TWO Street", 2, 2, locations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}








