package controllers;

import lib.Logger;
import lib.Event;
import lib.Movement;
import repositories.Configuration;
import ui.Renderer;


import commands.MovePlayerCommand;
import commands.UpdateInventorySelectedStatusCommand;
import commands.SelectInventoryItemCommand;
import commands.ChangeSelectedItemEquipStatusCommand;
import commands.TryMoveToNextLevelCommand;

public class GameController {

    private Renderer renderer;

    public GameController() {
        renderer = new Renderer(Configuration.getMapSize());
        renderer.render();
    }

    /**
    * This method is used to process incoming event and render game state changes.
    * @param event Incoming event
    * @return boolean Returns true iff event was processed successfully.
    */
    public boolean handleEvent(Event event) {
        processEvent(event);
        renderer.render();

        return true;
    }

    /**
    * This method is used to process incoming event.
    * @param event Incoming event
    */
    private void processEvent(Event event) {
      Logger.log("Event: " + event.getType().toString());

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
              new ChangeSelectedItemEquipStatusCommand().execute();;

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
