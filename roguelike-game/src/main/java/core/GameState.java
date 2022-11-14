package core;

import models.Player;

public class GameState {
    private Level level;
    private Player player;

    public GameState() {
        level = new Level();
        player = new Player(5, 5, 100, 100, "John");
    }

    public Room[] getRooms() {
        return level.getRooms();
    }

    public Player getPlayer() {
        return player;
    }

    public void updateGameState(int dx, int dy) {
        level.makeNextMove(player, dx, dy);
    }
}
