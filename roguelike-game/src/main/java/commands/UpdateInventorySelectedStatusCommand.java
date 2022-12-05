package commands;

import models.Inventory;

public class UpdateInventorySelectedStatusCommand implements ICommand {
    private boolean status;

    public UpdateInventorySelectedStatusCommand(boolean status) {
        this.status = status;
    }

    @Override
    public void execute() {
        Inventory.getInventory().setSelectedStatus(status);
    }
}
