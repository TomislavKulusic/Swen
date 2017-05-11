import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by Tomislav on 4/29/2017.
 * Swen final project
 * Created in: 21 : 32
 */

class Rotate {

    Rotate(CustomImage image) {

        BufferedImage bf = image.getBufferedImage();
        BufferedImage newImage = createRotated(bf);
        image.setImage(new ImageIcon(newImage));
        image.setBuffered(newImage);

    }

    private static BufferedImage createRotated(BufferedImage image) {
        AffineTransform at = new AffineTransform();

        at.translate(image.getWidth() / 2, image.getHeight() / 2);
        at.rotate(Math.PI / 2);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);

        return createTransformed(image, at);
    }

    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return newImage;
    }

}
