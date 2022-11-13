package models;

public class Door extends GameObject {
    public Door(int posX, int posY) {
        super(posX, posY);
        
        representation = new char[1][1];
        representation[0][0] = '+';
    }
}
