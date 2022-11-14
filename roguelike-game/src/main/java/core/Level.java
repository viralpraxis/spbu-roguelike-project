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

    public void makeNextMove(Player player, int dx, int dy) {
        Room curRoom = rooms[currentRoomId];

        int retValue = curRoom.makeMove(player, dx, dy);
        if (retValue != -1) {
            rooms[retValue].makeVisible();
            currentRoomId = retValue;
        }
    }

    public Room[] getRooms() {
        return rooms;
    }
}
