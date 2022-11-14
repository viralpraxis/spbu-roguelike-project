package core;

import lib.Event;
import lib.GameStateChanger;
import ui.Renderer;

public class GameController {
    private Renderer render;
    private GameState gameState;

    public GameController() {
        render = new Renderer();
        gameState = new GameState();

        render.render(gameState);
    }

    public boolean handleEvent(Event event) {
        GameStateChanger.call(gameState, event);
        render.render(gameState);

        return true;
    }
}
