package test.lib;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import org.hamcrest.core.IsInstanceOf;
import static org.junit.matchers.JUnitMatchers.*;

import core.Level;
import core.Room;
import models.Door;
import lib.MapGenerator;

class MapGeneratorTest {
    // @Test
    // public void testGenerate() {
    //     Level[] levels = perform(50, 50);
    //     assertEquals(1, levels.length);
    //
    //     Level level = levels[0];
    //     assertTrue(level.getRooms().length > 0);
    //
    //     for (Room room : level.getRooms()) {
    //         assertTrue(room.getRoomContent().size() > 0);
    //         assertThat(Arrays.asList(room.getRoomContent()), hasItem(IsInstanceOf.instanceOf(Door.class)));
    //     }
    // }
    //
    // private Level[] perform(int width, int height) {
    //     return MapGenerator.generate(width, height);
    // }
}
