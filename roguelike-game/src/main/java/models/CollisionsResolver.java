package models;

import java.util.LinkedList;
import java.util.List;

public class CollisionsResolver {
    private List<GameObject> collisions;
    private int roomId;
    private boolean playerDead;
    private int desiredPosX;
    private int desiredPosY;

    public CollisionsResolver(int roomId) {
        this.roomId = roomId;
        playerDead = false;
        collisions = new LinkedList<>();
    }

    public void resolveCollisionsAndMovePlayer(Player player, Room room) {
        boolean steppedOnDoorDuringTurn = false;
        for (GameObject gameObject: collisions) {
            if (gameObject instanceof Door)
                steppedOnDoorDuringTurn = true;
            resolveCollision(player, gameObject, room);
        }

        if (!room.containsMobAtPos(desiredPosX, desiredPosY) && !steppedOnDoorDuringTurn &&
            room.posInsideRoom(desiredPosX, desiredPosY)) {
            player.moveAbsolute(desiredPosX, desiredPosY);
        }
    }

    public void addCollision(GameObject a) {
        collisions.add(a);
    }

    public int getCurrentRoom() {
        return roomId;
    }

    public boolean isPlayerDead() {
        return playerDead;
    }

    public void setDesirablePlayerPos(int x, int y) {
        desiredPosX = x;
        desiredPosY = y;
    }

    private void resolveCollision(Player player, GameObject gameObject, Room room) {
        gameObject.handleStepFrom(player);

        if (gameObject instanceof Mob) {
            Mob mob = (Mob) gameObject;

            player.handleStepFrom(mob);

            if (mob.getHealth() <= 0) {
                mob.makeDestroyed();
            }

            if (mob.isDestroyed()) {
                player.addExperience(mob.getExperienceForKill());
            }
            // TODO: Add handling for confused mob
        }

        if (player.getHealth() <= 0) {
            playerDead = true;
            return;
        }

        if (gameObject instanceof Door) {
            Door door = (Door) gameObject;
            roomId = door.leadsTo();
            player.moveAbsolute(door.getLeadsToX(), door.getLeadsToY());
        }

        if (gameObject.isDestroyed()) {
            room.deleteGameObject(gameObject);
        }
    }
}
