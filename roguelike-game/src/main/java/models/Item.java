package models;

public class Item extends GameObject {
    private String name;
    private boolean equipped;

    public Item(int posX, int posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        representation = new char[1][1];
        representation[0][0] = 'i';
        visible = true;
        equipped = false;
    }

    @Override
    public void stepOn(Mob mob) {
        if (!(mob instanceof Player))
            return;

        Inventory.getInventory().addItem(this);
        visible = false;
    }

    public void changeEquipStatus(Player player) {
        if (equipped) {
            equipped = false;
            // TODO: alter player's stats
        } else {
            equipped = true;
            // TODO: alter player's stats
        }
    }

    public String getName() {
        return name;
    }

    public boolean isEquipped() {
        return equipped;
    }
}
