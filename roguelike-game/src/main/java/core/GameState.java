package core;

import models.Player;
import repositories.LevelRepository;
import lib.MapGenerator;

public class GameState {
    private Level level;
    private Player player;
    private Level[] levels;
    private int curLevelId;

    public GameState() {
        LevelRepository.LevelData[] levelsData = new LevelRepository().getLevels();
        // Level[] levels = MapGenerator.generate(50, 24);
        this.level = levelsData[0].getLevel();

        Level[] levels = new Level[levelsData.length];
        for (int i = 0; i < levels.length; i++) { levels[i] = levelsData[i].getLevel(); }
        this.levels = levels;
        curLevelId = 0;

        player = levelsData[0].getPlayer();
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
