package models;

import java.util.Random;

public class ConfusedMob extends Mob {
    private Mob mob;
    private int timeLeft;
    private Random rand;

    public ConfusedMob(Mob mob) {
        this.mob = mob;

        timeLeft = 5;
        rand = new Random();
    }

    @Override
    public int getExperienceForKill() {
        return mob.getExperienceForKill();
    }

    @Override
    public void stepOn(Mob mob) {
        this.mob.stepOn(mob);
    }

    @Override
    public int getHealth() {
        return mob.getHealth();
    }

    @Override
    public int getPower() {
        return mob.getPower();
    }

    @Override
    public void addExperience(int value) {
        mob.addExperience(value);
    }

    @Override
    public void move(int dx, int dy) {
        mob.move(dx, dy);
    }

    @Override
    public boolean isDestroyed() {
        return mob.isDestroyed();
    }

    @Override
    public void setMobBehavior(String behavior) {
        mob.setMobBehavior(behavior);
    }

    @Override
    public int posX() {
        return mob.posX();
    }

    @Override
    public int posY() {
        return mob.posY();
    }

    @Override
    public boolean isSteppable() {
        return mob.isSteppable();
    }

    @Override
    public boolean isVisible() {
        return mob.isVisible();
    }

    @Override
    public void makeNextMove(Mob player, Room room) {
        if (timeLeft >= 0)
        {
            timeLeft -= 1;
            makeRandomMove(player, room);
        } else {
            mob.makeNextMove(player, room);
        }
    }

    private void makeRandomMove(Mob player, Room room) {
        boolean moved = false;
        while (!moved) {
            int direction = rand.nextInt(4);
            if (direction == 0) {
                moved = tryMakeMove(player, room, -1, 0);
            } else if (direction == 1) {
                moved = tryMakeMove(player, room, +1, 0);
            } else if (direction == 2) {
                moved = tryMakeMove(player, room, 0, -1);
            } else if (direction == 3) {
                moved = tryMakeMove(player, room, 0, +1);
            }
        }
    }

    private boolean tryMakeMove(Mob player, Room room, int dx, int dy) {
        if (player.posX() == mob.posX() + dx && player.posY() == mob.posY() + dy) {
            player.stepOn(mob);
            return true;
        }

        if (!room.containsMobAtPos(mob.posX() + dx, mob.posY() + dy) &&
            mob.posX() + dx < room.posX() + room.getSize() - 1 && mob.posX() + dx > room.posX() &&
            mob.posY() + dy < room.posY() + room.getSize() - 1 && mob.posY() + dy > room.posY()) {
            mob.move(dx, dy);
            return true;
        }
        return false;
    }
}
