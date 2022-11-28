package models.mobs;


/**
 * This class represents a concrete factory that can create fantasy mobs.
 */
public class FantasyMobGeneratorFactory implements MobGeneratorFactory {
    @Override
    public StrongMob createStrongMob(int posX, int posY) {
        return new DragonMob(posX, posY);
    }

    @Override
    public WeakMob createWeakMob(int posX, int posY) {
        return new SkeletonMob(posX, posY);
    }
}
