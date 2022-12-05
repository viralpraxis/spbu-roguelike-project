package models;

import models.mobs.Mob;
import models.mobs.Player;

/**
 * This class represents an item that player can pick up.
 */
public class Item extends GameObject {
    private String name;
    private boolean equipped;

    public Item(int posX, int posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        visible = true;
        equipped = false;
        steppable = true;
        destroyed = false;
    }

    @Override
    public void handleStepFrom(Mob mob) {
        if (!(mob instanceof Player))
            return;

        Inventory.getInventory().addItem(this);
        visible = false;
        destroyed = true;
    }

    /**
     * Change equip status of an item and alter player stats.
     *
     * @param player Player whose stats should be altered
     */
    public void changeEquipStatus(Player player) {
        if (equipped) {
            equipped = false;
            // TODO: alter player's stats
        } else {
            equipped = true;
            // TODO: alter player's stats
        }
    }

    /**
     * Get the name of this item.
     *
     * @return name of an item
     */
    public String getName() {
        return name;
    }

    /**
     * Checks whether this item is equipped.
     *
     * @return true if item is equipped, false - otherwise
     */
    public boolean isEquipped() {
        return equipped;
    }
}
