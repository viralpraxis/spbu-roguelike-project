package models;

import java.util.List;
import java.util.LinkedList;

public class Room {
    private final int posX;
    private final int posY;
    private final int size;
    private boolean visible;
    private List<GameObject> objects;

    public Room(boolean visible, int posX, int posY, List<GameObject> objects, int size) {
        this.visible = visible;
        this.posX = posX;
        this.posY = posY;
        this.objects = objects;
        this.size = size;
    }

    /**
     * Checks whether this room contains Mobs.
     *
     * @return true if this room contains Mobs, false - otherwise
     */
    public boolean containsMobs() {
        for (GameObject object : objects) {
            if (object instanceof Mob) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles next move of a mob.
     *
     * @param player Main player of the game
     * @param dx  relative change of position in x direction
     * @param dy  relative change of position in y direction
     * @return -1 if after the turn mob stays in the same room; id of the next room otherwise
     */
    public void makeMove(Player player, int dx, int dy, CollisionsResolver colsResolver) {
        GameObject objToStep = null;

        int x = player.posX() + dx;
        int y = player.posY() + dy;

        moveMobsInRoom(player, colsResolver);

        int objId = 0;
        int idx = 0;
        for (GameObject obj : objects) {
            if (obj.posX() == x && obj.posY() == y) {
                objToStep = obj;
                objId = idx;
                if (objToStep instanceof Mob) {
                    break;
                }
            }
            idx += 1;
        }

        if (objToStep != null) {
            colsResolver.addCollision(objToStep);
        }

        colsResolver.setDesirablePlayerPos(x, y);
    }

    /**
     * Makes room visible, so it will be rendered.
     */
    public void makeVisible() {
        visible = true;
    }

    /**
     * Checks whether the room is visible.
     *
     * @return true if room is visible, false - otherwise
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Gets x position of the top left corner of the room.
     *
     * @return x position of the corner
     */
    public int posX() {
        return posX;
    }

    /**
     * Gets y position of the top left corner of the room.
     *
     * @return y position of the corner
     */
    public int posY() {
        return posY;
    }

    /**
     * Gets the outer size of the room.
     *
     * @return size of the room
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets all objects that are in the room.
     *
     * @return A list of GameObjects that are in the room
     */
    public List<GameObject> getRoomContent() {
        return objects;
    }

    /**
     * Checks whether this room contains mob at a given position.
     *
     * @param x x position of a cell in which to check for mob
     * @param y y position of a cell in which to check for mob
     * @return true if room contains mob at the cell (x,y), false - otherwise
     */
    public boolean containsMobAtPos(int x, int y) {
        for (GameObject obj : objects) {
            if (obj.posX() == x && obj.posY() == y && obj instanceof Mob) {
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes an instance of GameObject from the room.
     *
     * @param gameObject instance of GameObject that should be deleted
     */
    public void deleteGameObject(GameObject gameObject) {
        objects.remove(gameObject);
    }

    /**
     * Adds an instance of GameObject to the room.
     *
     * @param gameObject an instance of GameObject to be added
     */
    public void addGameObject(GameObject gameObject) {
        objects.add(gameObject);
    }

    /**
     * Checks whether next step of a mob will move him outside the room.
     *
     * @param mob mob whose new position will be checked
     * @param dx relative change in position in x-axis
     * @param dy relative change in position in y-axis
     * @return true if new position is outside the room, false - otherwise
     */
    public boolean nextStepOutsideRoom(Mob mob, int dx, int dy) {
        if (mob.posX() + dx + 1 >= posX + size)
            return true;
        if (mob.posX() + dx <= posX)
            return true;
        if (mob.posY() + dy + 1 >= posY + size)
            return true;
        if (mob.posY() + dy <= posY)
            return true;
        return false;
    }

    /**
     * Checks that the given position is inside the room.
     *
     * @param x x coordinate of position
     * @param y y coordinate of position
     * @return true if given position is inside the room, false - otherwise
     */
    public boolean posInsideRoom(int x, int y) {
        if (x + 1 >= posX + size)
            return false;
        if (x <= posX)
            return false;
        if (y + 1 >= posY + size)
            return false;
        if (y <= posY)
            return false;
        return true;
    }

    private void moveMobsInRoom(Player player, CollisionsResolver collisionsResolver) {
        for (GameObject object: objects) {
            if (object instanceof Mob) {
                ((Mob) object).makeNextMove(player, this, collisionsResolver);
            }
        }
    }
}
