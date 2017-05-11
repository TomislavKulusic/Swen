import java.io.IOException;
import java.sql.Connection;

public class Controller {

    private CustomImage customImage;

    public Controller() {

    }

    public Controller(CustomImage customImage) {
        this.customImage = customImage;
    }

    boolean Save(CustomImage img) {
        try {
            new Save(img);
            return true;

        } catch (IOException e) {
            return false;
        }
    }

    void Resize(CustomImage img, int width, int height) {
        new Resize(img, width, height);
    }

    void Flip(CustomImage img) {
        new Flip(img);
    }

    void Rotate(CustomImage img) {
        new Rotate(img);
    }

    void addTag(String tag, Connection connection, String tags, String name) {
        new AddTag(tag, connection, tags, name);
    }

    public void setCustomImage(CustomImage customImage) {
        this.customImage = customImage;
    }
}