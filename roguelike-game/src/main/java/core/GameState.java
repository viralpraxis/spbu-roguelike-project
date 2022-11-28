package core;

import models.Level;
import models.Room;
import models.mobs.Player;
import lib.MapBuilder;
import repositories.Configuration;

public class GameState {
    private Level level;
    private Level[] levels;
    private Player player;
    private int curLevelId = 0;

    public GameState() {
        MapBuilder mapBuilder = new MapBuilder();
        mapBuilder
          .setGenerationMethod(MapBuilder.GenerationMethod.PREDEFINED)
          .setSize(Configuration.getMapSize())
          .setLevelsCount(4, 8);

        this.levels = mapBuilder.build();
        this.level = levels[0];
    }

    /**
     * Checks whether current level is finished. If so, transfers player to the next level.
     */
    public void tryMoveToNextLevel() {
        if (!level.isFinished()) return;
        if (++curLevelId >= levels.length) return;

        this.level = levels[curLevelId];
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
        return this.level.getPlayer();
    }

    /**
     * Update the state of the game by trying to move the player.
     *
     * @param dx relative change of position in the x direction
     * @param dy relative change of position in the y direction
     */
    public void updateGameState(int dx, int dy) {
        level.makeNextMove(this.level.getPlayer(), dx, dy);
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
