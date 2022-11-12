package core;

public class GameInitializer {
    public void initializeGame() {
        System.out.println("Game initialized");
    }

    public static void main(String[] args) {
        new GameInitializer().initializeGame();
    }
}
