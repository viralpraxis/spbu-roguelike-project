package test.models.mobs;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.mobs.SFMobGeneratorFactory;
import models.mobs.Mob;
import models.mobs.CyborgChainsawMob;
import models.mobs.DroidShooterMob;

public class SFMobGeneratorFactoryTest {
    private Random random = new Random();

    @Test
    public void testCreateStrongMob() {
        var factory = new SFMobGeneratorFactory();
        int posX = randomInt();
        int posY = randomInt();
        Mob mob = factory.createStrongMob(posX, posY);

        assertEquals(mob.posX(), posX);
        assertEquals(mob.posY(), posY);

        assertTrue(mob instanceof CyborgChainsawMob);
    }

    @Test
    public void testCreateWeakMob() {
        var factory = new SFMobGeneratorFactory();
        int posX = randomInt();
        int posY = randomInt();
        Mob mob = factory.createWeakMob(posX, posY);

        assertEquals(mob.posX(), posX);
        assertEquals(mob.posY(), posY);

        assertTrue(mob instanceof DroidShooterMob);
    }

    private int randomInt() {
        return random.nextInt();
    }
}
