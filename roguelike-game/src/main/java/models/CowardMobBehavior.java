package models;

public class CowardMobBehavior implements MobBehavior{
    @Override
    public void makeNextMove(Mob player, Mob mob, Room room) {
        int deltaX = mob.posX() - player.posX();
        int deltaY = mob.posY() - player.posY();

        if (Integer.signum(deltaY) + mob.posY() > room.posY() &&
            Integer.signum(deltaY) + mob.posY() < room.posY() + room.getSize() - 1 &&
            !room.containsMobAtPos(mob.posX(), mob.posY() + Integer.signum(deltaY)))
            mob.move(0, Integer.signum(deltaY));
        else if (Integer.signum(deltaX) + mob.posX() > room.posX() &&
                 Integer.signum(deltaX) + mob.posX() < room.posX() + room.getSize() - 1 &&
                 !room.containsMobAtPos(mob.posX() + Integer.signum(deltaX), mob.posY()))
            mob.move(Integer.signum(deltaX), 0);
    }
}
