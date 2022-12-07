package models.mobs;

/**
 * Prototype pattern interface that allows mob cloning.
 */
public interface ClonableMob {
    /**
     * Clone mob.
     *
     * @return A copy of a mob
     */
    public ClonableMob clone();

    /**
     * Get probability of mob cloning at each change of game state.
     *
     * @return probbility of replication
     */
    public float getReplicationProb();
}
