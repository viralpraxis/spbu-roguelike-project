package repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import core.Level;
import core.Room;
import models.Door;
import models.Item;
import models.GameObject;

public class MapRepository extends Repository {
    private Level[] levels;

    String getDatasourceFilepath() {
        return "src/main/java/config/maps.yaml";
    }

    public Level[] getLevels() {
        if (this.levels != null) { return this.levels; }

        ArrayList<LinkedHashMap> maps = (ArrayList<LinkedHashMap>) data.get("maps");
        for (LinkedHashMap map : maps) {
            ArrayList<LinkedHashMap> levels = (ArrayList<LinkedHashMap>) map.get("levels");
            Level[] targetLevels = new Level[levels.size()];
            int levelIndex = 0;
            for (LinkedHashMap level : levels) {
                ArrayList<LinkedHashMap> rooms = (ArrayList<LinkedHashMap>) level.get("rooms");
                Room[] targetRooms = new Room[rooms.size()];
                int roomIndex = 0;
                for (LinkedHashMap room : rooms) {
                    targetRooms[roomIndex++] = new Room(
                        (Boolean) room.get("visible"),
                        (Integer) room.get("x"),
                        (Integer) room.get("y"),
                        buildGameObjects((ArrayList<LinkedHashMap>) room.get("objects"))
                    );
                }
                targetLevels[levelIndex++] = new Level(targetRooms);
            }

            return targetLevels;
        }

        return levels;
    }

    private List<GameObject> buildGameObjects(ArrayList<LinkedHashMap> data) {
        List<GameObject> gameObjects = new ArrayList<GameObject>();

        for (LinkedHashMap data_entry : data) {
            gameObjects.add(buildGameObject(data_entry));
        }

        return gameObjects;
    }

    private GameObject buildGameObject(LinkedHashMap data) {
        String type = (String) data.get("type");

        switch (type) {
            case "door":
                return new Door(
                    (Integer) data.get("x"),
                    (Integer) data.get("y"),
                    (Integer) data.get("target_x"),
                    (Integer) data.get("target_y"),
                    (Integer) data.get("target_room")
                );
            case "item":
                return new Item(
                    (Integer) data.get("x"),
                    (Integer) data.get("y"),
                    (String) data.get("name")
                );
            default:
                throw new IllegalArgumentException();
        }
    }
}
