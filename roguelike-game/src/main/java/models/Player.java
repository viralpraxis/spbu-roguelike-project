package models;

enum Gender {
    MALE,
    FEMALE,
    NON_BINARY,
}

enum Race {
    ELVE,
    HUMAN,
    ORC,
    GOBLIN,
    DEMON,
}

public class Player extends Mob {
    private final String name;
    // private final Gender gender;
    // private final Race race;

    private final static int DEFAULT_HEALTH = 100;
    private final static int DEFAULT_POWER = 100;
    private final static String DEFAULT_NAME = "John";

    public Player(int posX, int posY) {
        this(posX, posY, DEFAULT_HEALTH, DEFAULT_POWER, DEFAULT_NAME);
    }

    public Player(int posX, int posY, int health, int power, String name) {
        super(posX, posY, health, power);

        this.name = name;
        this.representation = new char[1][1];
        this.representation[0][0] = '@';
        this.visible = true;
    }

    /**
     * Moves player to the point (x, y) on the map.
     *
     * @param x new x coordinate of the player
     * @param y new y coordinate of the player
     */
    public void moveAbsolute(int x, int y) {
        posX = x;
        posY = y;
    }

    @Override
    public String toString() {
        return String.format("Player((%d, %d), H%s, S%s, %s)", posX, posY, health, power, name);
    }
}
