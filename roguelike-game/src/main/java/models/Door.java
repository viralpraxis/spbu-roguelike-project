package models;

public class Door extends GameObject {
    private final int leadsToX;
    private final int leadsToY;
    private final int leadsToRoom;

    public Door(int posX, int posY, int leadsToX, int leadsToY, int leadsToRoom) {
        this.posX = posX;
        this.posY = posY;
        this.leadsToX = leadsToX;
        this.leadsToY = leadsToY;
        this.leadsToRoom = leadsToRoom;
        this.visible = true;

        representation = new char[1][1];
        representation[0][0] = '+';
    }

    /** Returns the room id to which the door leads.
     * @return A room id
     */
    public int leadsTo() {
        return leadsToRoom;
    }

    @Override
    public void stepOn(Mob mob) {
        mob.posX = leadsToX;
        mob.posY = leadsToY;
    }
}
