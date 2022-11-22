package test.models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.GameObject;
import models.Room;
import models.Mob;
import models.Player;


class RoomTest {
    private Room room;
    private Random random = new Random();

    @Test
    public void testContainsMobs_whenContains() {
        List<GameObject> roomObjects = new ArrayList<>();
        roomObjects.add(new Player(randomInt(), randomInt()));
        roomObjects.add(new Mob(randomInt(), randomInt(), randomInt(), randomInt()));

        room = new Room(
            false,
            randomInt(),
            randomInt(),
            roomObjects,
            10
        );

        assertEquals(true, room.containsMobs());
    }

    @Test
    public void testContainsMobs_whenDoesNotContain() {
        List<GameObject> roomObjects = new ArrayList<>();
        roomObjects.add(new Player(randomInt(), randomInt()));

        room = new Room(
            false,
            randomInt(),
            randomInt(),
            roomObjects,
            10
        );

        assertEquals(true, room.containsMobs());
    }

    @Test
    public void testMakeVisible() {
        room = new Room(
            false,
            randomInt(),
            randomInt(),
            new ArrayList<GameObject>(),
            10
        );

        room.makeVisible();

        assertEquals(true, room.isVisible());
    }

    @Test
    public void testIsVisible_whenVisible() {
        room = new Room(
            true,
            randomInt(),
            randomInt(),
            new ArrayList<GameObject>(),
            10
        );

        assertEquals(true, room.isVisible());
    }


    @Test
    public void testIsVisible_whenInvisible() {
        room = new Room(
            false,
            randomInt(),
            randomInt(),
            new ArrayList<GameObject>(),
            10
        );

        assertEquals(false, room.isVisible());
    }

    @Test
    public void testGetRoomContent() {
        List<GameObject> roomObjects = new ArrayList<>();
        room = new Room(
            false,
            randomInt(),
            randomInt(),
            roomObjects,
            10
        );

        assertEquals(roomObjects, room.getRoomContent());
    }

    private int randomInt() {
        return random.nextInt();
    }
}
