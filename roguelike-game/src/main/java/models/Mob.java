package models;

public class Mob extends GameObject {
    private int health;
    private int power;

    public Mob(int posX, int posY, int health, int power) {
        super(posX, posY);
        this.health = health;
        this.power = power;
    }

    public void move(int dx, int dy) {
        posX += dx;
        posY += dy;
    }
}
