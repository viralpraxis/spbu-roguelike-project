package test.models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.GameObject;
import models.Level;
import models.Room;
import models.Mob;
import models.Player;

class LevelTest {
    private Level level;
    private Random random = new Random();

    @Test
    public void testIsFinished_whenFinished() {
        Player player = new Player(randomInt(), randomInt());
        level = new Level(new Room[]{buildRoomWithoutMobs(), buildRoomWithoutMobs()}, player);

        assertEquals(true, level.isFinished());
    }

    @Test
    public void testIsFinished_whenNotFinished() {
        Player player = new Player(randomInt(), randomInt());
        level = new Level(new Room[]{buildRoomWithMobs(), buildRoomWithoutMobs()}, player);

        assertEquals(false, level.isFinished());
    }

    @Test
    public void testGetRooms() {
        Player player = new Player(randomInt(), randomInt());
        Room[] rooms = new Room[]{buildRoomWithMobs(), buildRoomWithoutMobs()};
        level = new Level(rooms, player);

        assertEquals(rooms, level.getRooms());
    }

    @Test
    public void testGetPlayer() {
        Player player = new Player(randomInt(), randomInt());
        Room[] rooms = new Room[]{buildRoomWithMobs(), buildRoomWithoutMobs()};
        level = new Level(rooms, player);

        assertEquals(player, level.getPlayer());
    }

    private Room buildRoomWithMobs() {
        List<GameObject> roomObjects;
        Room room;

        roomObjects = new ArrayList<>();
        roomObjects.add(new Player(randomInt(), randomInt()));
        roomObjects.add(new Mob(randomInt(), randomInt(), randomInt(), randomInt()));
        room = new Room(
            false,
            randomInt(),
            randomInt(),
            roomObjects
        );

        return room;
    }

    private Room buildRoomWithoutMobs() {
        return new Room(
            false,
            randomInt(),
            randomInt(),
            new ArrayList<GameObject>()
        );
    }

    private int randomInt() {
        return random.nextInt();
    }
}
