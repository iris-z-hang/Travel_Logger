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
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMapUnvisited() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
        try {
            Map map = reader.read();
            assertEquals("city name", map.getCity());
            assertEquals(0, map.getSize(map.getUnvisited()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptyMapVisited() {
        JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
        try {
            Map map = reader.read();
            assertEquals("city name", map.getCity());
            assertEquals(0, map.getSize(map.getVisited()));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMapUnvisited() throws IOException {
        JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
        try {
            Map map = reader.read();
            assertEquals("city name", map.getCity());
            List<Location> locations = map.getLocations(map.getUnvisited());
            assertEquals(2, locations.size());
            checkLocation("ONE", "ONE street", 1, 1, locations.get(0));
            checkLocation("TWO", "TWO street", 2, 2, locations.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}








