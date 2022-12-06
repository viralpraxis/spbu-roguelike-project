package commands;

import core.GameInitializer;
import models.Inventory;

public class ChangeSelectedItemEquipStatusCommand implements ICommand {
    @Override
    public void execute() {
        Inventory.getInventory().selectedItemChangeEquipStatus(
            GameInitializer.getGameState().getPlayer()
        );
    }
}
