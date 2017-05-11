import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikola on 26.04.2017..
 * Swen final project
 * Created in: 21 : 56
 */
class Loader {

    private File directory;

    Loader(String filePath) {
        directory = new File(filePath);
    }

    ArrayList<File> getFiles() {
        return new ArrayList<>(Arrays.asList(directory.listFiles()));
    }

    ArrayList<CustomImage> getImages(ArrayList<File> files) {
        ArrayList<CustomImage> customImages = new ArrayList<>();

        for (File file : files) {
            try {
                CustomImage customImage = new CustomImage(file);

                customImages.add(customImage);
            } catch (Exception ignored) {
            }
        }

        return customImages;
    }

}
