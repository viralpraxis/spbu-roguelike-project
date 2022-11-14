package lib;

public class Event {
    private Type type;

    public enum Type {
        GAME_EXIT,

        PLAYER_MOVE_UP,
        PLAYER_MOVE_DOWN,
        PLAYER_MOVE_LEFT,
        PLAYER_MOVE_RIGHT
    }

    public Event(Type type) {
        this.type = type;
    }

    public Event.Type getType() {
        return this.type;
    }
}
