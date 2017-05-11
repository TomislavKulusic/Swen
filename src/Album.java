import java.util.ArrayList;

/**
 * Created by Tomislav on 5/4/2017.
 * Swen final project
 * Created in: 22 : 05
 */
public class Album {

    private ArrayList<CustomImage> images = new ArrayList<>();
    private String albumName;
    private int albumId;

    Album(String albumName) {
        this.albumName = albumName;
    }

    void addImages(CustomImage image) {
        images.add(image);
    }

    ArrayList<CustomImage> getImages() {
        return images;
    }

    void setImages(ArrayList<CustomImage> images) {
        this.images = images;
    }

    String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    int getAlbumId() {
        return albumId;
    }

    void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
