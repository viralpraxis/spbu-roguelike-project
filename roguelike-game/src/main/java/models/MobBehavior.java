package models;

interface MobBehavior {
    /**
     * This function moves mob according to its behavior.
     *
     * @param player             main Player of the game
     * @param mob                Mob that should be moved
     * @param room               Room in which mob is located
     * @param collisionsResolver
     */
    public void makeNextMove(Player player, Mob mob, Room room, CollisionsResolver collisionsResolver);
}