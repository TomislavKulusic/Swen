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

    /**
     * @param albumName
     */
    Album(String albumName) {
        this.albumName = albumName;
    }

    /** Add an image to the album
     * @param image
     */
    void addImages(CustomImage image) {
        images.add(image);
    }

    ArrayList<CustomImage> getImages() {
        return images;
    }

    /**
     * @param images
     */
    void setImages(ArrayList<CustomImage> images) {
        this.images = images;
    }

    /**
     * @return
     */
    String getAlbumName() {
        return albumName;
    }

    /**
     * @param albumName
     */
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    /**
     * @return
     */
    int getAlbumId() {
        return albumId;
    }

    /**
     * @param albumId
     */
    void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
