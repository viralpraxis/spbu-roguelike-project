package test.models;

import java.util.Random;
import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import models.Inventory;
import models.Item;


class InventoryTest {
    private Inventory inventory;
    private Random random = new Random();

    @Test
    public void testGetInventory() {
        Inventory inventory_1 = Inventory.getInventory();
        Inventory inventory_2 = Inventory.getInventory();

        assertEquals(inventory_1, inventory_2);
    }

    @Test
    public void testAddItem() {
        inventory = Inventory.getInventory();

        int initialItemsSize = inventory.getItems().size();
        Item item = new Item(randomInt(), randomInt(), "test-item");

        inventory.addItem(item);

        assertEquals(initialItemsSize + 1, inventory.getItems().size());
    }

    @Test
    public void testSelectNext() {
      inventory = Inventory.getInventory();

      assertEquals(0, inventory.getSelectedId());

      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-1"));
      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-2"));
      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-3"));

      inventory.selectNext();
      assertEquals(1, inventory.getSelectedId());

      inventory.selectNext();
      assertEquals(2, inventory.getSelectedId());

      inventory.selectNext();
      assertEquals(0, inventory.getSelectedId());
    }

    @Test
    public void testSelectPrevious() {
      inventory = Inventory.getInventory();

      assertEquals(0, inventory.getSelectedId());

      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-1"));
      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-2"));
      inventory.addItem(new Item(randomInt(), randomInt(), "test-item-3"));

      inventory.selectPrevious();
      assertEquals(2, inventory.getSelectedId());

      inventory.selectPrevious();
      assertEquals(1, inventory.getSelectedId());

      inventory.selectPrevious();
      assertEquals(0, inventory.getSelectedId());
    }

    @Test
    public void testSetSelectedStatus() {
        inventory = Inventory.getInventory();

        inventory.setSelectedStatus(false);
        assertEquals(false, inventory.isSelected());

        inventory.setSelectedStatus(true);
        assertEquals(true, inventory.isSelected());
    }

    @Test
    public void testGetDescription() {
        inventory = Inventory.getInventory();

        assertEquals("Item description", inventory.getDescription());
    }

    private int randomInt() {
        return random.nextInt();
    }

    @BeforeEach
    public void nullifyInventorySingletonInstance() {
        Field field = null;

        try {
            field = Inventory.class.getDeclaredField("instance");
            field.setAccessible(true);
            field.set(null, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            System.exit(1);
        } finally {
            if (field != null) field.setAccessible(false);
        }
    }
}
