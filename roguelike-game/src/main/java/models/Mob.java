package models;

public class Mob extends GameObject {
    protected int baseHealth;
    protected int basePower;
    protected int health;
    protected int power;

    public Mob(int posX, int posY, int health, int power) {
        this.posX = posX;
        this.posY = posY;
        this.baseHealth = health;
        this.basePower = power;
        this.steppable = false;
        this.health = baseHealth;
        this.power = basePower;

        this.representation = new char[1][1];
        this.representation[0][0] = 'm';
        this.visible = true;
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

    @Override
    public void stepOn(Mob mob) {
        // TODO: Attack character or another mob
        mob.health -= power;
        health -= mob.power;
        if (health <= 0)
            destroyed = true;
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
}
