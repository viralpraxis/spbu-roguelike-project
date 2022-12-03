package models;

/**
 * This class represents a door which can lead to any room.
 */
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
    }

    /** Returns the room id to which the door leads.
     * @return A room id
     */
    public int leadsTo() {
        return leadsToRoom;
    }

    /** Returns X position this door leads to.
     * @return A coordinate
     */
    public int getLeadsToX() {
        return leadsToX;
    }

    /** Returns Y position this door leads to.
     * @return A coordinate
     */
    public int getLeadsToY() {
        return leadsToY;
    }

    @Override
    public void handleStepFrom(Mob mob) {
        // mob.posX = leadsToX;
        // mob.posY = leadsToY;
    }
}
