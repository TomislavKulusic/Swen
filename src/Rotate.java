import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

/**
 * Created by Tomislav on 4/29/2017.
 */
public class Rotate {


    public void Rotate(CustomImage image, double a) {
        //BufferedImage rotated = createRotated(image.getBufferedImage(), a);

        BufferedImage bf = image.getBufferedImage();

        BufferedImage newImage = new BufferedImage(bf.getWidth(),bf.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphic = newImage.createGraphics();

        graphic.rotate(Math.toRadians(10), bf.getWidth()/2, bf.getWidth()/2);

        graphic.drawImage(bf, null, 0, 0);


        image.setImage(new ImageIcon(newImage));
        image.setBuffered(newImage);
    }


//    private static BufferedImage createRotated(BufferedImage image, double deg)
//    {
//        AffineTransform at = AffineTransform.getRotateInstance(Math.PI, image.getWidth()/2, image.getHeight()/2);
//        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
//
//        return createTransformed(image, at);
//    }
//
//    private static BufferedImage createTransformed(BufferedImage image, AffineTransform at)
//    {
//        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g = newImage.createGraphics();
//        g.transform(at);
//        g.drawImage(image, 0, 0, null);
//        g.dispose();
//        return newImage;
//    }

}
