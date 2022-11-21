package repositories;

import java.util.HashMap;


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

    public Object _get(String key) {
        String[] tokens = key.split(KEY_SPLIT_REGEXP);

        System.out.println(tokens.length);
        for (String s : tokens) { System.out.println(s);}

        var currentData = new HashMap<String, Object>(data);

        String value;

        for (int i = 0; i < tokens.length - 1; i++) {
            currentData = (HashMap<String, Object>) currentData.get(tokens[i]);
        }

        return currentData.get(tokens[tokens.length - 1]);
    }
}
