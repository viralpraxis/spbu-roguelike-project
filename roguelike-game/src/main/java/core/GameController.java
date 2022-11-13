package core;

import ui.Renderer;

public class GameController {
    private Renderer render;
    private GameState gameState;

    public GameController() {
        render = new Renderer();
        gameState = new GameState();
    }

    public void handleEvent() {
        render.render(gameState);
    }
}
