package core;

import lib.Event;
import ui.Renderer;
import repositories.Configuration;

import models.mobs.Player;
import lib.Logger;
import lib.Event;
import lib.Movement;

import commands.MovePlayerCommand;
import commands.UpdateInventorySelectedStatusCommand;
import commands.SelectInventoryItemCommand;
import commands.ChangeSelectedItemEquipStatusCommand;
import commands.TryMoveToNextLevelCommand;

public class GameController {
    private Renderer render;
    private GameState gameState;

    public GameController() {
        render = new Renderer(Configuration.getMapSize());
        gameState = GameInitializer.getGameState();

        render.render(gameState);
    }

    /**
    * This method is used to process incoming event and render game state changes.
    * @param event Incoming event
    * @return boolean Returns true iff event was processed successfully.
    */
    public boolean handleEvent(Event event) {
        processEvent(event);
        render.render(gameState);

        return true;
    }

    /**
    * This method is used to process incoming event.
    * @param event Incoming event
    */
    public void processEvent(Event event) {
      Logger.log("Event: " + event.getType().toString());
      Player player = gameState.getPlayer();

      switch (event.getType()) {
          case PLAYER_MOVE_UP:
              new MovePlayerCommand(new Movement(0, -1)).execute();

              break;
          case PLAYER_MOVE_DOWN:
              new MovePlayerCommand(new Movement(0, 1)).execute();

              break;
          case PLAYER_MOVE_LEFT:
              new MovePlayerCommand(new Movement(-1, 0)).execute();

              break;
          case PLAYER_MOVE_RIGHT:
              new MovePlayerCommand(new Movement(1, 0)).execute();

              break;
          case PLAYER_SELECT_ITEM:
              new UpdateInventorySelectedStatusCommand(true).execute();

              break;
          case PLAYER_UNSELECT_ITEM:
              new UpdateInventorySelectedStatusCommand(false).execute();

              break;
          case PLAYER_SELECT_NEXT_ITEM:
              new SelectInventoryItemCommand(true).execute();

              break;
          case PLAYER_SELECT_PREVIOUS_ITEM:
              new SelectInventoryItemCommand(false).execute();

              break;
          case PLAYER_EQUIP_ITEM:
              new ChangeSelectedItemEquipStatusCommand(player).execute();;

              break;
          case PLAYER_MOVE_NEXT_LEVEL:
              new TryMoveToNextLevelCommand().execute();

              break;
          case GAME_EXIT:
              Logger.log("Exiting..");
              System.exit(0);
          default:
              Logger.log("WARN", "Unknown event: " + event.getType().toString());
        }
    }
}
