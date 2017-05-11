import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class Save {

    Save(CustomImage image) throws IOException {
        new File("Edited Images").mkdir();

        ImageIO.write(image.getBufferedImage(), "jpg", new File("Edited Images" + image.getUrl()));
    }

}