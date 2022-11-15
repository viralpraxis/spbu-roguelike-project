package lib;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import core.*;
import models.*;

public class MapGenerator {
    private static Random randomizer = new Random();

    /**
    * This method is used to generate levels.
    * @param width Map wisth.
    * @param height Map height.
    * @return Level[] Returns an array of initialized levels.
    */
    public static Level[] generate(int width, int height) {
        int roomsCount = 2;
        Room[] rooms = new Room[roomsCount];
        int[][] roomPositions = generateRoomPositions(roomsCount, width, height);
        int[][] doorPositions = generateDoorPositions(roomPositions);

        for (int i = 0; i < roomsCount; i++) {
            rooms[i] = generateRoom(
                i,
                i == 0,
                roomPositions[i][0],
                roomPositions[i][1],
                doorPositions[i],
                doorPositions[(i + 1) % roomsCount],
                roomsCount
              );
        }

        int[] playerPosition = randomPointInsideRoom(rooms[0]);
        return new Level[]{
          new Level(
              rooms,
              new Player(playerPosition[0], playerPosition[1])
          )
        };
    }

    private static int[][] generateRoomPositions(int roomsCount, int width, int height) {
        int[][] roomPositions = new int[roomsCount][2];

        for (int i = 0; i < roomsCount; i++) {
            roomPositions[i] = new int[]{
                randomizer.nextInt(width - 11),
                randomizer.nextInt(height - 11)
            };
        }

        return roomPositions;
    }

    private static int[][] generateDoorPositions(int[][] roomPositions) {
        int[][] doorPositions = new int[roomPositions.length][2];

        for (int i = 0; i < doorPositions.length; i++) {
            doorPositions[i] = randomPointInRoomBound(roomPositions[i][0], roomPositions[i][1], 10);
        }

        return doorPositions;
    }

    private static Room generateRoom(int roomID, boolean visible, int x, int y, int[] doorPosition, int[] nextDoorPosition, int roomsCount) {
        System.out.println(roomID + " " + x + " " + y + Arrays.toString(doorPosition) + " " + Arrays.toString(nextDoorPosition));
        return new Room(
            visible,
            x,
            y,
            generateGameObject(roomID, x, y, doorPosition, nextDoorPosition, roomsCount)
        );
    }

    private static List<GameObject> generateGameObject(int roomID, int roomX, int roomY, int[] doorPosition, int[] nextDoorPosition, int roomsCount) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        gameObjects.add(
            new Door(
                doorPosition[0],
                doorPosition[1],
                nextDoorPosition[0],
                nextDoorPosition[1],
                (roomID + 1) % roomsCount
            )
        );

        return gameObjects;
    }

    // yeah whatever
    private static int[] randomPointInRoomBound(int x, int y, int size) {
        int[][] points = new int[size * 4 - 8][2];
        int index = 0;

        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + i, y}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + i, y + size - 1}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x, y + i}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + size - 1, y + i}; }

        return points[randomizer.nextInt(points.length)];
    }

    private static int[] randomPointInsideRoom(Room room) {
        return new int[]{
            room.posX() + 1 + randomizer.nextInt(room.getSize() - 1),
            room.posY() + 1 + randomizer.nextInt(room.getSize() - 1)
        };
    }
}
