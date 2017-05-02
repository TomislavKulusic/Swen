import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

/**
 * Created by Tomislav on 4/29/2017.
 */
public class Rotate {


    public void Rotate(CustomImage image, double a) {
        BufferedImage rotated = createRotated(image.getBufferedImage(), a);
        image.setImage(new ImageIcon(rotated));
        image.setBuffered(rotated);
    }


    private static BufferedImage createRotated(BufferedImage image, double deg)
    {
        AffineTransform at = AffineTransform.getRotateInstance(Math.PI, image.getWidth()/2, image.getHeight()/2.0);
        return createTransformed(image, at);
    }

    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at)
    {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.transform(at);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

}
