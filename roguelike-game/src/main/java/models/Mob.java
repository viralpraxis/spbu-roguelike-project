package models;

public class Mob extends GameObject {
    private int health;
    private int power;

    public Mob(int posX, int posY, int health, int power) {
        this.posX = posX;
        this.posY = posY;
        this.health = health;
        this.power = power;
        this.steppable = false;
    }

    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
    }

    @Override
    public void stepOn(Mob mob) {
        // TODO: Attack character or another mob
    }
}
