package repositories;

import java.util.HashMap;
import lib.Size;

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

    public static Size getMapSize() {
      return new Size(
        (Integer) get("map.width"),
        (Integer) get("map.height")
      );
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
