import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;


public class Flip {


   private static BufferedImage createFlipped(BufferedImage image)
   {
      AffineTransform at = new AffineTransform();
      at.concatenate(AffineTransform.getScaleInstance(1, -1));
      at.concatenate(AffineTransform.getTranslateInstance(0, -image.getHeight()));
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
   
   public void Flip(CustomImage image)
   {  
      BufferedImage flipped  = createFlipped(image.getBufferedImage());
      image.setImage(new ImageIcon(flipped));
      image.setBuffered(flipped);
   }


}