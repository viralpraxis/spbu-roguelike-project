package models;

public abstract class GameObject {
    protected String title;
    protected char[][] representation; 
    protected int posX;
    protected int posY;
    protected boolean steppable;
    protected boolean visible;

    public char[][] getRepresentation() {
        return representation;
    }

    public int posX() {
        return posX;
    }

    public int posY() {
        return posY;
    }

    public boolean isVisible() {
        return visible;
    }

    public abstract void stepOn(Mob mob);
}
