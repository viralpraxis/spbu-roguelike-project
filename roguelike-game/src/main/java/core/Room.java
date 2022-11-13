package core;

import models.GameObject;

public class Room {
    private boolean visible;
    private GameObject[] objects;
    private final int posX;
    private final int posY;  
    private final int size;

    public Room(boolean visible, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.visible = visible;
        this.size = 10;
    }

    public void makeVisible() {
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public int getSize() {
        return size;
    }
}
