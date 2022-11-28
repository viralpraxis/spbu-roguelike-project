package models.mobs;


/**
 * This class represents a concrete factory that can create science-fiction mobs.
 */
public class SFMobGeneratorFactory implements MobGeneratorFactory {
    @Override
    public StrongMob createStrongMob(int posX, int posY) {
        return new CyborgChainsawMob(posX, posY);
    }

    @Override
    public WeakMob createWeakMob(int posX, int posY) {
        return new DroidShooterMob(posX, posY);
    }
}
