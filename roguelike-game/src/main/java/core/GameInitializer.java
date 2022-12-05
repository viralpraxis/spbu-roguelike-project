package core;

import eventproducers.EventProducer;


public class GameInitializer {
    private static GameController controller;
    private static GameState gameState;

    public static void main(String[] args) {
        initialize();
    }

    /**
    * This method returns singleton controller instance.
    * @return GameController Returns singleton controller instance.
    */
    public static GameController getGameController() {
        if (controller == null) controller = new GameController();

        return controller;
    }

    public static GameState getGameState() {
        if (gameState == null) gameState = new GameState();

        return gameState;
    }

    private static void initialize() {
        Thread thread = new Thread() {
          @Override
          public void run() {
            EventProducer.initializeAll();

            while (true) {}
          }
        };
        thread.start();

        try {
          thread.join();
        } catch (InterruptedException e) {
          e.printStackTrace();
          System.exit(0);
        }
    }
}
