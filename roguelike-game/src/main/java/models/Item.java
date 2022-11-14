package models;

public class Item extends GameObject {
    private String name;
    public Item(int posX, int posY, String name) {
        this.posX = posX;
        this.posY = posY;
        this.name = name;
        representation = new char[1][1];
        representation[0][0] = 'i';
        visible = true;
    }
    @Override
    public void stepOn(Mob mob) {
        if (!(mob instanceof Player))
            return;

        Inventory.getInventory().addItem(this);
        visible = false;
    }

    public String getName() {
        return name;
    }
}
