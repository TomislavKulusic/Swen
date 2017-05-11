import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Flips the image
 */
class Flip {

    /**
     * Flips the image
     *
     * @param image Image that needs to be flipped
     */
    Flip(CustomImage image) {
        BufferedImage flipped = createFlipped(image.getBufferedImage());
        image.setImage(new ImageIcon(flipped));
        image.setBuffered(flipped);
    }

    /**
     * Creates image that is flipped
     * @param image The image that needs to be flipped
     * @return Flipped image
     */
    private static BufferedImage createFlipped(BufferedImage image) {
        AffineTransform at = new AffineTransform();
        at.concatenate(AffineTransform.getScaleInstance(1, -1));
        at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
        return createTransformed(image, at);
    }

    /**
     * Helps make image look good
     * @param image Image
     * @param at AffineTransform
     * @return New Image
     */
    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return newImage;
    }

}