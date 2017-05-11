import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikola on 26.04.2017..
 */
public class Loader {

    String filePath;//s
    File directory;
    ArrayList<File> files;

    public Loader(String filePath){

        this.filePath = filePath;
        directory = new File(filePath);


    }

    public ArrayList<File> getFiles(){

        files = new ArrayList<File>(Arrays.asList(directory.listFiles()));
        return files;

    }

    public ArrayList<CustomImage> getImages(ArrayList<File> files){

        ArrayList<CustomImage> customImages = new ArrayList<>();

        for(int i = 0; i < files.size(); i++){

            try {

                CustomImage customImage = new CustomImage(files.get(i));

                customImages.add(customImage);


            } catch (Exception e) {
                
            }
        }

        return customImages;


    }

}
