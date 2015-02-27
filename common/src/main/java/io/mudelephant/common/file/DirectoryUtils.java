package io.mudelephant.common.file;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Directory utils for common needs.
 */
public class DirectoryUtils {

    /**
     * Returns the parent directory of the jar or main project directory.
     * @param clazz The class which will decide jar or project.
     * @return
     */
    public static File getApplicationDirectory(Class clazz) {
        File path = null;
        try {
            //Get class direcctory.
            path = new File(clazz.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());
            if (path.exists()) {
                if (path.isFile()) {
                    //If it is in jar, get the parent
                    return path.getParentFile();
                } else {
                    //If directory it means it is running in IDE (Its just for easy development)
                    return path.getParentFile().getParentFile();
                }
            }
        } catch (URISyntaxException e) {
            //Impossible exception. Do nothing.
            e.printStackTrace();
        }
        return path;
    }
}
