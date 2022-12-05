package commands;

import models.Inventory;
import models.mobs.Player;

public class ChangeSelectedItemEquipStatusCommand implements ICommand {
    private Player player;

    public ChangeSelectedItemEquipStatusCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        Inventory.getInventory().selectedItemChangeEquipStatus(player);
    }
}
