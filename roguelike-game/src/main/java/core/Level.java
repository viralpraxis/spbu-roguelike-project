package core;

import models.Player;

public class Level {
    private Room[] rooms;
    private int currentRoomId;
    private int levelId;

    public Level() {
        levelId = 0;
        rooms = new Room[2];
        rooms[0] = new Room(true, 0, 0);
        rooms[1] = new Room(false, 30, 10);
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
}
