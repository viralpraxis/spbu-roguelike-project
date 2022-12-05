package commands;

import models.Inventory;

public class SelectInventoryItemCommand implements ICommand {
    private boolean selectNext;

    public SelectInventoryItemCommand(boolean selectNext) {
        this.selectNext = selectNext;
    }

    @Override
    public void execute() {
        if (this.selectNext) {
            Inventory.getInventory().selectNext();
        } else {
            Inventory.getInventory().selectPrevious();
        }
    }
}
