import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikola on 26.04.2017..
 * Swen final project
 * Created in: 21 : 56
 *
 * Loads all images
 */
class Loader {

    private ArrayList<File> files;

    /**
     * @param filePath
     */
    Loader(String filePath) {
        this.files = new ArrayList<>(Arrays.asList(new File(filePath).listFiles()));
    }

    ArrayList<CustomImage> getImages() {
        ArrayList<CustomImage> customImages = new ArrayList<>();

        for (File file : files)
            try {
                CustomImage customImage = new CustomImage(file);

                customImages.add(customImage);
            } catch (Exception ignored) {
            }

        return customImages;
    }

    ArrayList<File> getFiles() {
        return files;
    }

}
