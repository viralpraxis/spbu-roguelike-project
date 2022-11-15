package core;

import models.Player;
import repositories.MapRepository;

public class GameState {
    private Level level;
    private Player player;
    private Level[] levels;
    private int curLevelId;

    public GameState() {
        Level[] levels = new MapRepository().getLevels();
        this.level = levels[0];
        this.levels = levels;
        curLevelId = 0;
        player = new Player(5, 5, 100, 100, "John");
    }

    /**
     * Checks whether current level is finished. If so, transfers player to the next level.
     */
    public void tryMoveToNextLevel() {
        if (!level.isFinished()) return;
        curLevelId += 1;

        // FIXME: Add initial player coordinates at each level to maps.yaml
        if (curLevelId == 1) {
            player.moveAbsolute(33, 6);
        }

        if (curLevelId >= levels.length) return;
        level = levels[curLevelId];
    }

    /**
     * Gets all rooms that are present at the current level.
     *
     * @return An array of rooms
     */
    public Room[] getRooms() {
        return level.getRooms();
    }

    /**
     * Gets the player at the current state of the game.
     *
     * @return A player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Update the state of the game by trying to move the player.
     *
     * @param dx relative change of position in the x direction
     * @param dy relative change of position in the y direction
     */
    public void updateGameState(int dx, int dy) {
        level.makeNextMove(player, dx, dy);
    }

    /**
     * Gets the level-number of the currently loaded level. Level-number is >= 0.
     *
     * @return Value of the level-number
     */
    public int getLevelNumber() {
        return curLevelId;
    }
}
