package test.models.mobs;

import java.util.Random;

import models.mobs.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FantasyMobGeneratorFactoryTest {
    private Random random = new Random();

    @Test
    public void testCreateStrongMob() {
        var factory = new FantasyMobGeneratorFactory();
        int posX = randomInt();
        int posY = randomInt();
        Mob mob = factory.createStrongMob(posX, posY);

        assertEquals(mob.posX(), posX);
        assertEquals(mob.posY(), posY);

        assertTrue(mob instanceof DragonMob);
    }

    @Test
    public void testCreateWeakMob() {
        var factory = new FantasyMobGeneratorFactory();
        int posX = randomInt();
        int posY = randomInt();
        Mob mob = factory.createWeakMob(posX, posY);

        assertEquals(mob.posX(), posX);
        assertEquals(mob.posY(), posY);

        assertTrue(mob instanceof SkeletonMob);
    }

    private int randomInt() {
        return random.nextInt();
    }
}
