package commands;

import core.GameInitializer;
import lib.Movement;

public class MovePlayerCommand implements ICommand {
    private Movement movement;

    public MovePlayerCommand(Movement movement) {
        this.movement = movement;
    }

    @Override
    public void execute() {
        GameInitializer.getGameState().updateGameState(
            movement.getDeltaX(), movement.getDeltaY()
        );
    }
}
