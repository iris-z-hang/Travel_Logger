package persistence;

import model.Location;
import model.Map;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Map map = new Map("My map");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMapUnvisited() {

        try {
            Map map = new Map("My map");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
            map = reader.read();
            assertEquals("My map", map.getCity());
            assertEquals(0, map.getSize(map.getUnvisited()));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyMapVisited() {
        try {
            Map map = new Map("My map");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMap.json");
            map = reader.read();
            assertEquals("My map", map.getCity());
            assertEquals(0, map.getSize(map.getVisited()));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
    @Test
    void testWriterGeneralWorkroomUnvisited() {
        try {
            Map map = new Map("My map");
            Location location1 = new Location("ONE", "ONE street", 1, 1);
            Location location2 = new Location("TWO", "TWO street", 2, 2);

            map.addLocation(map.getUnvisited(), location1);
            map.addLocation(map.getUnvisited(), location2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            map = reader.read();
            assertEquals("My city", map.getCity());
            List<Location> locations = map.getLocations(map.getUnvisited());
            assertEquals(2, map.getSize(map.getUnvisited()));
            checkLocation("ONE", "ONE Street", 1, 1, locations.get(0));
            checkLocation("TWO", "TWO Street", 2, 2, locations.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralWorkroomVisited() {
        try {
            Map map = new Map("My map");
            Location location1 = new Location("ONE", "ONE street", 1, 1);
            Location location2 = new Location("TWO", "TWO street", 2, 2);

            map.addLocation(map.getVisited(), location1);
            map.addLocation(map.getVisited(), location2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMap.json");
            writer.open();
            writer.write(map);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMap.json");
            map = reader.read();
            assertEquals("My city", map.getCity());
            List<Location> locations = map.getLocations(map.getVisited());
            assertEquals(2, map.getSize(map.getVisited()));
            checkLocation("ONE", "ONE Street", 1, 1, locations.get(0));
            checkLocation("TWO", "TWO Street", 2, 2, locations.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
