package core;

import eventproducers.EventProducer;


public class GameInitializer {
    private static GameController gameController;
    private static GameState gameState;

    public static void main(String[] args) {
        initialize();
    }

    /**
    * This method returns singleton controller instance.
    * @return GameController Returns singleton controller instance.
    */
    public static GameController getGameController() {
        return gameController;
    }

    /**
    * This method returns singleton game state instance.
    * @return GameState Returns singleton controller instance.
    */

    public static GameState getGameState() {
        return gameState;
    }

    private static void initialize() {
        Thread thread = new Thread() {
          @Override
          public void run() {
            EventProducer.initializeAll();
            gameState = new GameState();
            gameController = new GameController();

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
