package repositories;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import core.Level;
import core.Room;
import models.Door;
import models.Item;
import models.GameObject;
import models.Player;

public class LevelRepository extends Repository {
    private LevelData[] levels;

    public class LevelData {
        private Level level;
        private Player player;

        public LevelData(Level level, Player player) {
            this.level = level;
            this.player = player;
        }

        public Level getLevel() {
            return this.level;
        }

        public Player getPlayer() {
            return this.player;
        }
    }

    String getDatasourceFilepath() {
        return "src/main/java/config/levels.yaml";
    }

    public LevelData[] getLevels() {
        if (this.levels != null) { return this.levels; }

        ArrayList<LinkedHashMap> levels = (ArrayList<LinkedHashMap>) data.get("levels");
        LevelData[] targetLevelsData = new LevelData[levels.size()];
        int levelIndex = 0;
        for (LinkedHashMap level : levels) {
            Player player = null;
            ArrayList<LinkedHashMap> rooms = (ArrayList<LinkedHashMap>) level.get("rooms");
            Room[] targetRooms = new Room[rooms.size()];
            int roomIndex = 0;

            LinkedHashMap playerData = (LinkedHashMap) level.get("player");
            if (playerData != null) {
                player = new Player((Integer) playerData.get("x"), (Integer) playerData.get("y"));
            }

            for (LinkedHashMap room : rooms) {
                targetRooms[roomIndex++] = new Room(
                    (Boolean) room.get("visible"),
                    (Integer) room.get("x"),
                    (Integer) room.get("y"),
                    buildGameObjects((ArrayList<LinkedHashMap>) room.get("objects"))
                );
            }
            targetLevelsData[levelIndex++] = new LevelData(
                new Level(targetRooms),
                player
            );
        }

        this.levels = targetLevelsData;

        return targetLevelsData;
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
