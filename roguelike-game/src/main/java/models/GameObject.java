package models;

public class GameObject {
    protected String title;
    protected char[][] representation; 
    protected int posX;
    protected int posY;

    public GameObject(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public char[][] getRepresentation() {
        return representation;
    }

    public int posX() {
        return posX;
    }

    public int posY() {
        return posY;
    }
}
