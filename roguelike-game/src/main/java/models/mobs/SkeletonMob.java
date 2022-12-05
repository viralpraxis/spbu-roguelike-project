package models.mobs;


/**
 * Concrete descendant of WeakMob class that is used by fantasy factory.
 */
public class SkeletonMob extends WeakMob {
    public SkeletonMob(int posX, int posY) {
        super(posX, posY, 105, 30);
    }
}
