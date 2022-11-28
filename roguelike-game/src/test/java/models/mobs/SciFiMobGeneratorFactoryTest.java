package test.models.mobs;

import java.util.Random;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.mobs.SciFiMobGeneratorFactory;
import models.mobs.Mob;
import models.mobs.CyborgChainsawMob;
import models.mobs.DroidShooterMob;

public class SciFiMobGeneratorFactoryTest {
    private Random random = new Random();

    @Test
    public void testCreateStrongMob() {
        var factory = new SciFiMobGeneratorFactory();
        int posX = randomInt();
        int posY = randomInt();
        Mob mob = factory.createStrongMob(posX, posY);

        assertEquals(mob.posX(), posX);
        assertEquals(mob.posY(), posY);

        assertTrue(mob instanceof CyborgChainsawMob);
    }

    @Test
    public void testCreateWeakMob() {
        var factory = new SciFiMobGeneratorFactory();
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
