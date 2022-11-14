package models;

public class Item extends GameObject {
    public Item(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
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
}
