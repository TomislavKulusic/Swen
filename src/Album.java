import java.util.ArrayList;

/**
 * Created by Tomislav on 5/4/2017.
 */
public class Album {

    public String albumName;
    public ArrayList<CustomImage> images = new ArrayList<CustomImage>();

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public void addImages(CustomImage image) {
        images.add(image);
    }

    public ArrayList<CustomImage> getImages() {
        return images;
    }
}
