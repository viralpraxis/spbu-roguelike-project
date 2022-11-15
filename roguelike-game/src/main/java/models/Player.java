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
}
