import javax.swing.*;
import java.awt.*;

/**
 * Resize the image
 */
class Resize {

    /**
     * Resize the image
     *
     * @param img    CustomImage image that needs to be saved
     * @param width  Width of image
     * @param height Height of image
     */
    Resize(CustomImage img, int width, int height) {
        img.setImage(new ImageIcon(img.getBufferedImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
        img.setBufferedFromImage();
    }

}
