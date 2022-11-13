package core;

public class Level {
    private Room[] rooms;

    public Level() {
        rooms = new Room[2];
        rooms[0] = new Room(true, 10, 10);
        rooms[1] = new Room(true, 20, 20);
    }

    public Room[] getRooms() {
        return rooms;
    }
}
