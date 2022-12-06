package commands;

import core.GameInitializer;

public class TryMoveToNextLevelCommand implements ICommand {
    public void execute() {
        GameInitializer.getGameState().tryMoveToNextLevel();
    }
}
