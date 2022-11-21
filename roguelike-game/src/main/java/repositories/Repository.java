package repositories;

import java.util.Map;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.File;
import org.yaml.snakeyaml.*;

abstract class Repository {
    private Yaml yaml;
    private InputStream inputStream;
    protected Map<String, Object> data;

    abstract String getDatasourceFilepath();

    public Repository() {
        yaml = new Yaml();

        try {
            File initialFile = new File(this.getDatasourceFilepath());
            InputStream targetStream = new FileInputStream(initialFile);

            data = yaml.load(targetStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            System.exit(1);
        }
    }

    /**
    * This method is used to get raw data..
    * @return Map<String, Object> Returns raw data.
    */
    public Map<String, Object> getData() {
        return this.data;
    }
}
