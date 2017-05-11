import java.util.ArrayList;

/**s
 * Created by Tomislav on 5/4/2017.
 */
public class Album {

    public ArrayList<CustomImage> images = new ArrayList<>();
    private String albumName;
    private int albumId;

    public Album(String albumName) {
        this.albumName = albumName;
    }

    public void addImages(CustomImage image) {
        images.add(image);
    }

    public ArrayList<CustomImage> getImages() {
        return images;
    }

    public void setImages(ArrayList<CustomImage> images) {
        this.images = images;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
