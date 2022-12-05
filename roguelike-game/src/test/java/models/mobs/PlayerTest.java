package test.models.mobs;

import java.util.Random;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import models.mobs.Player;

class PlayerTest {
    private Player player;
    private Random random = new Random();

    @Test
    public void testMoveAbsolute() {
        player = buildPlayer();

        int newX = randomInt();
        int newY = randomInt();

        player.moveAbsolute(newX, newY);

        assertEquals(newX, player.posX());
        assertEquals(newY, player.posY());
    }

    @Test
    public void testToString() {
        player = buildPlayer();

        assertEquals(
            String.format("Player((%d, %d), H%s, S%s, %s, %f)", player.posX(), player.posY(), player.getHealth(), player.getPower(), player.getName(), player.getBashChance()),
            player.toString()
        );
    }

    private Player buildPlayer() {
        return new Player(randomInt(), randomInt());
    }

    private int randomInt() {
        return random.nextInt();
    }
}
