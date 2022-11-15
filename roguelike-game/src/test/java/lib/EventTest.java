package test.lib;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import lib.Event;

class EventTest {
    @Test
    public void testGetType() {
        Event event = new Event(Event.Type.GAME_EXIT);

        assertEquals(event.getType(), Event.Type.GAME_EXIT);
        assertEquals("1", "2");
    }
}
