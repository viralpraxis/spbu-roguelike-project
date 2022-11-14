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
        rooms[1] = new Room(true, 30, 10);
    }

    public void makeNextMove(Player player, int dx, int dy) {
        Room curRoom = rooms[currentRoomId];

        if (nextStepOutsideRoom(player, dx, dy))
            return;

        player.move(dx, dy);
    }

    public Room[] getRooms() {
        return rooms;
    }

    private boolean nextStepOutsideRoom(Player player, int dx, int dy) {
        if (player.posX() + dx + 1 >= rooms[currentRoomId].posX() + rooms[currentRoomId].getSize())
            return true;
        if (player.posX() + dx <= rooms[currentRoomId].posX())
            return true;
        if (player.posY() + dy + 1 >= rooms[currentRoomId].posY() + rooms[currentRoomId].getSize())
            return true;
        if (player.posY() + dy <= rooms[currentRoomId].posY())
            return true;
        return false;
    }
}
