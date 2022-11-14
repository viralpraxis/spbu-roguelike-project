package lib;

import core.GameState;
import models.Player;

// FIXME: Better naming?
public class GameStateChanger {
    public static void call(GameState gameState, Event event) {
        Logger.log("Event: " + event.getType().toString());
        Player player = gameState.getPlayer();

        switch (event.getType()) {
            case PLAYER_MOVE_UP:
                gameState.updateGameState(0, -1);

                break;
            case PLAYER_MOVE_DOWN:
                gameState.updateGameState(0, 1);

                break;
            case PLAYER_MOVE_LEFT:
                gameState.updateGameState(-1, 0);

                break;
            case PLAYER_MOVE_RIGHT:
                gameState.updateGameState(1, 0);

                break;
            case GAME_EXIT:
                Logger.log("Exiting..");
                System.exit(0);
            default:
                Logger.log("WARN", "Unknow event: " + event.getType().toString());
        }
    }
}
