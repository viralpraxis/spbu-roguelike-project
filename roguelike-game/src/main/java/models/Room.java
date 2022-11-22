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
     * @param mob Mob whose move we should handle
     * @param dx  relative change of position in x direction
     * @param dy  relative change of position in y direction
     * @return -1 if after the turn mob stays in the same room; id of the next room otherwise
     */
    public int makeMove(Mob mob, int dx, int dy) {
        GameObject objToStep = null;

        int x = mob.posX() + dx;
        int y = mob.posY() + dy;

        moveMobsInRoom(mob);

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

        int retValue = -1;

        if (objToStep != null && objToStep instanceof Door) {
            // We stepped on a door
            retValue = ((Door) objToStep).leadsTo();
        }

        if (objToStep != null) {
            objToStep.stepOn(mob);

            if (objToStep instanceof Mob) {
                objects.add(new ConfusedMob((Mob) objToStep));
                objects.remove(objId);
            }
        }

        if (objToStep != null && objToStep.isDestroyed()) {
            objects.remove(objId);
            objToStep = null;
        }

        if ((!nextStepOutsideRoom(mob, dx, dy) && objToStep == null) || (objToStep != null && objToStep.isSteppable()))
            mob.move(dx, dy);

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

    private void moveMobsInRoom(Mob mob) {
        List<GameObject> deleteList = new LinkedList<>();

        for (GameObject object: objects) {
            if (object instanceof Mob) {
                ((Mob) object).makeNextMove(mob, this);
                if (((Mob) object).isDestroyed())
                    deleteList.add(object);
            }
        }

        for (GameObject destroyedObj: deleteList) {
            objects.remove(destroyedObj);
        }
    }
}
