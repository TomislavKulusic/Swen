import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikola on 26.04.2017..
 */
public class Loader {

    String filePath;
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


                BufferedImage bImage = ImageIO.read(files.get(i));
                CustomImage customImage = new CustomImage(new ImageIcon(bImage));

                customImages.add(customImage);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return customImages;


    }

}
