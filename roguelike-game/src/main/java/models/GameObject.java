package models;

/**
 * This class represents basic object that can be rendered.
 */
public abstract class GameObject {
    protected String title;
    protected int posX;
    protected int posY;
    protected boolean steppable;
    protected boolean visible;
    protected boolean destroyed;

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
    public abstract void wasSteppedBy(Mob mob);

    /**
     * Checks whether someone can step on this object.
     *
     * @return True if someone can step on this object, false - otherwise
     */
    public boolean isSteppable() {
        return steppable;
    }

    /**
     * Checks whether an object is destroyed and should be deleted from the room that contains that object.
     *
     * @return True if this object is destroyed, false - otherwise
     */
    public boolean isDestroyed() {
        return destroyed;
    }
}
