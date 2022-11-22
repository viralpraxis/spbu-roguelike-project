package core;

import lib.Event;
import ui.Renderer;

public class GameController {
    private Renderer render;
    private GameState gameState;

    public GameController() {
        render = new Renderer();
        gameState = new GameState();

        render.render(gameState);
    }

    /**
    * This method is used to process incoming event.
    * show the usage of various javadoc Tags.
    * @param event Incoming event
    * @return boolean Returns true iff event was processed successfully.
    */
    public boolean handleEvent(Event event) {
        GameStateChanger.call(gameState, event);
        render.render(gameState);

        return true;
    }
}
