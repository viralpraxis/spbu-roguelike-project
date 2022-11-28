package models.mobs;

import models.Room;

public class AggressiveMobBehavior implements MobBehavior {
    @Override
    public void makeNextMove(Mob player, Mob mob, Room room) {
        int deltaX = mob.posX() - player.posX();
        int deltaY = mob.posY() - player.posY();

        if (Math.abs(deltaX) == 1 && deltaY == 0 || deltaX == 0 && Math.abs(deltaY) == 1) {
            player.stepOn(mob);
            return;
        }

        if (deltaX == 0 && !room.containsMobAtPos(mob.posX(), mob.posY() - Integer.signum(deltaY)))
            mob.move(0, -Integer.signum(deltaY));
        else if (!room.containsMobAtPos(mob.posX() - Integer.signum(deltaX), mob.posY()))
            mob.move(-Integer.signum(deltaX), 0);

        if (mob.posX() == player.posX() && mob.posY() == player.posY()) {
            player.stepOn(mob);
        }
    }
}
