package models.mobs;

import models.Room;
import models.CollisionsResolver;

public class CowardMobBehavior implements MobBehavior{
    @Override
    public void makeNextMove(Player player, Mob mob, Room room, CollisionsResolver collisionsResolver) {
        int deltaX = mob.posX() - player.posX();
        int deltaY = mob.posY() - player.posY();

        if (!room.nextStepOutsideRoom(mob, 0, Integer.signum(deltaY)) &&
            !room.containsMobAtPos(mob.posX(), mob.posY() + Integer.signum(deltaY))) {
            mob.move(0, Integer.signum(deltaY));
        }
        else if (!room.nextStepOutsideRoom(mob, Integer.signum(deltaX), 0) &&
                 !room.containsMobAtPos(mob.posX() + Integer.signum(deltaX), mob.posY())) {
            mob.move(Integer.signum(deltaX), 0);
        }
    }
}
