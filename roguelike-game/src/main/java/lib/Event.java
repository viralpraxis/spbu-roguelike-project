package lib;

public class Event {
    private Type type;

    public enum Type {
        GAME_EXIT,

        PLAYER_MOVE_UP,
        PLAYER_MOVE_DOWN,
        PLAYER_MOVE_LEFT,
        PLAYER_MOVE_RIGHT,
        PLAYER_SELECT_ITEM,
        PLAYER_UNSELECT_ITEM,
        PLAYER_SELECT_NEXT_ITEM,
        PLAYER_SELECT_PREVIOUS_ITEM,
        PLAYER_EQUIP_ITEM,
        PLAYER_MOVE_NEXT_LEVEL,
    }

    public Event(Type type) {
        this.type = type;
    }

    public Event.Type getType() {
        return this.type;
    }
}
