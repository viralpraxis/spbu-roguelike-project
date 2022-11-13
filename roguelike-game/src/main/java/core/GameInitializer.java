package core;

import core.GameController;

public class GameInitializer {
    GameController controller;

    public void initializeGame() {
        controller = new GameController();
        controller.handleEvent();
    }

    public static void main(String[] args) {
        new GameInitializer().initializeGame();
    }
}
