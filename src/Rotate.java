import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by Tomislav on 4/29/2017.
 * Swen final project
 * Created in: 21 : 32
 * <p>
 * Rotates the image
 */

class Rotate {

    /**
     * Rotates the image
     *
     * @param image CustomImage that needs to be edited
     */
    Rotate(CustomImage image) {
        BufferedImage bf = image.getBufferedImage();
        BufferedImage newImage = createRotated(bf);

        image.setImage(new ImageIcon(newImage));
        image.setBuffered(newImage);
    }

    /**
     * Creates rotated image
     *
     * @param image BufferedImage of image you want to set
     * @return Edited image
     */
    private static BufferedImage createRotated(BufferedImage image) {
        AffineTransform at = new AffineTransform();

        at.translate(image.getWidth() / 2, image.getHeight() / 2);
        at.rotate(Math.PI / 2);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();

        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return newImage;
    }

}
