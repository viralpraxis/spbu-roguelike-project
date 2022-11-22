package lib;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import core.*;
import models.*;

public class LevelGenerator {
    private static Random randomizer = new Random();

    private int width;
    private int height;

    private int roomsCount;
    private Room[] rooms;
    private int[][] roomPositions;
    private int[][] doorPositions;
    private int[] roomSizes;

    /**
    * This method is used to generate levels.
    * @param width Map wisth.
    * @param height Map height.
    * @return Level Returns initialized levels.
    */
    public LevelGenerator(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Level generate() {
        roomsCount = 5 + randomizer.nextInt(5);
        rooms = new Room[roomsCount];
        System.out.println("Rooms count: " + roomsCount);
        generateRoomPositions();
        doorPositions = new int[roomsCount][2];

        for (int i = 0; i < roomsCount; i++) {
            rooms[i] = generateRoom(i);
        }

        for (int i = 0; i < roomsCount; i++) {
            System.out.println(roomPositions[i][0] + ":" + roomPositions[i][1]);
        }
        System.out.println();

        int[] playerPosition = randomPointInsideRoom(new int[]{rooms[0].posX(), rooms[0].posY()}, rooms[0].getSize());

        return new Level(
            rooms,
            new Player(playerPosition[0], playerPosition[1])
        );
    }

    private void generateRoomPositions() {
        roomPositions = new int[roomsCount][2];
        roomSizes = new int[roomsCount];

        for (int i = 0; i < roomsCount; i++) {
            int epoch = 0;
            int roomSize = 5 + randomizer.nextInt(10);
            roomSizes[i] = roomSize;
            while (roomPositions[i][0] == 0 && roomPositions[i][1] == 0 && epoch++ >= 0) {
                int possibleX = randomizer.nextInt(width - (roomSize + 1));
                int possibleY = randomizer.nextInt(height - (roomSize + 1));
                if (canPlaceRoom(i, possibleX, possibleY, roomSize)) {
                    roomPositions[i] = new int[]{possibleX, possibleY};
                }

                if (epoch > 100) throw new RuntimeException("Faield to generate map");
            }
        }
    }

    private boolean canPlaceRoom(int index, int x, int y, int roomSize) {
        for (int i = 0; i < index; i++) {
            if (Math.abs(roomPositions[i][0] - x) <= roomSize && Math.abs(roomPositions[i][1] - y) <= roomSize) return false;
        }

        return true;
    }

    private int[] generateDoorPosition(int roomIndex) {
        return randomPointInRoomBound(roomPositions[roomIndex][0], roomPositions[roomIndex][1], roomSizes[roomIndex]);
    }

    private Room generateRoom(int index) {
        return new Room(
            index == 0,
            roomPositions[index][0],
            roomPositions[index][1],
            generateGameObject(index),
            roomSizes[index]
        );
    }

    private List<GameObject> generateGameObject(int roomIndex) {
        ArrayList<GameObject> gameObjects = new ArrayList<>();

        if (roomIndex > 0 && roomIndex < roomsCount - 1) {
            int[] doorPosition = generateDoorPosition(roomIndex);
            int[] prevDoorPosition = generateDoorPosition(roomIndex - 1);

            gameObjects.add(
                new Door(
                    doorPosition[0],
                    doorPosition[1],
                    prevDoorPosition[0],
                    prevDoorPosition[1],
                    roomIndex - 1
                )
            );

            rooms[roomIndex - 1].getRoomContent().add(
                new Door(
                    prevDoorPosition[0],
                    prevDoorPosition[1],
                    doorPosition[0],
                    doorPosition[1],
                    roomIndex
                )
            );
        }

        int itemsCount = randomizer.nextInt(2);
        for (int i = 0; i < itemsCount; i++) {
            int[] itemPosition = randomPointInsideRoom(roomPositions[i], roomSizes[roomIndex]);
            gameObjects.add(new Item(itemPosition[0], itemPosition[1], "foo-bar"));
        }

        return gameObjects;
    }

    // yeah whatever
    private int[] randomPointInRoomBound(int x, int y, int size) {
        int[][] points = new int[size * 4 - 8][2];
        int index = 0;

        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + i, y}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + i, y + size - 1}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x, y + i}; }
        for (int i = 1; i < size - 1; i++) { points[index++] = new int[]{x + size - 1, y + i}; }

        return points[randomizer.nextInt(points.length)];
    }

    private int[] randomPointInsideRoom(int[] roomPosition, int size) {
        return new int[]{
            roomPosition[0] + 1 + randomizer.nextInt(size - 1),
            roomPosition[1] + 1 + randomizer.nextInt(size - 1)
        };
    }
}
