package test.models;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

import models.Door;
import models.Mob;

class DoorTest {
    private Door door;
    private Mob mob;
    private Random random = new Random();

    @Test
    public void testLeadsTo() {
        int roomLeadsTo = randomInt();
        door = new Door(randomInt(), randomInt(), randomInt(), randomInt(), roomLeadsTo);

        assertEquals(door.leadsTo(), roomLeadsTo);
    }

    @Test
    public void testStepOn() {
        door = new Door(randomInt(), randomInt(), randomInt(), randomInt(), randomInt());
        mob = new Mob(randomInt(), randomInt(), randomInt(), randomInt());

        door.stepOn(mob);

        assertEquals(mob.posX(), door.getLeadsToX());
        assertEquals(mob.posY(), door.getLeadsToY());
    }

    @Test
    public void testGetLeadsToX() {
        int doorLeadsToX = randomInt();
        door = new Door(randomInt(), randomInt(), doorLeadsToX, randomInt(), randomInt());

        assertEquals(door.getLeadsToX(), doorLeadsToX);
    }

    @Test
    public void testGetLeadsToY() {
        int doorLeadsToY = randomInt();
        door = new Door(randomInt(), randomInt(), randomInt(), doorLeadsToY, randomInt());

        assertEquals(door.getLeadsToY(), doorLeadsToY);
    }

    private int randomInt() {
        return random.nextInt();
    }
}
