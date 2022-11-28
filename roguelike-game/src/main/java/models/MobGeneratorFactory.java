package models;

/**
 * Abstract factory that can create different types of mobs.
 */
public interface MobGeneratorFactory {
    /**
     * Generate a StrongMob at a given position.
     *
     * @param posX x position where mob should be generated
     * @param posY y position where mob should be generated
     * @return generated instance of a StrongMob
     */
    public StrongMob createStrongMob(int posX, int posY);

    /**
     * Generate a WeakMob at a given position.
     *
     * @param posX x position where mob should be generated
     * @param posY y position where mob should be generated
     * @return generated instance of a WeakMob
     */
    public WeakMob createWeakMob(int posX, int posY);
}
