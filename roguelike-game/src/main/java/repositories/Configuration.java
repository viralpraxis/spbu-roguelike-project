package repositories;

import java.util.HashMap;

import models.mobs.MobGeneratorFactory;
import models.mobs.SciFiMobGeneratorFactory;
import models.mobs.FantasyMobGeneratorFactory;
import lib.Size;
import lib.Interval;

public class Configuration extends Repository {
    private final String KEY_SPLIT_REGEXP = "\\.";

    private static Configuration instance;

    @Override
    String getDatasourceFilepath() {
        return "src/main/java/config/configuration.yaml";
    }

    public static Object get(String key) {
        if (instance == null) { instance = new Configuration(); }

        return instance._get(key);
    }

    public static Object fetch(String key, Object defaultValue) {
      Object value = get(key);

      return value == null ? defaultValue : value;
    }

    public static Size getMapSize() {
      return new Size(
        (Integer) get("map.width"),
        (Integer) get("map.height")
      );
    }

    public static MobGeneratorFactory getMobGeneratorFactory() {
      switch ((String) get("mobs_style")) {
        case "scifi": return new SciFiMobGeneratorFactory();
        case "fantasy": return new FantasyMobGeneratorFactory();
        default:
          return new SciFiMobGeneratorFactory();
      }
    }

    public static Interval<Integer> getMapGenerationLevelsCountInterval() {
      Integer left = (Integer) fetch("map.generation.min_levels_count", 4);
      Integer right = (Integer) fetch("map.generation.max_levels_count", 8);

      return new Interval<Integer>(left, right);
    }

    public Object _get(String key) {
        String[] tokens = key.split(KEY_SPLIT_REGEXP);
        var currentData = new HashMap<String, Object>(data);

        try {
            for (int i = 0; i < tokens.length - 1; i++) {
                currentData = (HashMap<String, Object>) currentData.get(tokens[i]);
            }

            return currentData.get(tokens[tokens.length - 1]);
        } catch (NullPointerException e) {
            return null;
        }
    }
}
