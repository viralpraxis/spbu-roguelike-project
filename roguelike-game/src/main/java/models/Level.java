package models;

public class Level {
    private Room[] rooms;
    private Player player;
    private int currentRoomId;
    private int levelId;

    public Level(Room[] rooms, Player player) {
        this.rooms = rooms;
        this.player = player;
        levelId = 0;
    }

    /**
     * Checks whether level is finished, and player can pass to the next level. Level is considered finished
     * when all mobs are dead.
     *
     * @return true if level is finished, false - otherwise
     */
    public boolean isFinished() {
        for (Room room : rooms) {
            if (room.containsMobs()) return false;
        }
        return true;
    }

    /**
     * Handles next Player move at the level.
     *
     * @param player Player whose move will be handled
     * @param dx     desired relative change of position in x direction
     * @param dy     desired relative change of position in y direction
     */
    public void makeNextMove(Player player, int dx, int dy) {
        Room curRoom = rooms[currentRoomId];

        int retValue = curRoom.makeMove(player, dx, dy);
        if (retValue != -1) {
            rooms[retValue].makeVisible();
            currentRoomId = retValue;
        }
    }

    /**
     * Gets all rooms that the level contains.
     *
     * @return An array of rooms
     */
    public Room[] getRooms() {
        return rooms;
    }

    public Player getPlayer() {
        return player;
    }
}
