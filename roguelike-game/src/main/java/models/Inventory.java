package models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static Inventory instance;
    private List<Item> items;

    private Inventory() {
        items = new ArrayList<>();
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
}
