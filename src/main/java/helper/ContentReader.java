package helper;

import java.io.FileInputStream;
import java.io.IOException;

public class ContentReader {

    public static FileInputStream getPropertyFile(String site) throws IOException {
        String path = System.getProperty("user.dir");
        if (site == null) {
            path = path + "/src/main/resources/us.properties";
        }else {
            path = path + "/src/main/resources/"+site+".properties";
        }

        FileInputStream file = new FileInputStream(path);
        return file;
    }
}
