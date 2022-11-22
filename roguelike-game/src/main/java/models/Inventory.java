package models;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents player's inventory.
 */
public class Inventory {
    private static Inventory instance;
    private List<Item> items;
    private int lastSelectedItemId;
    private boolean selected;

    private Inventory() {
        items = new ArrayList<>();
        selected = false;
        lastSelectedItemId = 0;
    }

    /**
     * Get an instance of Inventory.
     *
     * @return instance of Inventory
     */
    public static Inventory getInventory() {
        if (instance == null) instance = new Inventory();

        return instance;
    }

    /**
     * Add item to the inventory.
     * @param item Item ro be added
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Gets list of item that currently in the inventory.
     *
     * @return List of items the inventory contains
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Select next item in the inventory.
     */
    public void selectNext() {
        if (items.size() == 0) {
            lastSelectedItemId = 0;
        } else {
            lastSelectedItemId = (lastSelectedItemId + 1) % items.size();
        }
    }

    /**
     * Select previous item in the inventory.
     */
    public void selectPrevious() {
        if (items.size() == 0) {
            lastSelectedItemId = 0;
            return;
        }
        if (lastSelectedItemId == 0)
            lastSelectedItemId = items.size() - 1;
        else
            lastSelectedItemId -= 1;
    }

    /**
     * Set the select status for the inventory. If someone wants to equip an item, he/she should first select it.
     *
     * @param status boolean value to which the internal select status will be set
     */
    public void setSelectedStatus(boolean status) {
        selected = status;
    }

    /**
     * Checks whether any item is selected.
     *
     * @return true if there is any selected item in the inventory, false - otherwise
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Gets the id of the last selected item.
     *
     * @return Id of the last selected item
     */
    public int getSelectedId() {
        return lastSelectedItemId;
    }

    /**
     * Change the equipped status of the currently selected item.
     *
     * @param player Player whose characteristics will be altered after the equipped status of item is changed
     */
    public void selectedItemChangeEquipStatus(Player player) {
        if (!selected)
            return;
        items.get(lastSelectedItemId).changeEquipStatus(player);
    }

    /**
     * Gets description of the currently selected item.
     *
     * @return String description of an item
     */
    public String getDescription() {
        return "Item description";
    }
}
