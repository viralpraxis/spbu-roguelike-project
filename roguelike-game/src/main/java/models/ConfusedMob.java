package models;

import java.util.Random;

import repositories.Configuration;

public class ConfusedMob extends Mob {
    private Mob mob;
    private int timeLeft;
    private Random rand;

    public ConfusedMob(Mob mob) {
        this.mob = mob;

        timeLeft = 5;
        rand = new Random(Configuration.getRandomSeed());
    }

    /**
     * Gets the time left for this mob to be confused.
     *
     * @return amount of the time left
     */
    public int getTimeLeft() {
        return timeLeft;
    }

    /**
     * Return an instance of mob that this instance of confused mob decorates.
     *
     * @return an instance of mob that is decorated
     */
    public Mob getMob() {
        return mob;
    }

    @Override
    public int getExperienceForKill() {
        return mob.getExperienceForKill();
    }

    @Override
    public void handleStepFrom(Mob mob) {
        this.mob.handleStepFrom(mob);
    }

    @Override
    public int getHealth() {
        return mob.getHealth();
    }

    @Override
    public void alterHealth(int dHealth) {
        mob.alterHealth(dHealth);
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
    public void makeDestroyed() {
        mob.makeDestroyed();
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
    public void makeNextMove(Player player, Room room, CollisionsResolver collisionsResolver) {
        timeLeft -= 1;
        makeRandomMove(player, room, collisionsResolver);
    }

    private void makeRandomMove(Player player, Room room, CollisionsResolver collisionsResolver) {
        boolean moved = false;
        while (!moved) {
            int direction = rand.nextInt(4);
            if (direction == 0) {
                moved = tryMakeMove(player, room, -1, 0, collisionsResolver);
            } else if (direction == 1) {
                moved = tryMakeMove(player, room, +1, 0, collisionsResolver);
            } else if (direction == 2) {
                moved = tryMakeMove(player, room, 0, -1, collisionsResolver);
            } else if (direction == 3) {
                moved = tryMakeMove(player, room, 0, +1, collisionsResolver);
            }
        }
    }

    private boolean tryMakeMove(Player player, Room room, int dx, int dy, CollisionsResolver collisionsResolver) {
        if (player.posX() == mob.posX() + dx && player.posY() == mob.posY() + dy) {
            collisionsResolver.addCollision(this);
            // player.handleStepFrom(mob);
            return true;
        }

        if (!room.containsMobAtPos(mob.posX() + dx, mob.posY() + dy) &&
            !room.nextStepOutsideRoom(mob, dx, dy)) {
            mob.move(dx, dy);
            return true;
        }
        return false;
    }
}
