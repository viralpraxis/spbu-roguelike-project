package core;

import models.Door;
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

        if (posX == 0) {
            objects = new GameObject[1];
            objects[0] = new Door(9, 2, 35, 15, 1);
        }
        if (posX == 30) {
            objects = new GameObject[1];
            objects[0] = new Door(39, 12, 5, 5, 0);
        }
    }

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
