package models.mobs;


/**
 * Concrete descendant of StrongMob class that is used by fantasy factory.
 */
public class DragonMob extends StrongMob {
    public DragonMob(int posX, int posY) {
        super(posX, posY, 150, 45);
    }
}
