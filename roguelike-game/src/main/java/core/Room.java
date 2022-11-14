package core;

import models.Door;
import models.GameObject;
import models.Item;
import models.Mob;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private final int posX;
    private final int posY;
    private final int size;
    private boolean visible;
    private List<GameObject> objects;

    public Room(boolean visible, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.visible = visible;
        this.size = 10;

        // TODO: Add random level generation
        if (posX == 0) {
            objects = new ArrayList<>(2);
            objects.add(new Door(9, 2, 35, 15, 1));
            objects.add(new Item(2, 2, "Blade of the abyss"));
        }
        if (posX == 30) {
            objects = new ArrayList<>(2);
            objects.add(new Door(39, 12, 5, 5, 0));
            objects.add(new Item(37, 13, "Sacrificial lamb"));
        }
    }

    /**
     * Handles next move of a mob.
     *
     * @param mob Mob whose move we should handle
     * @param dx  relative change of position in x direction
     * @param dy  relative change of position in y direction
     * @return -1 if after the turn mob stays in the same room; id of the next room otherwise
     */
    public int makeMove(Mob mob, int dx, int dy) {
        GameObject objToStep = null;

        int x = mob.posX() + dx;
        int y = mob.posY() + dy;

        int objId = 0;
        for (GameObject obj : objects) {
            if (obj.posX() == x && obj.posY() == y) {
                objToStep = obj;
                break;
            }
            objId += 1;
        }

        int retValue = -1;

        if (objToStep != null && objToStep instanceof Door) {
            // We stepped on a door
            retValue = ((Door) objToStep).leadsTo();
        }

        if (objToStep != null && objToStep instanceof Item) {
            objects.remove(objId);
        }

        if (!nextStepOutsideRoom(mob, dx, dy))
            mob.move(dx, dy);

        if (objToStep != null) {
            objToStep.stepOn(mob);
        }

        return retValue;
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

    private boolean nextStepOutsideRoom(Mob mob, int dx, int dy) {
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
}
