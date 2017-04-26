import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Nikola on 26.04.2017..
 */
public class Loader {

    String filePath;
    File directory;

    public Loader(String filePath){

        this.filePath = filePath;
        directory = new File(filePath);


    }

    public ArrayList<File> getFiles(){

        return new ArrayList<File>(Arrays.asList(directory.listFiles()));

    }

}
