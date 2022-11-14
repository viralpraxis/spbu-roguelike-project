package models;

public abstract class GameObject {
    protected String title;
    protected char[][] representation;
    protected int posX;
    protected int posY;
    protected boolean steppable;
    protected boolean visible;

    /**
     * Gets the representation of how an object should be rendered.
     *
     * @return 2-d char array which graphically represents an object
     */
    public char[][] getRepresentation() {
        return representation;
    }

    /**
     * Gets the x coordinate of an object in global coordinate system.
     *
     * @return the x coordinate
     */
    public int posX() {
        return posX;
    }

    /**
     * Gets the y coordinate of an object in global coordinate system.
     *
     * @return the y coordinate
     */
    public int posY() {
        return posY;
    }

    /**
     * Checks whether an object is visible.
     *
     * @return true if object is visible, false - otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Updates the state of a mob which stepped on this object.
     *
     * @param mob mob which stepped on this object
     */
    public abstract void stepOn(Mob mob);
}
