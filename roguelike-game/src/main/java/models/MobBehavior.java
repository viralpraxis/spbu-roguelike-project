package models;

interface MobBehavior {
    /**
     * This function moves mob according to its behavior.
     * @param player main Player of the game
     * @param mob Mob that should be moved
     * @param room Room in which mob is located
     */
    public void makeNextMove(Mob player, Mob mob, Room room);
}