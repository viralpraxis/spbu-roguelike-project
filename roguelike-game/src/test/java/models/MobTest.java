package test.models;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.Mob;


class MobTest {
    private Mob mob;
    private Random random = new Random();

    @Test
    public void testMove() {
        int posX = randomInt();
        int posY = randomInt();
        mob = new Mob(posX, posY, randomInt(), randomInt());

        int deltaX = randomInt(100);
        int deltaY = randomInt(100);

        mob.move(deltaX, deltaY);

        assertEquals(posX + deltaX, mob.posX());
        assertEquals(posY + deltaY, mob.posY());
    }

    @Test
    public void testGetHealth() {
        int mobHealth = randomInt();
        mob = new Mob(randomInt(), randomInt(), mobHealth, randomInt());

        assertEquals(mobHealth, mob.getHealth());
    }

    @Test
    public void testGetPower() {
        int mobPower = randomInt();
        mob = new Mob(randomInt(), randomInt(), randomInt(), mobPower);

        assertEquals(mobPower, mob.getPower());
    }


    private int randomInt() {
        return random.nextInt();
    }

    private int randomInt(int upperbound) {
        return random.nextInt(upperbound);
    }
}
