package test.models;

import java.lang.reflect.Field;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import models.Item;
import models.Player;
import models.Mob;
import models.Inventory;


class ItemTest {
    private Item item;
    private Player player;
    private Random random = new Random();

    @Test
    public void testStepOn_whenMobIsPlayer() {
        item = buildItem();
        Player player = buildPlayer();
        Inventory inventory = Inventory.getInventory();
        int initialInventoryItemsSize = inventory.getItems().size();

        item.handleStepFrom(player);

        assertEquals(
            initialInventoryItemsSize + 1,
            inventory.getItems().size()
        );
        assertEquals(false, item.isVisible());
    }

    @Test
    public void testStepOn_whenMobIsNotPlayer() {
        item = buildItem();
        Mob mob = buildMob();
        Inventory inventory = Inventory.getInventory();
        int initialInventoryItemsSize = inventory.getItems().size();
        boolean initialVisibility = item.isVisible();

        item.handleStepFrom(mob);

        assertEquals(
            initialInventoryItemsSize,
            inventory.getItems().size()
        );
        assertEquals(initialVisibility, item.isVisible());
    }

    @Test
    public void testChangeEquipStatus() {
        item = buildItem();
        Player player = buildPlayer();
        boolean initialEquipped = item.isEquipped();

        item.changeEquipStatus(player);

        assertEquals(!initialEquipped, item.isEquipped());
    }

    @Test
    public void testGetName() {
        String itemName = "test-item";
        item = new Item(randomInt(), randomInt(), itemName);

        assertEquals(itemName, item.getName());
    }

    @Test
    public void testIsEquipped() {
        item = buildItem();
        player = buildPlayer();

        assertEquals(false, item.isEquipped());

        item.changeEquipStatus(player);
        assertEquals(true, item.isEquipped());
    }

    private Item buildItem() {
        return new Item(randomInt(), randomInt(), "test-item");
    }

    private Player buildPlayer() {
        return new Player(randomInt(), randomInt());
    }

    private Mob buildMob() {
        return new Mob(randomInt(), randomInt(), randomInt(), randomInt());
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
