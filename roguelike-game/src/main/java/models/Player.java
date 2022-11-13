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
    }
}
