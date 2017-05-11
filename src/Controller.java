import java.io.IOException;
import java.sql.Connection;

public class Controller {

    /**
     * Default constructor
     */
    public Controller() {}

    /**
     * Save an image
     * @param img Image to be saved
     * @return Returns true if save is successful, otherwise return false
     */
    boolean Save(CustomImage img) {
        try {
            new Save(img);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Resize an image
     * @param img Image to be resized
     * @param width New image width
     * @param height New image height
     */
    void Resize(CustomImage img, int width, int height) {
        new Resize(img, width, height);
    }

    /**
     * Flip an image
     * @param img Image to be flipped
     */
    void Flip(CustomImage img) {
        new Flip(img);
    }

    /**
     * Rotate an image by 90 degrees
     * @param img Image to be rotated
     */
    void Rotate(CustomImage img) {
        new Rotate(img);
    }

    /**
     * Add a new tag to an image
     * @param tag New image tag
     * @param connection Database connection
     * @param tags Current image tags
     * @param name Image name
     */
    void addTag(String tag, Connection connection, String tags, String name) {
        new AddTag(tag, connection, tags, name);
    }

}