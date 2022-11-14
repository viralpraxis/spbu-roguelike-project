package models;

import java.util.ArrayList;
import java.util.List;

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

    public static Inventory getInventory() {
        if (instance == null) instance = new Inventory();
        return instance;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public void selectNext() {
        lastSelectedItemId = (lastSelectedItemId + 1) % items.size();
    }

    public void selectPrevious() {
        if (lastSelectedItemId == 0)
            lastSelectedItemId = items.size() - 1;
        else
            lastSelectedItemId -= 1;
    }

    public void setSelectedStatus(boolean status) {
        selected = status;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getSelectedId() {
        return lastSelectedItemId;
    }

    public void selectedItemChangeEquipStatus(Player player) {
        if (!selected)
            return;
        items.get(lastSelectedItemId).changeEquipStatus(player);
    }

    public String getDescription() {
        return "Item description";
    }
}
