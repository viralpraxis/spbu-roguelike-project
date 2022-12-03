package models;

public class Mob extends GameObject {
    protected int baseHealth;
    protected int basePower;
    protected int health;
    protected int power;
    private MobBehavior mobBehavior;
    private int experience;
    private int level;
    private int experienceForKill;

    public Mob() {}

    public Mob(int posX, int posY, int health, int power) {
        this.posX = posX;
        this.posY = posY;
        this.baseHealth = health;
        this.basePower = power;
        this.steppable = false;
        this.health = baseHealth;
        this.power = basePower;

        this.visible = true;

        mobBehavior = new CowardMobBehavior();
        experience = 0;
        level = 0;
        experienceForKill = 51;
    }

    /**
     * Set behavior to this mob.
     *
     * @param behavior String description of behavior of the mob. Three types of behavior are allowed:
     *                 "aggressive", "coward", "passive".
     */
    public void setMobBehavior(String behavior) {
        switch (behavior) {
            case ("aggressive"):
                mobBehavior = new AggressiveMobBehavior();
                break;
            case ("coward"):
                mobBehavior = new CowardMobBehavior();
                break;
            case ("passive"):
                mobBehavior = new PassiveMobBehavior();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    /**
     * Moves a move relative to its current position.
     *
     * @param dx relative change in position in x direction
     * @param dy relative change in position in y direction
     */
    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
    }

    /**
     * Increases experience of this mob. Move mob to the next level if it has enough experience.
     *
     * @param value amount of experience to add
     */
    public void addExperience(int value) {
        experience += value;

        int newLevel = experience / 100;
        if (newLevel > level) {
            health = baseHealth;

            int healthIncreasePerLevel = (int) (baseHealth * 0.1);
            int powerIncreasePerLevel = (int) (basePower * 0.01);
            health += healthIncreasePerLevel * newLevel;
            power += powerIncreasePerLevel * newLevel;
            level = newLevel;
        }
    }

    /**
     * Get experience that is granted for killing this mob.
     *
     * @return amount of experience
     */
    public int getExperienceForKill() {
        return experienceForKill;
    }

    @Override
    public void handleStepFrom(Mob mob) {
        mob.health -= power;
    }

    /**
     * Get health of the mob.
     *
     * @return int value of health of the mob
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get attack power of the mob.
     *
     * @return int value of power of the mob
     */
    public int getPower() {
        return power;
    }

    /**
     * This method moves mob according to its move behavior, location of another mob (usually player) and room it is
     * located in.
     *
     * @param player Mob according to which next move decision for this mob should be made
     * @param room Room in which this mob is located in
     */
    public void makeNextMove(Player player, Room room, CollisionsResolver collisionsResolver) {
        mobBehavior.makeNextMove(player, this, room, collisionsResolver);
    }
}
