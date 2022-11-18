package models;

interface MobBehavior {
    /**
     * This function moves mob according to the set behavior.
     * @param player main Player of the game
     * @param mob Mob that should be moved
     */
    public void makeNextMove(Player player, Mob mob);
}