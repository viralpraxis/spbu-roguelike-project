package core;

import models.Door;
import models.Item;
import models.GameObject;
import models.Mob;

public class Room {
    private final int posX;
    private final int posY;
    private final int size;
    private boolean visible;
    private GameObject[] objects;

    public Room(boolean visible, int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.visible = visible;
        this.size = 10;

        // TODO: Add random level generation
        if (posX == 0) {
            objects = new GameObject[2];
            objects[0] = new Door(9, 2, 35, 15, 1);
            objects[1] = new Item(2, 2);
        }
        if (posX == 30) {
            objects = new GameObject[2];
            objects[0] = new Door(39, 12, 5, 5, 0);
            objects[1] = new Item(37, 13);
        }
    }

    /**
     * Handles next move of a mob.
     * @param mob Mob whose move we should handle
     * @param dx relative change of position in x direction
     * @param dy relative change of position in y direction
     * @return -1 if after the turn mob stays in the same room; id of the next room otherwise
     */
    public int makeMove(Mob mob, int dx, int dy) {
        GameObject objToStep = null;

        int x = mob.posX() + dx;
        int y = mob.posY() + dy;

        for (GameObject obj : objects) {
            if (obj.posX() == x && obj.posY() == y) {
                objToStep = obj;
                break;
            }
        }

        int retValue = -1;

        if (objToStep != null && objToStep instanceof Door) {
            // We stepped on a door
            retValue = ((Door) objToStep).leadsTo();
        }

        if (!nextStepOutsideRoom(mob, dx, dy))
            mob.move(dx, dy);

        if (objToStep != null) {
            objToStep.stepOn(mob);
        }

        return retValue;
    }

    public void makeVisible() {
        visible = true;
    }

    public boolean isVisible() {
        return visible;
    }

    public int posX() {
        return posX;
    }

    public int posY() {
        return posY;
    }

    public int getSize() {
        return size;
    }

    /**
     * Gets all objects that are in the room.
     * @return An array of GameObjects that are in the room
     */
    public GameObject[] getRoomContent() {
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
