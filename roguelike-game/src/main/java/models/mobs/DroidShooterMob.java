package models.mobs;

/**
 * Concrete descendant of WeakMob class that is used by science-fiction factory.
 */
public class DroidShooterMob extends WeakMob implements ClonableMob {
    private float replicationProb = 0.05f;

    public DroidShooterMob(int posX, int posY) {
        super(posX, posY, 121, 40);
    }

    private DroidShooterMob(int posX, int posY, int health, int strength) {
        super(posX, posY, health, strength);
    }

    @Override
    public DroidShooterMob clone() {
        var copyMob = new DroidShooterMob(this.posX(), this.posY(), this.getHealth(), this.getPower());
        return copyMob;
    }

    @Override
    public float getReplicationProb() {
        return replicationProb;
    }
}
