package models;

import java.util.Random;

public class Player extends Mob {
    private final String name;

    private final static int DEFAULT_HEALTH = 100;
    private final static int DEFAULT_POWER = 100;
    private final static String DEFAULT_NAME = "John";
    private final static double DEFAULT_BASH_CHANCE = 0.5;
    private double bashChance;
    private Random random;

    public Player(int posX, int posY, int health, int power, String name, double bashChance) {
        super(posX, posY, health, power);

        this.name = name;
        this.visible = true;
        this.bashChance = bashChance;

        random = new Random();
    }

    public Player(int posX, int posY, int health, int power, String name) {
        this(posX, posY, health, power, name, DEFAULT_BASH_CHANCE);
    }

    public Player(int posX, int posY) {
        this(posX, posY, DEFAULT_HEALTH, DEFAULT_POWER, DEFAULT_NAME);
    }

    /**
     * Moves player to the point (x, y) on the map.
     *
     * @param x new x coordinate of the player
     * @param y new y coordinate of the player
     */
    public void moveAbsolute(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public void handleStepFrom(Mob mob) {
        super.handleStepFrom(mob);
        if (random.nextDouble() < bashChance) {
            mob.makeConfused();
        }
    }

    public String getName() {
        return name;
    }

    public double getBashChance() {
        return bashChance;
    }

    @Override
    public String toString() {
        return String.format("Player((%d, %d), H%s, S%s, %s, %f)", posX, posY, health, power, name, bashChance);
    }
}
