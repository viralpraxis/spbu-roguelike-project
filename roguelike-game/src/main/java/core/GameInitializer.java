package core;

import eventproducers.EventProducer;
import core.GameController;
import repositories.MapRepository;

public class GameInitializer {
    private static GameController controller;

    public static void main(String[] args) {
        initialize();
    }

    public static GameController getGameController() {
        return controller;
    }

    private static void initialize() {

        Thread thread = new Thread() {
          @Override
          public void run() {
            EventProducer.initializeAll();
            controller = new GameController();

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
