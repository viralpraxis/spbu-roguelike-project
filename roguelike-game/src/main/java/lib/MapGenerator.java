package lib;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;

import core.*;
import models.*;

public class MapGenerator {
    private static Random randomizer = new Random();

    public static Level[] generate(int width, int height) {
        int roomsCount = randomizer.nextInt(7);
        Room[] rooms = new Room[roomsCount];

        for (int i = 0; i < roomsCount; i++) {
            rooms[i] = generateRoom(
                i == 0,
                randomizer.nextInt(width - 10),
                randomizer.nextInt(height - 10)
              );
        }

        return new Level[]{
          new Level(rooms)
        };
    }

    private static Room generateRoom(boolean visible, int x, int y) {
        return new Room(
            visible,
            x,
            y,
            generateGameObject(x, y)
        );
    }

    private static List<GameObject> generateGameObject(int roomX, int roomY) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        int[] doorPoint = randomPointInRoomBound(roomX, roomY, 10);

        return gameObjects;

        // gameObjects.add(
        //     new Door(
        //         doorPoint[0],
        //         doorPoint[1]
        //     )
        // );
    }

    // yeah whatever
    private static int[] randomPointInRoomBound(int x, int y, int size) {
        int[][] points = new int[size * 4 - 4][2];
        int index = 0;

        for (int i = 0; i < size; i++) { points[index++] = new int[]{x + i, y}; }
        for (int i = 0; i < size; i++) { points[index++] = new int[]{x + i, y + size}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x, y + i}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + size, y + i}; }

        return points[randomizer.nextInt(points.length)];
    }

    private int[] randomPointInsideRoom(Room room) {
        return new int[]{
            room.posX() + 1 + randomizer.nextInt(room.getSize()),
            room.posY() + 1 + randomizer.nextInt(room.getSize())
        };
    }
}
