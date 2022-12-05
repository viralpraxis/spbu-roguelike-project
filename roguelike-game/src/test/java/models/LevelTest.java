package test.models;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.GameObject;
import models.Level;
import models.Room;
import models.mobs.Mob;
import models.mobs.Player;

class LevelTest {
    private Level level;
    private Random random = new Random();

    @Test
    public void testEmptyRoomRandomMovement() {
        List<GameObject> gameObjects = new ArrayList<>();
        Room room = new Room(true, 10, 10, gameObjects, 100);
        Player player = new Player(10, 10);
        Level level = new Level(new Room[]{room}, player);

        try {
            int dx, dy;
            for (int i = 0; i < 100; i++) {
                dx = (randomBoolean() ? 1 : -1);
                dy = (randomBoolean() ? 1 : -1);

                level.makeNextMove(player, dx, dy);
            }
        } catch (Exception e) {
            assertTrue(false);
        }

        assertTrue(level.isFinished());
    }

    @Test
    public void testFightWithAggresiveMob() {
        List<GameObject> gameObjects = new ArrayList<>();
        Mob mob = new Mob(15, 15, 20, 20);
        mob.setMobBehavior("aggressive");
        gameObjects.add(mob);

        Room room = new Room(true, 10, 10, gameObjects, 100);
        Player player = new Player(11, 11, 100, 10, "Edsger W. Dijkstra", 0.0);
        System.out.println(player);
        Level level = new Level(new Room[]{room}, player);

        for (int i = 0; i < 1000; i++) {
            level.makeNextMove(player, 0, 0);
        }

        assertEquals(60, player.getHealth());
        assertEquals(0, mob.getHealth());
    }

    @Test
    public void testFightWithPassiveMob() {
        List<GameObject> gameObjects = new ArrayList<>();
        int initialMobX = 15;
        int initialMobY = 15;
        Mob mob = new Mob(initialMobX, initialMobY, 20, 20);
        mob.setMobBehavior("passive");
        gameObjects.add(mob);

        Room room = new Room(true, 10, 10, gameObjects, 100);
        Player player = new Player(11, 11, 100, 10, "Edsger W. Dijkstra", 0.0);
        Level level = new Level(new Room[]{room}, player);

        int dx, dy;
        for (int i = 0; i < 1000; i++) {
            dx = Integer.signum(mob.posX() - player.posX());
            dy = Integer.signum(mob.posY() - player.posY());
            level.makeNextMove(player, dx, dy);

            assertEquals(initialMobX, mob.posX());
            assertEquals(initialMobY, mob.posY());
        }

        assertEquals(60, player.getHealth());
        assertEquals(0, mob.getHealth());
    }

    @Test
    public void testFightWithManyAggresiveMobs() {
        int mobsCount = 2;

        List<GameObject> gameObjects = new ArrayList<>();
        Mob[] mobs = new Mob[2];

        for (int i = 0; i < mobs.length; i++) {
            Mob mob = new Mob(10 + i * 10, 10 + i * 10, 100, 50);
            mob.setMobBehavior("aggressive");
            mobs[i] = mob;
            gameObjects.add(mob);
        }

        Room room = new Room(true, 10, 10, gameObjects, 100);
        Player player = new Player(15, 15, 5, 0, "Edsger W. Dijkstra", 0.0);
        Level level = new Level(new Room[]{room}, player);

        for (int i = 0; i < 1000; i++) {
            level.makeNextMove(player, 0, 0);
        }

        assertEquals(0, player.getHealth());
        assertEquals(2, gameObjects.size());
        for (int i = 0; i < mobs.length; i++) {
            assertEquals(100, mobs[i].getHealth());
        }
    }

    @Test
    public void testFightWithCowardMob() {
      List<GameObject> gameObjects = new ArrayList<>();
      Mob mob = new Mob(50, 50, 20, 20);
      mob.setMobBehavior("coward");
      gameObjects.add(mob);

      Room room = new Room(true, 10, 10, gameObjects, 100);
      Player player = new Player(11, 11, 100, 10, "Edsger W. Dijkstra", 0.0);
      Level level = new Level(new Room[]{room}, player);

      int dx, dy;
      for (int i = 0; i < 1000; i++) {
          dx = Integer.signum(mob.posX() - player.posX());
          dy = Integer.signum(mob.posY() - player.posY());
          level.makeNextMove(player, dx, dy);
      }

      assertEquals(50, player.posX());
      assertEquals(108, player.posY());

      assertEquals(50, mob.posX());
      assertEquals(108, mob.posY());

      assertEquals(60, player.getHealth());
      assertEquals(0, mob.getHealth());
    }

    @Test
    public void testFightWithConfusedMob() {
      List<GameObject> gameObjects = new ArrayList<>();
      Mob mob = new Mob(50, 50, 20, 20);
      mob.setMobBehavior("coward");
      gameObjects.add(mob);

      Room room = new Room(true, 10, 10, gameObjects, 100);
      Player player = new Player(11, 11, 100, 10, "Edsger W. Dijkstra", 50.0);
      Level level = new Level(new Room[]{room}, player);

      int dx, dy;
      for (int i = 0; i < 1000; i++) {
          dx = Integer.signum(mob.posX() - player.posX());
          dy = Integer.signum(mob.posY() - player.posY());
          level.makeNextMove(player, dx, dy);
      }

      assertEquals(50, player.posX());
      assertEquals(108, player.posY());

      assertEquals(50, mob.posX());
      assertEquals(108, mob.posY());

      assertTrue(player.getHealth() > 0);
      assertEquals(0, mob.getHealth());
    }

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
            roomObjects,
            10
        );

        return room;
    }

    private Room buildRoomWithoutMobs() {
        return new Room(
            false,
            randomInt(),
            randomInt(),
            new ArrayList<GameObject>(),
            10
        );
    }

    private boolean randomBoolean() {
        return random.nextBoolean();
    }

    private int randomInt() {
        return random.nextInt();
    }
}
